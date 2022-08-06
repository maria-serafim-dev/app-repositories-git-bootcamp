package com.example.portfoliogithub.domain.di

import com.example.portfoliogithub.domain.useCase.ListUserRepositoriesUseCaseImpl
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {


    fun load(){
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module{
        return module {
            factory {
                ListUserRepositoriesUseCaseImpl(get(), get())
            }
        }
    }
}