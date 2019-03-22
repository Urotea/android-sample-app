package com.example.takao.androidboilerplate.dao

import com.example.takao.androidboilerplate.entity.PingPong
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class NetworkDaoMockImpl: NetworkDao {
    override fun getPingPongFromServer(): PingPong = runBlocking {
        delay(1000)
        PingPong.PING
    }
}
