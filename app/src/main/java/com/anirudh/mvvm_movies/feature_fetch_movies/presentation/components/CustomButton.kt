package com.anirudh.mvvm_movies.feature_fetch_movies.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    modifier: Modifier,
    name: String = "Book Now",
) {
    Card(
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(100.dp),
        backgroundColor = Color.White,
        elevation = 10.dp,
        border = BorderStroke(1.dp, Color.White),
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.wrapContentSize(Alignment.Center).padding(horizontal = 50.dp, vertical = 0.dp),
        )
    }
}