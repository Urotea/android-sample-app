package com.example.takao.androidboilerplate.ui.next

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.takao.androidboilerplate.state.MainActivityState

class NextFragmentProps(
    state: LiveData<MainActivityState>
) {
    val label: LiveData<String> = Transformations.map(state) {
        it.nextFragmentState.pingPong.toString()
    }
}
