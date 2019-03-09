package com.example.takao.androidboilerplate.redux

interface Reducer<S: Action, T: State> {
    fun reduce(action: S, state: T): T
}