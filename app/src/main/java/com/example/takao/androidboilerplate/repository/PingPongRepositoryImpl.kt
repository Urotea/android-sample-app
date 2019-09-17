package com.example.takao.androidboilerplate.repository

import com.example.takao.androidboilerplate.dao.NetworkDao
import com.example.takao.androidboilerplate.entity.PingPong
import javax.inject.Inject

class PingPongRepositoryImpl @Inject constructor(
    private val networkDao: NetworkDao
) : PingPongRepository {
    override suspend fun getNewPingPongStatus(): PingPong {
        return this.networkDao.getPingPongFromServer()
    }
}
