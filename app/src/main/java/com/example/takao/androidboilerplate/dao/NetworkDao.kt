package com.example.takao.androidboilerplate.dao

import com.example.takao.androidboilerplate.entity.PingPong

interface NetworkDao {
    suspend fun getPingPongFromServer(): PingPong
}
