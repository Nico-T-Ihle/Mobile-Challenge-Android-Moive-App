package com.example.mobilechallange2023

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun UserListScreen(viewModel: UserViewModel = hiltViewModel()) {
    val users = viewModel.users.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    Column {
        Text("Hello")
        LazyColumn {
            items(users.value) { user ->
                Text(text = "${user.name}, ${user.age} Jahre alt")
            }
        }
    }

    scope.launch {
        viewModel.getAllUsers()
    }
}
