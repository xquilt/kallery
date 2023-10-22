package com.polendina.kallery.ui.gallery.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.polendina.kallery.data.model.Photo

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
internal fun PhotoGridItem(
    photo: Photo,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = Modifier
            .wrapContentHeight()
            .clip(RoundedCornerShape(10.dp))
//            .height(250.dp)
//            .background(colors().random())
    ) {
        GlideImage(
            model = photo.url,
            contentDescription = null,
            failure = placeholder(ColorPainter(Color.Gray))
        )
    }
}

