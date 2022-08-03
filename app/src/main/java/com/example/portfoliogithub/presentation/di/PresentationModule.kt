package com.example.portfoliogithub.presentation.di

import com.example.portfoliogithub.presentation.viewModel.MainViewModel
import com.example.portfoliogithub.presentation.ui.RepoListAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {


    fun load() {
        loadKoinModules(viewModelModule() + adapterModule())
    }


    private fun viewModelModule(): Module {
        return module {
            viewModel {
                MainViewModel(get(), get())
            }
        }
    }

    private fun adapterModule() : Module{
        return module {
            factory {
                RepoListAdapter()
            }
        }
    }
}