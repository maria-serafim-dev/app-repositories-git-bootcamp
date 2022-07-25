package com.example.portfoliogithub.domain

import com.example.portfoliogithub.core.UseCase
import com.example.portfoliogithub.data.model.Repo
import com.example.portfoliogithub.data.model.User
import com.example.portfoliogithub.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserUseCase(private val repository: RepoRepository) : UseCase<String, User>() {
    override suspend fun execute(param: String): Flow<User> {
        return  repository.listUser(param)
    }
}