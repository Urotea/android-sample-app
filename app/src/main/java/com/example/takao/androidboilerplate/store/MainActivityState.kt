package com.example.takao.androidboilerplate.store

import com.example.takao.androidboilerplate.entity.PingPong
import com.example.takao.androidboilerplate.redux.State

data class MainActivityState(
    val mainFragmentState: MainFragmentState = MainFragmentState(),
    val nextFragmentState: NextFragmentState = NextFragmentState()
): State {

    data class MainFragmentState(
        val num: Int = 0
    )

    data class NextFragmentState(
        val pingPong: PingPong = PingPong.PING
    )
}
