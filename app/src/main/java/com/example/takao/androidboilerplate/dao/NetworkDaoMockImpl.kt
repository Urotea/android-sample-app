package com.example.takao.androidboilerplate.dao

import com.example.takao.androidboilerplate.entity.PingPong
import kotlinx.coroutines.delay

class NetworkDaoMockImpl: NetworkDao {
    override suspend fun getPingPongFromServer(): PingPong {
        delay(1000)
        return PingPong.PING
    }
}
