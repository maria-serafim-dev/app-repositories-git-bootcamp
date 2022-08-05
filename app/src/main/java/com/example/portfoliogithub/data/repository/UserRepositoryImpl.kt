package com.example.portfoliogithub.data.repository

import com.example.portfoliogithub.core.RemoteException
import com.example.portfoliogithub.data.services.GitHubService
import com.example.portfoliogithub.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class UserRepositoryImpl (private val service: GitHubService) : UserRepository{

    override suspend fun listUser(user: String) = flow {
        try{
            val repoList = service.listUser(user)
            emit(repoList)
        }catch (ex: HttpException) {
            throw RemoteException(ex.message ?: "Não foi possível fazer a busca no momento")
        }
    }
}