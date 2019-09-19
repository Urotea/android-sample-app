package com.example.takao.androidboilerplate.ui.next

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.takao.androidboilerplate.state.AppState
import com.example.takao.androidboilerplate.store.StateAccessor
import javax.inject.Inject

class NextFragmentViewModel @Inject constructor(
    private val stateAccessor: StateAccessor<AppState>
) : ViewModel() {
    val pingPong =
        Transformations.distinctUntilChanged(
            Transformations.map(this.stateAccessor.state) { state ->
                state.nextFragmentState.pingPong.name
            })
}
