package com.example.takao.androidboilerplate.dao.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubRepo(
    val owner: GithubOwner,
    @SerialName("name")
    val repoName: String,
    @SerialName("html_url")
    val url: String,
    @SerialName("stargazers_count")
    val star: String
)
