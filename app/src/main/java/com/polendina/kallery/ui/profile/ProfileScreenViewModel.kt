package com.polendina.kallery.ui.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polendina.kallery.data.repository.GalleryRepository
import com.polendina.kallery.data.model.Album
import com.polendina.kallery.domain.models.Genre
import com.polendina.kallery.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

interface ProfileScreenViewModel {
   var currentUser: User?
   var albums: List<Album>
   val genres: List<Genre>
//   val photos: List<Photo>
   var userProfileVisible: MutableState<Boolean>
   fun toggleUserProfileScreen(): Unit
}

@HiltViewModel
class ProfileScreenViewModelImpl @Inject constructor(
    private val photoService: GalleryRepository
): ViewModel(), ProfileScreenViewModel {
    private var _currentUser: MutableState<User>? = null
    private val _albums: SnapshotStateList<Album> = mutableStateListOf()
    private val _genres = mutableStateListOf<Genre>()
    private var _userProfileVisible = mutableStateOf(false)
    override var currentUser: User?
        get() = _currentUser?.value
        set(value) {}
    override var albums: List<Album>
        get() = _albums
        set(value) {}
    override val genres: List<Genre>
        get() = _genres
    override var userProfileVisible = _userProfileVisible
    override fun toggleUserProfileScreen() {
        _userProfileVisible.value = !_userProfileVisible.value
    }
    init {
        viewModelScope.launch(Dispatchers.IO) {
            photoService.getUsers()?.let {
                _currentUser = mutableStateOf(it.random())
            }
            photoService.userAlbums()?.let {
                _albums.addAll(it)
            }
            photoService.albumPhotos()?.let {
                _genres.addAll(it.map { Genre(title = it.title, overview = it.title, description = it.title) })
            }
        }
    }
}

class ProfileScreenViewModelMock: ViewModel(), ProfileScreenViewModel {
    private var _currentUser = mutableStateOf(
        User(
            id = 1,
            name = "Leanne Graham",
            username = "Bret",
            email = "Sincere@april.biz",
            address = User.Address(
                street = "Kulas Light",
                suite = "Apt. 556",
                city = "Gwenborough",
                zipcode = "92998-3874",
                geo = User.Address.Geo(
                    lat = "-37.3159",
                    lng = "81.1496"
                )
            ),
            phone = "1-770-736-8031 x56442",
            website = "hildegard.org",
            company = User.Company(
                name = "Romaguera-Crona",
                catchPhrase = "Multi-layered client-server neural-net",
                bs = "harness real-time e-markets"
            )
        )
    )
    override var currentUser: User? = _currentUser.value
    private val _words = listOf(
        "quidem", "molestiae", "enim", "sunt", "qui", "excepturi", "placeat", "culpa",
        "omnis", "laborum", "odio", "quibusdam", "autem", "aliquid", "et", "et", "quia"
    )
    private val _genres = mutableStateListOf(
        Genre(title = "new album mix", overview = "now album mix", description = "album for meng"),
        Genre(title = "favorite album mix", overview = "favorite album mix", description = "album for meng"),
        Genre(title = "trendy album mix", overview = "trendy album mix", description = "album for meng"),
        Genre(title = "classic album mix", overview = "classic album mix", description = "album for meng"),
        Genre(title = "pop album mix", overview = "pop album mix", description = "album for meng")
    )
    private var _albums = (0..10).map {
        Album(
            userId = Random.nextInt(10),
            id = Random.nextInt(10),
            title = _words.shuffled().subList(0, 6).joinToString(" "),
            favorite = false
        )
    }.toMutableStateList()
    override var albums: List<Album> = _albums
    override val genres = _genres
    private var _userProfileVisible = mutableStateOf(false)
    override var userProfileVisible = _userProfileVisible
    override fun toggleUserProfileScreen() {
        _userProfileVisible.value = !_userProfileVisible.value
    }
}