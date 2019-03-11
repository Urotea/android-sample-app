package com.example.takao.androidboilerplate.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.redux.Middleware
import com.example.takao.androidboilerplate.redux.Reducer
import com.example.takao.androidboilerplate.util.rx.SchedulerProvider
import com.example.takao.androidboilerplate.util.rx.toLiveData
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import javax.inject.Inject

class MainActivityStore @Inject constructor(
    private val reducer: Reducer<MainActivityActions, MainActivityState>,
    private val scheduler: SchedulerProvider,
    private val logMiddleware: Middleware<MainActivityActions, MainActivityState>
) : ViewModel() {

    private val disposeBag = CompositeDisposable()
    private val stream = BehaviorSubject.create<MainActivityActions>()
    private val _state = BehaviorSubject.createDefault(MainActivityState())
    val mainActivityState: LiveData<MainActivityState> =
        this._state.toFlowable(BackpressureStrategy.BUFFER).toLiveData()

    init {
        val actionStream = this.stream.toFlowable(BackpressureStrategy.BUFFER)
        val logStream = this.logMiddleware.apply(actionStream, this._state.toFlowable(BackpressureStrategy.BUFFER))
        logStream.subscribeOn(this.scheduler.io())
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

    fun dispatch(action: MainActivityActions) {
        this.stream.onNext(action)
    }

    override fun onCleared() {
        super.onCleared()
        this.disposeBag.dispose()
    }

}
