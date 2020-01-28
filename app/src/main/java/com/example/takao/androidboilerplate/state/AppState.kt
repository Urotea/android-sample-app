package com.example.takao.androidboilerplate.state

import com.example.takao.androidboilerplate.entity.PingPong

data class AppState(
    val mainFragmentState: MainFragmentState = MainFragmentState(),
    val nextFragmentState: NextFragmentState = NextFragmentState()
) {

    data class MainFragmentState(
        val num: Int = 0,
        val shouldGoToNextFragment: Boolean = false
    )

    data class NextFragmentState(
        val pingPong: PingPong = PingPong.PING
    )
}
