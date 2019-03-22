package com.example.takao.androidboilerplate.ui.main

import com.example.takao.androidboilerplate.actions.MainActivityActions

interface MainFragmentViewModel {

    fun dispatch(action: MainActivityActions)

    val mainFragmentProps: MainFragmentProps
}