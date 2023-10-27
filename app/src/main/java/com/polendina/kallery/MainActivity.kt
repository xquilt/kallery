package com.polendina.kallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.polendina.kallery.ui.gallery.GalleryScreen
import com.polendina.kallery.ui.gallery.GalleryViewModelImpl
import com.polendina.kallery.ui.profile.ProfileScreen
import com.polendina.kallery.ui.profile.ProfileScreenViewModelImpl
import com.polendina.kallery.ui.theme.KalleryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    ProfileScreen(hiltViewModel<ProfileScreenViewModelImpl>())
                    GalleryScreen(hiltViewModel<GalleryViewModelImpl>())
                }
            }
        }
    }
}