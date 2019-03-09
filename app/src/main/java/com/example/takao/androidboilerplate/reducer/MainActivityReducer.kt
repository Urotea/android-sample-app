package com.example.takao.androidboilerplate.reducer

import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.redux.Reducer
import com.example.takao.androidboilerplate.store.MainActivityState
import javax.inject.Inject

class MainActivityReducer @Inject constructor(): Reducer<MainActivityActions, MainActivityState> {

    override fun reduce(action: MainActivityActions, state: MainActivityState): MainActivityState {
        return when(action) {
            MainActivityActions.IncrementButtonClicked -> {
                val oldNum = state.num
                state.copy(num = oldNum + 1)
            }
        }
    }
}
