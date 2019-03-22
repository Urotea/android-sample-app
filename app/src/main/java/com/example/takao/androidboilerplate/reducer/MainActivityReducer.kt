package com.example.takao.androidboilerplate.reducer

import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.entity.PingPong
import com.example.takao.androidboilerplate.redux.Reducer
import com.example.takao.androidboilerplate.store.MainActivityState
import javax.inject.Inject

class MainActivityReducer @Inject constructor(): Reducer<MainActivityActions, MainActivityState> {

    override fun reduce(action: MainActivityActions, state: MainActivityState): MainActivityState {
        return when(action) {
            MainActivityActions.IncrementButtonClicked -> {
                val newMainFragmentState = state.mainFragmentState.copy(num = state.mainFragmentState.num + 1)
                state.copy(mainFragmentState = newMainFragmentState)
            }
            is MainActivityActions.PingButtonClicked -> {
                val newNextFragmentState = state.nextFragmentState.copy(pingPong = PingPong.PONG)
                state.copy(nextFragmentState = newNextFragmentState)
            }
            is MainActivityActions.PongNetworkResponseReceived -> {
                val newNextFragmentState = state.nextFragmentState.copy(pingPong = action.pingPong)
                state.copy(nextFragmentState = newNextFragmentState)
            }
        }
    }
}
