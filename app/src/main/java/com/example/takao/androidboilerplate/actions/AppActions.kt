package com.example.takao.androidboilerplate.actions

import com.example.takao.androidboilerplate.entity.PingPong
import java.time.OffsetDateTime

sealed class AppActions {
    object IncreaseCount: AppActions()

    data class StartPingPong(val time: OffsetDateTime): AppActions()

    data class PongNetworkResponseReceived(val pingPong: PingPong): AppActions()
}
