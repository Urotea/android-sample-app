package com.example.takao.androidreduxsample.reducer

import com.example.takao.androidreduxsample.actions.Actions
import com.example.takao.androidreduxsample.store.State

class Reducer {

    fun reduce(action: Actions, state: State): State {
        return when(action) {
            Actions.IncrementButtonClicked -> {
                val oldNum = state.num
                state.copy(num = oldNum + 1)
            }
        }
    }
}