package com.example.mobilechallange2023.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.mobilechallange2023.R
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun ExampleBox(
    shape: Shape,
    navController: NavHostController){
    Column(
        modifier = Modifier
            .wrapContentSize(Alignment.Center)) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(shape)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.close),
                        contentDescription = "Password Icon",
                        modifier = Modifier.size(15.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun MovieDetailScreen(
    title: String,
    year: String,
    rating: Float,
    posterUrl: String,
    revenue: Int,
    releaseDate: String,
    directorName: String,
    directorPictureUrl: String,
    runtime: Int,
    overview: String,
    reviews: Int,
    budget: Int,
    language: String,
    genres: String,
    navController: NavHostController
) {
    val filledStars = rating.toInt()
    val hours = runtime / 60
    val minutes = runtime % 60

    val formattedRuntime = "$hours h $minutes m"

    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val date = inputFormat.parse(releaseDate)
    val formattedDate = date?.let { outputFormat.format(it) } ?:  "Invalid date"

    LazyColumn {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(id = R.drawable.bookmark),
                        contentDescription = "Bookmark saved",
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                    ExampleBox(
                        shape = CircleShape,
                        navController = navController,
                    )
                }

                Column {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(top = 49.dp, bottom = 18.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(posterUrl),
                            contentDescription = "Movie Poster",
                            modifier = Modifier
                                .shadow(
                                    elevation = 35.dp,
                                    ambientColor = Color.Gray,
                                    spotColor = Color.Gray,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .height(200.dp),
                        )
                    }

                    Column(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier.padding(bottom = 12.dp)
                        ) {
                            for (i in 1..5) {
                                Image(
                                    painter = painterResource(id = R.drawable.star),
                                    contentDescription = "Star",
                                    modifier = Modifier
                                        .size(16.dp)
                                        .padding(end = 2.dp),
                                    colorFilter = ColorFilter.tint(
                                        if (i <= filledStars) Color(0xFFFD9E02) else Color.Gray // Farbe basierend auf der Bewertung setzen
                                    )
                                )
                            }
                        }
                        Text(
                            text = "$formattedDate · $formattedRuntime",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )
                        Row(
                            modifier = Modifier.padding(bottom = 12.dp)
                        ) {
                            Text(text = title, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                            Text(text = " ($year)", color = Color.LightGray, fontSize = 24.sp)
                        }

                        Text(text = genres)
                    }

                    Column {
                        Text(text = "Overview", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp), fontSize = 16.sp)
                        Text(text = overview, color = Color.LightGray, modifier = Modifier.padding(bottom = 16.dp), fontSize = 16.sp)
                    }

                    Text(text = "Key Facts", fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp), fontSize = 16.sp)
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row {
                            InfoBox(label = "Budget", value = "$$budget")
                            InfoBox(label = "Revenue", value = "$$revenue")
                        }
                        Row {
                            InfoBox(label = "Original Language", value = language)
                            InfoBox(label = "Rating", value = "$rating")
                        }
                    }

                }
            }
        }

    }

}

@Composable
fun InfoBox(label: String, value: String) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color.LightGray,
        modifier = Modifier
            .padding(8.dp)
            .height(100.dp)
            .width(150.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.W300,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailScreenPrev() {
    val navController = rememberNavController()
    MovieDetailScreen(
        title = "1917",
        year = "2019",
        rating = 3.987f,
        posterUrl = "https://image.tmdb.org/t/p/w500/iZf0KyrE25z1sage4SYFLCCrMi9.jpg",
        revenue = 374733942,
        releaseDate = "2019-12-25",
        directorName = "Sam Mendes",
        directorPictureUrl = "https://image.tmdb.org/t/p/w500/5z89X9rB76JDblqMQ52fviwXxAN.jpg",
        runtime = 119,
        overview = "At the height of the First World War, two young British soldiers must cross enemy territory and deliver a message that will stop a deadly attack on hundreds of soldiers.",
        reviews = 9932,
        budget = 100000000,
        language = "en",
        genres = "War, Drama, Action, Thriller, History",
        navController = navController
    )
}
