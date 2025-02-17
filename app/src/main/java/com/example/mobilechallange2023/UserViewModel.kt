package com.example.mobilechallange2023

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilechallange2023.Data.entity.User
import com.example.mobilechallange2023.Data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    fun insert(user: User) {
        viewModelScope.launch {
            repository.insert(user)
            getAllUsers() // Aktualisiere die Benutzerliste nach dem Einfügen
        }
    }

    fun getAllUsers() {
        viewModelScope.launch {
            val userList = repository.getAllUsers()
            _users.value = userList
        }
    }
}

