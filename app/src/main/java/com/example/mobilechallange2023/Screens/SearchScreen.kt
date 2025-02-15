package com.example.mobilechallange2023.Screens

import MovieViewModel
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobilechallange2023.Components.MovieListItem
import com.example.mobilechallange2023.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: MovieViewModel,
) {
    var searchItem by remember { mutableStateOf("") }

    val filteredMovies = viewModel.movies.filter {
        it.title.contains(searchItem, ignoreCase = true)
    }

    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow),
                            contentDescription = "Back Icon",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "All Movies",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.weight(1.5f))
                }

                Column(
                    modifier = Modifier.padding(top = 29.dp, bottom = 29.dp)
                ) {
                    TextField(
                        value = searchItem,
                        onValueChange = { searchItem = it },
                        placeholder = {
                            Text("Search", fontWeight = FontWeight.Bold, color = Color.Gray)
                        },
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
                            .background(Color.LightGray),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.search),
                                contentDescription = "Search Icon",
                                tint = Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                }

                Column {
                    filteredMovies.forEach { movie ->
                        MovieListItem(
                            onNavigate = {
                                val route = "MovieDetailScreen/" + Uri.encode(movie.title) + "/" +
                                        movie.year + "/" + movie.rating + "/" +
                                        Uri.encode(movie.posterUrl) + "/" + movie.revenue + "/" +
                                        Uri.encode(movie.releaseDate) + "/" +
                                        Uri.encode(movie.director?.name ?: "N/A") + "/" +
                                        Uri.encode(movie.director?.pictureUrl ?: "") + "/" +
                                        movie.runtime + "/" +
                                        Uri.encode(movie.overview ?: "") + "/" +
                                        movie.reviews + "/" +
                                        movie.budget + "/" +
                                        Uri.encode(movie.language ?: "N/A") + "/" +
                                        Uri.encode(movie.genres?.joinToString(",") ?: "")

                                println("Navigating to: $route")
                                navController.navigate(route)
                            },
                            moviePoster = movie.posterUrl,
                            title = movie.title,
                            releaseDate = movie.year.toString(),
                            rating = movie.rating.toDouble()
                        )
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun SearchScreenPreview(){
    val navController = rememberNavController()
    SearchScreen(navController = navController, viewModel = MovieViewModel())
}