package com.example.takao.androidboilerplate.dao

import com.example.takao.androidboilerplate.entity.PingPong
import kotlinx.coroutines.Deferred

interface NetworkDao {
    fun getPingPongFromServer(): PingPong
}