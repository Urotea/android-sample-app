package com.example.takao.androidboilerplate.reducer

import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.entity.PingPong
import com.example.takao.androidboilerplate.state.MainActivityState
import com.freeletics.rxredux.Reducer
import javax.inject.Inject

interface MainActivityReducer {
    val reducer: Reducer<MainActivityState, MainActivityActions>
}

class MainActivityReducerImpl @Inject constructor() : MainActivityReducer {

    override val reducer: Reducer<MainActivityState, MainActivityActions> = { state, action ->
        when (action) {
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
