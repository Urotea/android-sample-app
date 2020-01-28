package com.example.takao.androidboilerplate.ui.third

import com.example.takao.androidboilerplate.actions.AppActions
import com.example.takao.androidboilerplate.store.Dispatcher
import javax.inject.Inject

class ThirdFragmentActionCreator @Inject constructor(
    private val dispatcher: Dispatcher<AppActions>
) {
}