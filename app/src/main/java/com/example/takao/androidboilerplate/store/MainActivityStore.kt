package com.example.takao.androidboilerplate.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.redux.Middleware
import com.example.takao.androidboilerplate.redux.Reducer
import com.example.takao.androidboilerplate.redux.StreamPair
import com.example.takao.androidboilerplate.util.rx.SchedulerProvider
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
    private val stream = BehaviorSubject.create<StreamPair<MainActivityActions, MainActivityState>>()
    private val _state = MutableLiveData(MainActivityState())
    val mainActivityState: LiveData<MainActivityState> = this._state

    init {
        val logStream = this.logMiddleware.apply(this.stream.toFlowable(BackpressureStrategy.LATEST))
        logStream.subscribeOn(this.scheduler.io())
            .subscribeBy(onNext = { streamPair ->
                val newState = this.reducer.reduce(action = streamPair.action, state = streamPair.state)
                this._state.postValue(newState)
                Timber.d("after reduce. action=${streamPair.action}, state=$newState")
            }, onError = { error->
                Timber.e(error)
            }, onComplete = {
                Timber.i("MainActivityStore stream is closed.")
            }).addTo(disposeBag)
    }

    fun dispatch(action: MainActivityActions) {
        this.stream.onNext(StreamPair(action, this._state.value!!))
    }

    override fun onCleared() {
        super.onCleared()
        this.disposeBag.dispose()
    }

}
