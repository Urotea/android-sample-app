package com.example.takao.androidboilerplate.actions

import com.example.takao.androidboilerplate.entity.PingPong
import com.example.takao.androidboilerplate.redux.Action
import java.time.OffsetDateTime

sealed class MainActivityActions: Action {
    object IncrementButtonClicked: MainActivityActions()

    data class PingButtonClicked(val time: OffsetDateTime): MainActivityActions()

    data class PongNetworkResponseReceived(val pingPong: PingPong): MainActivityActions()
}