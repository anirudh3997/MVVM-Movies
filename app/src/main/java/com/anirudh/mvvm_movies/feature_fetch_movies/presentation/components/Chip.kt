package com.anirudh.mvvm_movies.feature_fetch_movies.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview()
@Composable
fun Chip(
    name: String = "Chip",
) {
    Card(
        modifier = Modifier.height(15.dp),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Color.Transparent,
        border = BorderStroke(1.dp, Color.White),
    ) {
        Text(
            text = name,
            fontSize = 10.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 0.dp)
        )
    }
}