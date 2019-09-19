package com.example.takao.androidboilerplate.reducer

import com.example.takao.androidboilerplate.actions.AppActions
import com.example.takao.androidboilerplate.entity.PingPong
import com.example.takao.androidboilerplate.state.AppState
import com.freeletics.coredux.Reducer
import javax.inject.Inject

interface AppReducer {
    val reducer: Reducer<AppState, AppActions>
}

class AppReducerImpl @Inject constructor() : AppReducer {

    override val reducer: Reducer<AppState, AppActions> = { state, action ->
        when (action) {
            AppActions.IncreaseCount -> {
                val newMainFragmentState = state.mainFragmentState.copy(num = state.mainFragmentState.num + 1)
                state.copy(mainFragmentState = newMainFragmentState)
            }
            is AppActions.StartPingPong -> {
                val newNextFragmentState = state.nextFragmentState.copy(pingPong = PingPong.PONG)
                state.copy(nextFragmentState = newNextFragmentState)
            }
            is AppActions.PongNetworkResponseReceived -> {
                val newNextFragmentState = state.nextFragmentState.copy(pingPong = action.pingPong)
                state.copy(nextFragmentState = newNextFragmentState)
            }
            is AppActions.GoToNextFragment -> {
                val newMainFragmentState = state.mainFragmentState.copy(shouldGoToNextFragment = true)
                state.copy(mainFragmentState = newMainFragmentState)
            }
            is AppActions.LeaveFromMainFragment -> {
                state.copy(mainFragmentState = AppState.MainFragmentState(num = state.mainFragmentState.num))
            }
            is AppActions.LeaveFromNextFragment -> state
        }
    }
}
