package com.anirudh.mvvm_movies.feature_fetch_movies.presentation.components

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.anirudh.mvvm_movies.core.Constants.BANNER_BASE_URL
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

@Composable
fun Banner(url: String, contentDescription: String, modifier: Modifier = Modifier, contentScale: ContentScale) {

    val bitmapState = remember { mutableStateOf<Bitmap?>(null) }
    val imageURL = BANNER_BASE_URL + url
    Glide.with(LocalContext.current).asBitmap().load(imageURL).into(
        object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        }
    )

    bitmapState.value?.let {
        Image(
            contentDescription = contentDescription,
            bitmap = it.asImageBitmap(),
            modifier = modifier,
            contentScale = contentScale
        )
    }
}

