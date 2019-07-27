package com.example.takao.androidboilerplate.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.takao.androidboilerplate.state.MainActivityState

class MainFragmentProps(
    state: LiveData<MainActivityState>
) {
    val numberLabel = Transformations.map(state) {
        "${it.mainFragmentState.num}回"
    }
}
