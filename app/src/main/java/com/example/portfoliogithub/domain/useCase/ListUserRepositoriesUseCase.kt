package com.example.portfoliogithub.domain.useCase

import com.example.portfoliogithub.data.model.RepoWithOwner
import kotlinx.coroutines.flow.Flow

interface ListUserRepositoriesUseCase {
     suspend operator fun invoke(param: String) : Flow<RepoWithOwner>
}