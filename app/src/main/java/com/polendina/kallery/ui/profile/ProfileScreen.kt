package com.polendina.kallery.ui.profile

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.polendina.kallery.ui.profile.components.GenresPreview
import com.polendina.kallery.ui.profile.components.UserAlbums
import com.polendina.kallery.ui.profile.components.UserProfileComposable
import com.polendina.kallery.ui.profile.components.UserWidget
import com.polendina.kallery.ui.theme.KalleryTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileScreen(
    profileScreenViewModel: ProfileScreenViewModel
) {
    Scaffold(
        topBar = {
            UserWidget(
                user = profileScreenViewModel.currentUser,
                onProfileClick = {
                    profileScreenViewModel.toggleUserProfileScreen()
                },
                modifier = Modifier
                    .padding(10.dp)
            )
        },
        bottomBar = {
            Row { }
        },
        modifier = Modifier
            .padding(10.dp)
    ) {
        val horizontalPagerState = rememberPagerState(initialPage = 0) { profileScreenViewModel.genres.size }
        Column (
            modifier = Modifier
                .padding(it)
        ) {
            GenresPreview(
                pagerState = horizontalPagerState,
                genres = profileScreenViewModel.genres
            )
            UserAlbums(
                albums = profileScreenViewModel.albums
            )
        }
    }
    if (profileScreenViewModel.userProfileVisible.value) {
        UserProfileComposable(
            profileScreenViewModel.currentUser
        ) {
            profileScreenViewModel.toggleUserProfileScreen()
        }
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ProfileScreenPreview() {
    KalleryTheme {
        ProfileScreen(
            profileScreenViewModel = ProfileScreenViewModelMock()
        )
    }
}