package com.example.mobilechallange2023

import MovieViewModel
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilechallange2023.Components.CustomTextField
import com.example.mobilechallange2023.Data.entity.User
import com.example.mobilechallange2023.Data.repository.UserRepository
import com.example.mobilechallange2023.Screens.HomeScreen
import com.example.mobilechallange2023.Screens.LoginScreen
import com.example.mobilechallange2023.Screens.MovieDetailScreen
import com.example.mobilechallange2023.Screens.SearchScreen
import com.example.mobilechallange2023.ui.theme.MobileChallange2023Theme
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scope = rememberCoroutineScope()

            scope.launch {
                addDummyData()
            }

            UserListScreen()
        }
    }

    private suspend fun addDummyData() {
        val users = listOf(
            User(id = 0, name = "John Doe", age = 25),
            User(id = 0, name = "Jane Smith", age = 30),
            User(id = 0, name = "Alice Johnson", age = 22)
        )

        for (user in users) {
            userRepository.insert(user)
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
                    val route = "MovieDetailScreen/$encodedTitle/${movie.year}/${movie.rating}/$encodedPosterUrl/${movie.revenue}/" +
                            "${movie.releaseDate}/${Uri.encode(movie.director?.name)}/${Uri.encode(movie.director?.pictureUrl)}/${movie.runtime}/" +
                            "${Uri.encode(movie.plot)}/${movie.reviews}/${movie.budget}/${movie.language}/${
                                Uri.encode(
                                movie.genres.toString()
                            )}"
                    println("Navigating to: $route")
                    navController.navigate(route)
                },
                viewModel = viewModel,
                onNav = { navController.navigate("NewScreen") },
                navController = navController
            )
        }

        composable(
            route = "MovieDetailScreen/{title}/{year}/{rating}/{posterUrl}/{revenue}/{releaseDate}/{directorName}/{directorPictureUrl}/{runtime}/{overview}/{reviews}/{budget}/{language}/{genres}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("year") { type = NavType.StringType },
                navArgument("rating") { type = NavType.FloatType },
                navArgument("posterUrl") { type = NavType.StringType },
                navArgument("revenue") { type = NavType.IntType },
                navArgument("releaseDate") { type = NavType.StringType },
                navArgument("directorName") { type = NavType.StringType },
                navArgument("directorPictureUrl") { type = NavType.StringType },
                navArgument("runtime") { type = NavType.IntType },
                navArgument("overview") { type = NavType.StringType },
                navArgument("reviews") { type = NavType.IntType },
                navArgument("budget") { type = NavType.IntType },
                navArgument("language") { type = NavType.StringType },
                navArgument("genres") { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val title = backStackEntry.arguments?.getString("title") ?: "N/A"
            val year = backStackEntry.arguments?.getString("year") ?: "N/A"
            val rating = backStackEntry.arguments?.getFloat("rating") ?: 0.0f
            val posterUrl = backStackEntry.arguments?.getString("posterUrl") ?: ""
            val revenue = backStackEntry.arguments?.getInt("revenue") ?: 0
            val releaseDate = backStackEntry.arguments?.getString("releaseDate") ?: "N/A"
            val directorName = backStackEntry.arguments?.getString("directorName") ?: "N/A"
            val directorPictureUrl = backStackEntry.arguments?.getString("directorPictureUrl") ?: ""
            val runtime = backStackEntry.arguments?.getInt("runtime") ?: 0
            val overview = backStackEntry.arguments?.getString("overview") ?: "N/A"
            val reviews = backStackEntry.arguments?.getInt("reviews") ?: 0
            val budget = backStackEntry.arguments?.getInt("budget") ?: 0
            val language = backStackEntry.arguments?.getString("language") ?: "N/A"
            val genres = backStackEntry.arguments?.getString("genres") ?: ""

            MovieDetailScreen(
                title,
                year,
                rating,
                posterUrl,
                revenue,
                releaseDate,
                directorName,
                directorPictureUrl,
                runtime,
                overview,
                reviews,
                budget,
                language,
                genres,
                navController
            )
        }

        composable("NewScreen") {
            SearchScreen(navController = navController, viewModel = viewModel)
        }
    }
}


