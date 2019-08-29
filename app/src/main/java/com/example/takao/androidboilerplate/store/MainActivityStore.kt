package com.example.takao.androidboilerplate.store

import androidx.lifecycle.LiveData
import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.sideEffect.MainActivitySideEffects
import com.example.takao.androidboilerplate.reducer.MainActivityReducer
import com.example.takao.androidboilerplate.state.MainActivityState
import com.example.takao.androidboilerplate.util.rx.toLiveData
import com.freeletics.rxredux.reduxStore
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.BackpressureStrategy
import timber.log.Timber
import javax.inject.Inject

interface MainActivityStore {
    val state: LiveData<MainActivityState>
    fun dispatch(action: MainActivityActions)
}

class MainActivityStoreImpl @Inject constructor(
    private val reducer: MainActivityReducer,
    private val middleware: MainActivitySideEffects
): MainActivityStore {
    private val actionStream = PublishRelay.create<MainActivityActions>()
    override val state: LiveData<MainActivityState>

    init {
        state = actionStream
            .doOnNext{ Timber.d("$it")}
            .reduxStore(
                initialState = MainActivityState(),
                sideEffects = listOf(this.middleware.doWhenPingButtonClicked),
                reducer = this.reducer.reducer
            )
            .distinctUntilChanged()
            .doOnNext { Timber.d("$it") }
            .toFlowable(BackpressureStrategy.DROP)
            .toLiveData()
    }

    override fun dispatch(action: MainActivityActions) {
        actionStream.accept(action)
    }
}
