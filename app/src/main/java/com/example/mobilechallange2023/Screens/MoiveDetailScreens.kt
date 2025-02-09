package com.example.mobilechallange2023.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.mobilechallange2023.R

@Composable
fun MovieDetailScreen(
    movie: String,
    year: String,
    rating: Float,
    posterUrl: String,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = {  navController.popBackStack() }) {
            Text(text = "back")
        }


        Spacer(modifier = Modifier.height(8.dp))

        Text(text = movie, fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Image(
            painter = rememberAsyncImagePainter(posterUrl),
            contentDescription = "Movie Poster",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Erscheinungsjahr: $year", fontSize = 18.sp)
        Text(text = "Bewertung: $rating/5", fontSize = 18.sp)
    }
}
