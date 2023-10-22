package com.polendina.kallery.ui.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.polendina.kallery.R
import com.polendina.kallery.data.model.Photo
import com.polendina.kallery.ui.theme.KalleryTheme
import com.polendina.kallery.utils.titleCase

@Composable
fun ImagePreviewScreen(
    photo: Photo
) {
    Scaffold (
        topBar = {
            Box (
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 5.dp,
                            bottomEnd = 5.dp
                        )
                    )
                    .background(MaterialTheme.colorScheme.onPrimary)
            ) {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                    )
                    Column (
                        modifier = Modifier
                            .weight(7f)
                    ) {
                        Text(
                            text = photo.title.titleCase(),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                        )
                        Text(
                            text = photo.title.split(" ").subList(0, 2).joinToString(" "),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            style = TextStyle(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.outline
                            )
                        )
                    }
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
        },
        bottomBar = {
            Row (
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Actions.entries.forEach {
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = null
                            )
                        }
                        Text(
                            text = it.title,
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    }
                }
            }
        }
    ) {
        var scale by remember { mutableStateOf(1f) }
        var offset by remember { mutableStateOf(Offset.Zero) }
        BoxWithConstraints (
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
                scale = (scale * zoomChange).coerceIn(1f, 5f)
                val maxX = (scale - 1) * constraints.maxWidth / 2
                val maxY = (scale - 1) * constraints.maxHeight / 2
                offset = Offset(
                    x = (offset.x + panChange.x).coerceIn(-maxX, maxX),
                    y = (offset.y + panChange.y).coerceIn(-maxY, maxY)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.gradient),
                contentDescription = null,
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationX = offset.x
                        translationY = offset.y
                    }
                    .transformable(state)
            )
        }
    }
}

internal enum class Actions (
    val title: String,
    val icon: ImageVector
) {
    SHARE(title = "Share", icon = Icons.Outlined.Share),
    FAVORITE(title = "Favorite", icon = Icons.Outlined.FavoriteBorder),
    EDIT(title = "Edit", icon = Icons.Outlined.Edit),
    DELETE(title = "Delete", icon = Icons.Outlined.Delete),
    MORE(title = "More", icon = Icons.Outlined.Share)
}

@Preview(showBackground = true)
@Composable
fun ImagePreviewScreenPreview() {
    KalleryTheme {
        ImagePreviewScreen(
            Photo(albumId = 9, id = 20, title = "accusamus beatae ad facilis cum similique qui sunt", url = "", thumbnailUrl = "")
        )
    }
}