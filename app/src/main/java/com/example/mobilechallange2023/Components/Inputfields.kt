package com.example.mobilechallange2023.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilechallange2023.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    text: String,
    onTextChange: (String) -> Unit,
    type: String,
    password: String = "",
    confirmPassword: String = "",
    passwordError: Boolean = false
) {
    var passwordIcon by remember { mutableStateOf(false) }
    var confirmPasswordIcon by remember { mutableStateOf(false) }

    when (type) {
        "name" -> TextField(
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = { Text("Name", fontWeight = FontWeight.Bold, color = Color.Gray) },
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .shadow(8.dp, RoundedCornerShape(8.dp))
                .background(Color.LightGray)
        )
        "mail" -> TextField(
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = { Text("Mail", fontWeight = FontWeight.Bold, color = Color.Gray) },
            textStyle = TextStyle(fontSize = 16.sp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .shadow(8.dp, RoundedCornerShape(8.dp))
                .background(Color.LightGray)
        )
        "password" -> TextField(
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = { Text("Password", fontWeight = FontWeight.Bold, color = Color.Gray) },
            textStyle = TextStyle(fontSize = 16.sp),
            visualTransformation = if (passwordIcon) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = {
                    passwordIcon = !passwordIcon
                }) {
                    Icon(
                        painter = if (passwordIcon) painterResource(id = R.drawable.visibility) else painterResource(
                            id = R.drawable.visibility_hidden
                        ),
                        contentDescription = "Password Icon"
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Gray
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .shadow(8.dp, RoundedCornerShape(8.dp))
                .background(Color.LightGray)
        )
        "confirm_password" -> Column {
            TextField(
                value = text,
                onValueChange = {
                    onTextChange(it)
                },
                placeholder = {
                    Column {
                        Text("Confirm Password", fontWeight = FontWeight.Bold, color = Color.Gray)
                        if (passwordError) {
                            Text(
                                text = "Passwords don't match",
                                color = Color.Red,
                                fontSize = 12.sp,
                                modifier = Modifier.padding( top = 4.dp)
                            )
                        }
                    }
                },
                textStyle = TextStyle(fontSize = 16.sp),
                visualTransformation = if (confirmPasswordIcon) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        confirmPasswordIcon = !confirmPasswordIcon
                    }) {
                        Icon(
                            painter = if (confirmPasswordIcon) painterResource(id = R.drawable.visibility) else painterResource(
                                id = R.drawable.visibility_hidden
                            ),
                            contentDescription = "Confirm Password Icon"
                        )
                    }
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .shadow(8.dp, RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            )
        }
    }
}

@Preview
@Composable
fun InputPreview() {
    var name by remember { mutableStateOf("") }
    var mail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }

    Column {
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
    }
}
