package com.example.portfoliogithub.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.portfoliogithub.data.model.Repo
import com.example.portfoliogithub.data.model.User
import com.example.portfoliogithub.domain.ListUserRepositoriesUseCase
import com.example.portfoliogithub.domain.ListUserUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val listUserRepositoriesUseCase: ListUserRepositoriesUseCase, private val listUserUseCase: ListUserUseCase) : ViewModel() {

    private val _repos = MutableLiveData<State>()
    val repos : LiveData<State> = _repos

    private val _user = MutableLiveData<StateUser>()
    val user : LiveData<StateUser> = _user

    fun getRepoList(user: String){
        viewModelScope.launch {
            listUserRepositoriesUseCase(user)
                .onStart {
                    _repos.postValue(State.Loading)
                }
                .catch {
                    _repos.postValue(State.Error(it))
                }
                .collect {
                    _repos.postValue(State.Success(it))
                }

            listUserUseCase.execute(user)
                .onStart {
                    _user.postValue(StateUser.Loading)
                }
                .catch {
                    _user.postValue(StateUser.Error(it))
                }
                .collect {
                    _user.postValue(StateUser.Success(it))
                }
        }
    }



    sealed class State{
        object Loading : State()
        data class Success(val list: List<Repo>) : State()
        data class Error(val error: Throwable) : State()

    }

    sealed class StateUser{
        object Loading : StateUser()
        data class Success(val user: User) : StateUser()
        data class Error(val error: Throwable) : StateUser()

    }

}