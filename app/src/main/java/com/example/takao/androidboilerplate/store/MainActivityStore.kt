package com.example.takao.androidboilerplate.store

import androidx.lifecycle.ViewModel
import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.redux.Action
import com.example.takao.androidboilerplate.redux.Middleware
import com.example.takao.androidboilerplate.redux.Reducer
import com.example.takao.androidboilerplate.ui.main.MainFragmentProps
import com.example.takao.androidboilerplate.ui.main.MainFragmentViewModel
import com.example.takao.androidboilerplate.ui.next.NextFragmentProps
import com.example.takao.androidboilerplate.ui.next.NextFragmentViewModel
import com.example.takao.androidboilerplate.util.rx.SchedulerProvider
import com.example.takao.androidboilerplate.util.rx.toLiveData
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class MainActivityStore @Inject constructor(
    private val reducer: Reducer<MainActivityActions, MainActivityState>,
    private val scheduler: SchedulerProvider,
    @Named("log") private val logMiddleware: Middleware<MainActivityActions, MainActivityState>,
    @Named("async") private val asyncMiddleware: Middleware<MainActivityActions, MainActivityState>
) : ViewModel(), MainFragmentViewModel, NextFragmentViewModel {

    private val disposeBag = CompositeDisposable()
    private val stream = BehaviorSubject.create<MainActivityActions>()
    private val _state = BehaviorSubject.createDefault(MainActivityState())

    override val nextFragmentProps: NextFragmentProps = this.toNextFragmentProps()
    override val mainFragmentProps: MainFragmentProps = this.toMainFragmentProps()

    init {
        val actionStream = run {
            val first = this.stream.toFlowable(BackpressureStrategy.LATEST).observeOn(this.scheduler.io())
            val second = this.logMiddleware.apply(first, this._state.toFlowable(BackpressureStrategy.LATEST))
            this.asyncMiddleware.apply(
                second, this._state.toFlowable(
                    BackpressureStrategy.LATEST
                )
            )
        }
        actionStream
            .subscribeBy(onNext = { action ->
                val newState = this.reducer.reduce(action = action, state = this._state.value!!)
                if (this._state.value != newState) {
                    this._state.onNext(newState)
                }
                Timber.d("after reduce. action=$action, state=$newState")
            }, onError = { error ->
                Timber.e(error)
            }, onComplete = {
                Timber.i("MainActivityStore stream is closed.")
            }).addTo(disposeBag)
    }

    override fun dispatch(action: MainActivityActions) {
        this.stream.onNext(action)
    }

    override fun onCleared() {
        super.onCleared()
        this.disposeBag.dispose()
    }

    private fun toMainFragmentProps(): MainFragmentProps {
        return MainFragmentProps(
            numberLabel = this._state
                .toFlowable(BackpressureStrategy.BUFFER)
                .map { "count: ${it.mainFragmentState.num}" }
                .toLiveData()
        )
    }

    private fun toNextFragmentProps(): NextFragmentProps {
        return NextFragmentProps(
            label = this._state
                .toFlowable(BackpressureStrategy.BUFFER)
                .map { it.nextFragmentState.pingPong.name }
                .toLiveData()
        )
    }
}
