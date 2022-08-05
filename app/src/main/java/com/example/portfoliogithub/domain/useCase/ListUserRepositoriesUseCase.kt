package com.example.portfoliogithub.domain.useCase

import com.example.portfoliogithub.core.UseCase
import com.example.portfoliogithub.data.model.Repo
import com.example.portfoliogithub.data.model.RepoWithOwner
import com.example.portfoliogithub.data.model.User
import com.example.portfoliogithub.domain.repository.RepoRepository
import com.example.portfoliogithub.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ListUserRepositoriesUseCase(private val repoRepository: RepoRepository, private val userRepository: UserRepository) : UseCase<String, RepoWithOwner>() {

    private lateinit var owner: User
    private lateinit var listRepo: List<Repo>

    override suspend fun execute(param: String): Flow<RepoWithOwner> = flow {

        repoRepository.listRepositories(param).collect{
            listRepo = it
        }

        userRepository.listUser(param).collect{
            owner = User(it.login, it.name, it.htmlURL, it.avatarURL)
        }

        emit(RepoWithOwner(owner, listRepo))
    }
}
