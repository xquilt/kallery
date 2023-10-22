package com.polendina.kallery.ui.gallery

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polendina.kallery.R
import com.polendina.kallery.ui.gallery.components.PhotosGrid
import com.polendina.kallery.ui.theme.KalleryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryScreen(
    galleryViewModel: GalleryViewModel,
    modifier: Modifier = Modifier
) {
    Scaffold (
        topBar = {
             SearchBar(
                 query = galleryViewModel.searchQuery.collectAsState().value,
                 placeholder = {
                    Text(
                        text = stringResource(id = R.string.find_images),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.outline
                        )
                    )
                 },
                 onQueryChange = galleryViewModel::onQueryChange,
                 onSearch = galleryViewModel::onSearch,
                 active = true,
                 onActiveChange = {},
                 trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                 },
                 modifier = Modifier
                     .height(80.dp)
                     .padding(bottom = 10.dp)
                     .clip(RoundedCornerShape(10.dp))
             ) { }
        },
        bottomBar = {
            Row { }
        },
        modifier = Modifier
            .padding(10.dp)
    ) {
        PhotosGrid(
            photos = galleryViewModel.photos.collectAsState().value,
            modifier = Modifier
                .padding(it)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GalleryScreenPreview() {
    KalleryTheme {
        GalleryScreen(
            galleryViewModel = GalleryViewModelMock(),
            modifier = Modifier
                .fillMaxSize()
        )
    }
}