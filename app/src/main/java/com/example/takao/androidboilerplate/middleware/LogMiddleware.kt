package com.example.takao.androidboilerplate.middleware

import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.redux.Middleware
import com.example.takao.androidboilerplate.store.MainActivityState
import io.reactivex.Flowable
import io.reactivex.rxkotlin.withLatestFrom
import timber.log.Timber
import javax.inject.Inject

class LogMiddleware @Inject constructor() : Middleware<MainActivityActions, MainActivityState> {
    override fun apply(
        actionStream: Flowable<MainActivityActions>,
        stateStream: Flowable<MainActivityState>
    ): Flowable<MainActivityActions> {
        return actionStream
            .withLatestFrom(stateStream)
            .doOnNext { Timber.d("before reducer. action=${it.first}, state=${it.second}") }
            .map { it.first }
    }
}
