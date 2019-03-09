package com.example.takao.androidboilerplate.redux

import io.reactivex.Flowable

interface Middleware<S : Action, T : State> {

    fun apply(stream: Flowable<StreamPair<S, T>>): Flowable<StreamPair<S, T>>
}