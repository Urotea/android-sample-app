package com.example.takao.androidboilerplate.ui.second.third.paging

import androidx.paging.DataSource
import com.example.takao.androidboilerplate.dao.GithubApi
import com.example.takao.androidboilerplate.dao.entity.GithubRepo
import kotlinx.coroutines.CoroutineScope

class DataSourceFactory(
    private val username: String,
    private val api: GithubApi,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, GithubRepo>() {

    override fun create(): DataSource<Int, GithubRepo> = GithubPagingDataSource(username, api, scope)
}
