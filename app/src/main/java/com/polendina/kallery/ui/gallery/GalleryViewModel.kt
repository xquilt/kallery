package com.polendina.kallery.ui.gallery

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.polendina.kallery.data.repository.GalleryRepository
import com.polendina.kallery.data.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

interface GalleryViewModel {
    var searchQuery: StateFlow<String>
    val photos: StateFlow<List<Photo>>
    fun onSearch(searchQuery: String): Unit
    fun onQueryChange(newQuery: String): Unit
}

@OptIn(FlowPreview::class)
@HiltViewModel
class GalleryViewModelImpl @Inject constructor(
    private val photoRepository: GalleryRepository
): ViewModel(), GalleryViewModel {
    private var _searchQuery: MutableStateFlow<String> = MutableStateFlow("")
    override var searchQuery = _searchQuery.asStateFlow()
    private val _photos: MutableStateFlow<SnapshotStateList<Photo>> = MutableStateFlow(mutableStateListOf())
    override val photos: StateFlow<SnapshotStateList<Photo>> = _searchQuery
        .debounce(500)
        .distinctUntilChanged()
        .flowOn(Dispatchers.IO)
        .combine(_photos) {query, photos ->
            if (query.isBlank()) {
                _photos.value
            } else {
                photos.filter {
                    it.title.contains(_searchQuery.value)
                }.toMutableStateList()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(500),
            initialValue = _photos.value
        )
    override fun onQueryChange(newQuery: String) {
        _searchQuery.update { newQuery }
    }
    override fun onSearch(searchQuery: String) {
        _searchQuery.value = searchQuery
    }
    init {
        viewModelScope.launch(Dispatchers.IO) {
            photoRepository.albumPhotos()?.let {
                _photos.value.addAll(it)
            }
        }
    }
}

class GalleryViewModelMock : ViewModel(), GalleryViewModel {
    private var _searchQuery = MutableStateFlow("")
    private val _photos = (2..10).map {
        Photo(
            albumId = it,
            id = it,
            title = it.toString(),
            url = "http://127.0.0.1:8080/gameCharacter.png",
            thumbnailUrl = it.toString()
        )
    }
    override var searchQuery: StateFlow<String>
        get() = _searchQuery
        set(value) {}

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    override val photos: StateFlow<List<Photo>> = _searchQuery
        .debounce(5000)
        .distinctUntilChanged()
        .flatMapLatest { flow { emit(_photos) } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(500),
            emptyList()
        )

    override fun onQueryChange(newQuery: String) {
        _searchQuery.update { newQuery }
    }
    override fun onSearch(searchQuery: String) {
        photos
            .value
            .filter { it.title == _searchQuery.value }
    }
}