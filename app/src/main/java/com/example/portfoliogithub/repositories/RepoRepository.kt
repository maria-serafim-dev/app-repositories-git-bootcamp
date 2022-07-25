package com.example.portfoliogithub.repositories

import com.example.portfoliogithub.data.model.Repo
import com.example.portfoliogithub.data.model.User
import kotlinx.coroutines.flow.Flow

interface RepoRepository {

    suspend fun listRepositories(user: String) : Flow<List<Repo>>

    suspend fun listUser(user: String) : Flow<User>
}