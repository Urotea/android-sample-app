package com.example.takao.androidboilerplate.ui.main

import com.example.takao.androidboilerplate.actions.AppActions
import com.example.takao.androidboilerplate.store.Dispatcher
import javax.inject.Inject

class MainFragmentActionCreator @Inject constructor(
    private val dispatcher: Dispatcher<AppActions>
) {
    fun countUp() {
        this.dispatcher.dispatch(AppActions.IncreaseCount)
    }
}