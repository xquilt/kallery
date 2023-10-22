package com.polendina.kallery.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.polendina.kallery.R
import com.polendina.kallery.data.model.Album
import com.polendina.kallery.utils.titleCase
import com.polendina.kallery.ui.profile.ProfileScreenViewModelMock
import com.polendina.kallery.ui.theme.KalleryTheme

@Composable
fun UserAlbums(
    albums: List<Album>,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(id = R.string.albums),
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 30.sp
            )
        )
        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(albums) {
                AlbumWidget(
                    album = it,
                    favorite = it.favorite ?: false
                )
            }
        }
    }
}

@Composable
private fun AlbumWidget(
    album: Album,
    favorite: Boolean,
    modifier: Modifier = Modifier
        .background(MaterialTheme.colorScheme.secondaryContainer)
) {
    Box (
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box (
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
                    .fillMaxHeight()
                    .width(100.dp)
                    .weight(2f)
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(40.dp)
                )
            }
            Column (
                modifier = Modifier
                    .padding(
                        horizontal = 10.dp
                    )
                    .weight(4f)
            ) {
                Text(
                    text = album.title.titleCase(),
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    text = stringResource(id = R.string.id).uppercase()
                            + " "
                            + album.id.toString()
                            + " "
                            + stringResource(id = R.string.user_id).uppercase()
                            + " "
                            + album.userId.toString(),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.secondary
                    )
                )
            }
            IconButton(
                onClick = {
                    album.favorite = album.favorite?.not() ?: false
                },
                modifier = Modifier
                    .padding(
                        horizontal = 10.dp
                    )
                    .size(40.dp)
                    .weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    tint = if (favorite) Color.Red else Color.White,
                    contentDescription = null,
                )
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 400, widthDp = 400)
@Composable
fun AlbumWidgetPreview() {
    KalleryTheme {
        Column (
            Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 10.dp,
                    vertical = 10.dp
                )
        ) {
            UserAlbums(
                albums = ProfileScreenViewModelMock().albums
            )
        }
    }
}