package com.example.mobilechallange2023.Screens

import com.example.mobilechallange2023.DataModel.Movie
import MovieViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.mobilechallange2023.Components.MovieListItem
import com.example.mobilechallange2023.Components.UserProfile
import com.example.mobilechallange2023.R

@Composable
fun ExampleBox(
    shape: Shape,
    onNavigate: () -> Unit,
){
    Column(modifier = Modifier
        .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(48.dp)
                .clip(shape)
                .shadow(8.dp, RoundedCornerShape(8.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            IconButton(onClick = {
                onNavigate.invoke()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Confirm Password Icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    onNavigate: (Movie) -> Unit,
    viewModel: MovieViewModel,
    onNav: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp, top = 58.dp)
            .fillMaxSize()
    ) {
        item {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.size(48.dp),
                    ) {
                        UserProfile(painter = painterResource(id = R.drawable.user_profile))
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                    ) {
                        Text(text = "Hello \uD83D\uDC4B")
                        Text(text = "Jane Doe", fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    ExampleBox(shape = RectangleShape, onNavigate = onNav)
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .padding(top = 52.dp, bottom = 20.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("YOUR ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("FAVORITES")
                        }
                    }
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier
                ) {
                    viewModel.movies.forEachIndexed { index, movie ->
                        TiltedBox(movie.posterUrl)
                        if (index < viewModel.movies.size - 1) {
                            Spacer(modifier = Modifier.width(30.dp))
                        }
                    }
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .padding(top = 40.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("OUR")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append(" STAFF PICKS")
                        }
                    }
                )
            }
        }

        items(viewModel.movies) { movie ->
            MovieListItem(
                onNavigate = {onNavigate(movie)},
                moviePoster = movie.posterUrl,
                title = movie.title,
                releaseDate = movie.year.toString(),
                rating = movie.rating.toDouble()
            )
        }
    }
}


@Composable
fun TiltedBox(title: String) {
   Column(
       modifier = Modifier
           .width(182.dp)
           .height(270.dp)
           .shadow(12.dp, RoundedCornerShape(8.dp))
   ) {
       Image(
           painter = rememberAsyncImagePainter(title),
           contentDescription = "Movie Poster",
           modifier = Modifier
               .fillMaxWidth()
               .clip(RoundedCornerShape(16.dp))
       )
   }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    val currentScreen = remember { mutableStateOf("Home") }
    HomeScreen(onNavigate = { currentScreen.value = "Details" }, viewModel = MovieViewModel(), onNav = {currentScreen.value = "NewScreen"})
}
