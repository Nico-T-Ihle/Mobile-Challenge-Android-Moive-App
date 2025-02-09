package com.example.mobilechallange2023.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.mobilechallange2023.R

@Composable
fun MovieListItem(
    onNavigate: (() -> Unit)? = null,
    moviePoster: String,
    title: String,
    releaseDate: String,
    rating: Double,
) {
    val year = if (releaseDate.length >= 4) releaseDate.substring(0, 4) else "N/A"
    val displayTitle = if (title.length > 13) title.take(13) + "..." else title
    val filledStars = rating.toInt()

    Column(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .clickable { onNavigate?.invoke() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .width(60.dp)
                    .height(100.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = rememberAsyncImagePainter(moviePoster),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 8.dp, bottom = 5.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 26.dp)
            ) {
                Text(
                    text = year,
                    color = Color.LightGray,
                    fontSize = 12.sp
                )
                Text(
                    text = displayTitle,
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                    modifier = Modifier.padding(bottom = 8.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row {
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
            }
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Image(
                    painter = painterResource(id = R.drawable.bookmark),
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .size(25.dp)
                )
            }

        }
    }
}


@Preview
@Composable
fun PreviewTiltedGrid() {
    MovieListItem(
        moviePoster = "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
        title = "The Dark Knight",
        releaseDate = "2008-07-14",
        rating = 4.2495
    )
}