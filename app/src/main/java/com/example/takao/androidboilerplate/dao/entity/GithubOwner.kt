package com.example.takao.androidboilerplate.dao.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubOwner (
    val id: String,
    @SerialName("login")
    val name: String,
    @SerialName("avatar_url")
    val icon: String
)
