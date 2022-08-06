package com.example.portfoliogithub.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.portfoliogithub.data.model.RepoWithOwner
import com.example.portfoliogithub.domain.useCase.ListUserRepositoriesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val listUserRepositoriesUseCase: ListUserRepositoriesUseCase) : ViewModel() {

    private val _listWithRepo = MutableLiveData<State>()
    val listWithRepo : LiveData<State> = _listWithRepo

    fun getRepoList(user: String){
        viewModelScope.launch {
            listUserRepositoriesUseCase(user)
                .onStart {
                    _listWithRepo.postValue(State.Loading)
                }
                .catch {
                    _listWithRepo.postValue(State.Error(it))
                }
                .collect{
                _listWithRepo.postValue(State.Success(it))
            }
        }
    }

    sealed class State{
        object Loading : State()
        data class Success(val repoWithOwner: RepoWithOwner) : State()
        data class Error(val error: Throwable) : State()
    }
}