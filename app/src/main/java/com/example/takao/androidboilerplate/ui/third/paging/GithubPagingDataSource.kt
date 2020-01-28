package com.example.takao.androidboilerplate.ui.third.paging

import androidx.paging.PageKeyedDataSource
import com.example.takao.androidboilerplate.dao.GithubApi
import com.example.takao.androidboilerplate.dao.entity.GithubRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class GithubPagingDataSource(
    private val username: String,
    private val api: GithubApi, // ここをRepositoryPatternにしたい
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, GithubRepo>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GithubRepo>
    ) {
        this.scope.launch {
            this@GithubPagingDataSource.callApi(1, params.requestedLoadSize) { next, repos ->
                callback.onResult(repos, null, next)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GithubRepo>) {
        this.scope.launch {
            this@GithubPagingDataSource.callApi(
                params.key,
                params.requestedLoadSize
            ) { next, repos ->
                callback.onResult(repos, next)
            }
        }
    }

    // 途中から読み出すことはないので実装せず
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GithubRepo>) {}

    private suspend fun callApi(
        page: Int,
        perPage: Int,
        callback: (next: Int?, repos: List<GithubRepo>) -> Unit
    ) {
        val response = api.getGithubRepos(username, page, perPage)
        if (response.isSuccessful) {
            response.body()?.let {
                var next: Int? = null
                //Headerにnextがあれば次ページを加算
                response.headers().get("Link")?.let { value ->
                    val regex = Regex("rel=\"next\"")
                    if (regex.containsMatchIn(value)) {
                        next = page + 1
                    }
                }
                callback(next, it)
            }
        }
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}
