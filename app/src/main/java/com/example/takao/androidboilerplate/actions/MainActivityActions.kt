package com.example.takao.androidboilerplate.actions

import com.example.takao.androidboilerplate.redux.Action

sealed class MainActivityActions: Action {
    object IncrementButtonClicked: MainActivityActions()
}