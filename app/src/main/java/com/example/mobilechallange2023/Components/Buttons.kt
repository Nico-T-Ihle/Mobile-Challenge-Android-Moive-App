package com.example.mobilechallange2023.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilechallange2023.ui.theme.blackButton

@Composable
fun ButtonComponent(
    buttonText: String,
    action: (() -> Unit?)? = null
) {
    Button(
        onClick = {
            action?.invoke()
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(blackButton),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = buttonText,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(
                    top = 12.dp,
                    bottom = 12.dp,
                    start = 30.dp,
                    end = 30.dp
                )
        )
    }
}

@Preview()
@Composable
fun DefaultPreview() {
    ButtonComponent(buttonText = "Sign Up", )
}