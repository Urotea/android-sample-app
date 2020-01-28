package com.example.takao.androidboilerplate.actions

import com.example.takao.androidboilerplate.dao.entity.GithubOwner
import com.example.takao.androidboilerplate.entity.PingPong
import java.time.OffsetDateTime

sealed class AppActions {
    // MainFragment
    object IncreaseCount: AppActions()
    object GoToNextFragment: AppActions()
    object LeaveFromMainFragment: AppActions()

    // NextFragment
    data class StartPingPong(val time: OffsetDateTime): AppActions()
    data class PongNetworkResponseReceived(val pingPong: PingPong): AppActions()
    object LeaveFromNextFragment: AppActions()

    // ThirdFragment
    data class OwnerDataLoaded(val owner: GithubOwner): AppActions()
}
