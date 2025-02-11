package com.example.mobilechallange2023.Screens

import MovieViewModel
import TiltedGrid
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.mobilechallange2023.Components.ButtonComponent
import com.example.mobilechallange2023.Components.CustomTextField
import com.example.mobilechallange2023.Components.UserProfile

@Composable
fun LoginScreen(
    onNavigate: (() -> Unit)? = null,
    onBack: (() -> Unit)? = null
) {
    var name by remember { mutableStateOf("") }
    var mail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.height(340.dp)
            ) {
                Column(
                    modifier = Modifier
                        .graphicsLayer(rotationZ = -8f)
                        .offset(x = -15.dp, y = 0.dp)
                ) {
                    TiltedGrid(viewModel = MovieViewModel())
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 32.dp)
                ) {
                    UserProfile()
                }
            }
        }
        Column(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 10.dp)
        ) {
            CustomTextField(
                text = name,
                onTextChange = { name = it },
                type = "name"
            )
            CustomTextField(
                text = mail,
                onTextChange = { mail = it },
                type = "mail"
            )
            CustomTextField(
                text = password,
                onTextChange = {
                    password = it
                    passwordError = password != confirmPassword
                },
                type = "password"
            )
            CustomTextField(
                text = confirmPassword,
                onTextChange = {
                    confirmPassword = it
                    passwordError = password != confirmPassword
                },
                type = "confirm_password",
                password = password,
                confirmPassword = confirmPassword,
                passwordError = passwordError
            )
            ButtonComponent(buttonText = "Sign Up", action = {
                onNavigate?.invoke()
            })
        }
    }
}