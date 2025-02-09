package com.example.mobilechallange2023.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobilechallange2023.R

@Composable
fun UserProfile(
    painter: Painter? = null,
) {
    if (painter == null) {
        ExampleBox(shape = CircleShape)
    } else {
        Image(
            painter = painter,
            contentDescription = "user_profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun ExampleBox(shape: Shape){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)) {
        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(115.dp)
                    .clip(shape)
                    .background(Color.White),
            )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.image_placeholder),
                    contentDescription = "Password Icon"
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UserProfile(painter = painterResource(id = R.drawable.user_profile))
    // painter = painterResource(id = R.drawable.user_profile)
}