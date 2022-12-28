package com.example.moviesrus.ui.discover

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesrus.domain.models.Genre
import com.example.moviesrus.domain.repository.MovieRepository
import com.example.moviesrus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _genres: MutableStateFlow<GenreUiState> = MutableStateFlow(GenreUiState())
    val genres: StateFlow<GenreUiState> = _genres

    init {
        viewModelScope.launch {
            repository.getGenres().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { genres ->
                            _genres.update { it.copy(data = genres) }
                        }
                    }
                    is Resource.Error -> {
                        Log.d("DiscoverViewModel: Error", result.message.toString())
                    }
                    is Resource.Loading -> {
                        _genres.value = _genres.value.copy(isLoading = true)
                    }
                }
            }
        }
    }
}

data class GenreUiState(
    val data: List<Genre> = emptyList(),
    val isLoading: Boolean? = null,
)