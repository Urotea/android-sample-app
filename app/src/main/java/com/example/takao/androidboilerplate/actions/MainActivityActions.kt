package com.example.takao.androidboilerplate.actions

import com.example.takao.androidboilerplate.entity.PingPong
import java.time.OffsetDateTime

sealed class MainActivityActions {
    object IncrementButtonClicked: MainActivityActions()

    data class PingButtonClicked(val time: OffsetDateTime): MainActivityActions()

    data class PongNetworkResponseReceived(val pingPong: PingPong): MainActivityActions()
}
