package com.example.portfoliogithub.domain.repository

import com.example.portfoliogithub.data.model.Repo
import kotlinx.coroutines.flow.Flow

interface RepoRepository {

    suspend fun listRepositories(user: String) : Flow<List<Repo>>
}