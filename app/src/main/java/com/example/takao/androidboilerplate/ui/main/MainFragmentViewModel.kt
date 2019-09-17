package com.example.takao.androidboilerplate.ui.main

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.takao.androidboilerplate.state.AppState
import com.example.takao.androidboilerplate.store.StateAccessor
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val stateAccessor: StateAccessor<AppState>
): ViewModel() {
    val numberLabel = Transformations.map(this.stateAccessor.state) { state ->
        "${state.mainFragmentState.num}回"
    }

}
