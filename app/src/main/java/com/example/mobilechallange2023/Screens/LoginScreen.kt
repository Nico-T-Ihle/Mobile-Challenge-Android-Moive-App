package com.example.mobilechallange2023.Screens

import MovieViewModel
import TiltedGrid
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel = remember { MovieViewModel() }

    NavHost(navController = navController, startDestination = "LoginScreen") {

        composable("LoginScreen") {
            LoginScreen(
                onNavigate = { navController.navigate("HomeScreen") }
            )
        }

        composable("HomeScreen") {
            HomeScreen(
                onNavigate = { movie ->
                    val encodedTitle = Uri.encode(movie.title)
                    val encodedPosterUrl = Uri.encode(movie.posterUrl)
                    val route = "MovieDetailScreen/$encodedTitle/${movie.year}/${movie.rating}/$encodedPosterUrl"
                    println("Navigating to: $route")
                    navController.navigate(route)
                },
                viewModel = viewModel,
                onNav = {navController.navigate("NewScreen")}
            )
        }

        composable(
            route = "MovieDetailScreen/{title}/{year}/{rating}/{posterUrl}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("year") { type = NavType.StringType },
                navArgument("rating") { type = NavType.FloatType },
                navArgument("posterUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "N/A"
            val year = backStackEntry.arguments?.getString("year") ?: "N/A"
            val rating = backStackEntry.arguments?.getFloat("rating") ?: 0.0f
            val posterUrl = backStackEntry.arguments?.getString("posterUrl") ?: ""

            MovieDetailScreen(title, year, rating, posterUrl, navController)
        }

        composable("NewScreen") {
            NewScreen(navController = navController)
        }
    }
}

@Composable
fun NewScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text("Dies ist der neue Bildschirm!")


        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text(text = "Zurück zum HomeScreen")
        }
    }
}




@Preview
@Composable
fun PreviewTiltedGrid() {
    MainScreen()
}
