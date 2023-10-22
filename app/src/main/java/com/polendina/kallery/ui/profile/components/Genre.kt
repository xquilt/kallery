package com.polendina.kallery.ui.profile.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.polendina.kallery.domain.models.Genre
import com.polendina.kallery.utils.titleCase
import com.polendina.kallery.ui.profile.ProfileScreenViewModelMock
import com.polendina.kallery.ui.theme.KalleryTheme
import com.polendina.kallery.ui.theme.colors

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GenresPreview(
    pagerState: PagerState,
    genres: List<Genre>
) {
    HorizontalPager(
        state = pagerState,
        pageSpacing = 10.dp
    ) {
        GenreCard(
            genre = genres[it]
        )
    }
}

@Composable
fun GenreCard(
    genre: Genre,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(20.dp)
    Box (
        modifier = Modifier
            .padding(10.dp)
            .shadow(
                elevation = 10.dp,
                shape = shape,
                clip = true,
            )
            .background(
                Brush.linearGradient(
                    colors = colors()
                        .shuffled()
                        .subList(0, 3),
                    start = Offset.Zero,
                    end = Offset.Infinite
                ),
                shape = shape
            )
            .clip(shape)
            .height(350.dp)
            .width(400.dp)
    ) {
        Column (
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = genre.title.titleCase(),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 30.sp
                ),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(10.dp)
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(
                        horizontal = 25.dp,
                        vertical = 25.dp
                    )
            ){
                Text(
                    text = genre.overview.titleCase(),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold
                    ),
                )
                Text(
                    text = genre.description.uppercase(),
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.outline
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun GenreCardPreview() {
    val profileScreenViewModelMock = ProfileScreenViewModelMock()
    val pagerState = rememberPagerState (initialPage = 0) { profileScreenViewModelMock.genres.size }
    KalleryTheme {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            GenresPreview(
                pagerState = pagerState,
                genres = profileScreenViewModelMock.genres
            )
        }
    }
}