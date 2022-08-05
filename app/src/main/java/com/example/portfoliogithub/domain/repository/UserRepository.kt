package com.example.portfoliogithub.domain.repository

import com.example.portfoliogithub.data.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun listUser(user: String) : Flow<User>
}