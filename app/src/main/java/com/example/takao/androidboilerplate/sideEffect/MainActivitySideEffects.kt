package com.example.takao.androidboilerplate.sideEffect

import com.example.takao.androidboilerplate.actions.MainActivityActions
import com.example.takao.androidboilerplate.repository.PingPongRepository
import com.example.takao.androidboilerplate.state.MainActivityState
import com.example.takao.androidboilerplate.util.rx.SchedulerProvider
import com.freeletics.rxredux.SideEffect
import javax.inject.Inject

interface MainActivitySideEffects {
    val doWhenPingButtonClicked: SideEffect<MainActivityState, MainActivityActions>
}

class MainActivitySideEffectsImpl @Inject constructor(
    private val pingPongRepository: PingPongRepository,
    private val scheduler: SchedulerProvider
) : MainActivitySideEffects {

    override val doWhenPingButtonClicked: SideEffect<MainActivityState, MainActivityActions> = { action, _ ->
        action.ofType(MainActivityActions.PingButtonClicked::class.java)
            .observeOn(this.scheduler.io())
            .map { MainActivityActions.PongNetworkResponseReceived(this.pingPongRepository.getNewPingPongStatus()) }
    }
}
