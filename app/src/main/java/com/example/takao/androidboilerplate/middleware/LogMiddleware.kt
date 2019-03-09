package com.example.takao.androidboilerplate.middleware

import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.redux.Middleware
import com.example.takao.androidboilerplate.redux.StreamPair
import com.example.takao.androidboilerplate.store.MainActivityState
import io.reactivex.Flowable
import timber.log.Timber
import javax.inject.Inject

class LogMiddleware @Inject constructor() : Middleware<MainActivityActions, MainActivityState> {
    override fun apply(stream: Flowable<StreamPair<MainActivityActions, MainActivityState>>): Flowable<StreamPair<MainActivityActions, MainActivityState>> {
        return stream.doOnNext { Timber.d("before reducer. action=${it.action}, state=${it.state}") }
    }
}
