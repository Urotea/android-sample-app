package com.example.takao.androidboilerplate.dao

import com.example.takao.androidboilerplate.dao.entity.GithubOwner
import com.example.takao.androidboilerplate.dao.entity.GithubRepo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("/users/{username}/repos")
    suspend fun getGithubRepos(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<GithubRepo>>

    @GET("/users/{username}")
    suspend fun getOwner(@Path("username") username: String): Response<GithubOwner>
}