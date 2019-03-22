package com.example.takao.androidboilerplate.ui.next

import com.example.takao.androidboilerplate.actions.MainActivityActions

interface NextFragmentViewModel {

    fun dispatch(action: MainActivityActions)

    val nextFragmentProps: NextFragmentProps
}
