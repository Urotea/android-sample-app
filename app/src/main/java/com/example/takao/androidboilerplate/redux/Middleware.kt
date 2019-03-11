package com.example.takao.androidboilerplate.redux

import io.reactivex.Flowable

interface Middleware<S : Action, T : State> {

    fun apply(actionStream: Flowable<S>, stateStream: Flowable<T>): Flowable<S>
}