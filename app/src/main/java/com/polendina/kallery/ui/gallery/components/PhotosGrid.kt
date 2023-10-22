package com.polendina.kallery.ui.gallery.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polendina.kallery.data.model.Photo
import com.polendina.kallery.ui.gallery.GalleryViewModelMock
import com.polendina.kallery.ui.theme.KalleryTheme

@Composable
fun PhotosGrid(
    photos: List<Photo>,
    modifier: Modifier = Modifier
) {
    if (photos.isEmpty()) {
        NoResults()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = modifier
        ) {
            items(photos) {
                PhotoGridItem(
                    photo = it
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PhotoGridPreview() {
    KalleryTheme {
        Scaffold{
            PhotosGrid(
                photos = GalleryViewModelMock().photos.collectAsState().value,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        }
    }
}