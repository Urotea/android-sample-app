package com.example.takao.androidboilerplate.redux

data class StreamPair<S: Action, T: State>(
    val action: S,
    val state: T
)