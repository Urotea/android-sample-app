package com.example.takao.androidboilerplate.ui

import androidx.lifecycle.ViewModel
import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.store.MainActivityStore
import com.example.takao.androidboilerplate.ui.main.MainFragmentProps
import com.example.takao.androidboilerplate.ui.next.NextFragmentProps
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val store: MainActivityStore
) : ViewModel() {

    val mainFragmentProps = MainFragmentProps(store.state)

    val nextFragmentProps = NextFragmentProps(store.state)

    fun dispatch(action: MainActivityActions) = this.store.dispatch(action)
}
