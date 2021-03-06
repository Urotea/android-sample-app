package com.example.takao.androidboilerplate.repository

import com.example.takao.androidboilerplate.entity.PingPong

interface PingPongRepository {
    suspend fun getNewPingPongStatus(): PingPong
}
