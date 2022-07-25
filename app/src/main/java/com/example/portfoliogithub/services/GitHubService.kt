package com.example.portfoliogithub.services

import com.example.portfoliogithub.data.model.Repo
import com.example.portfoliogithub.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("users/{user}/repos")
    suspend fun listRepositories(@Path("user") user : String ) : List<Repo>

    @GET("users/{user}")
    suspend fun listUser(@Path("user") user : String ) : User
}