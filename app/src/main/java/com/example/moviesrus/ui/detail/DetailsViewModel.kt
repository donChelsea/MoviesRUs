package com.example.moviesrus.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesrus.domain.models.Movie
import com.example.moviesrus.domain.repository.MovieRepository
import com.example.moviesrus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    private val _movie: MutableStateFlow<DetailsUiState> = MutableStateFlow(DetailsUiState())
    val movie: StateFlow<DetailsUiState> = _movie

    fun updateMovie(movieId: Int) {
        viewModelScope.launch {
            repository.getMovie(movieId).collect { result ->
                when (result) {
                    is Resource.Success -> _movie.update { it.copy(data = result.data) }
                    is Resource.Error -> println("DetailsViewModel: ${result.message.toString()}")
                    is Resource.Loading -> println("DetailsViewModel: ${result.data.toString()}")
                }
            }
        }
    }

}

data class DetailsUiState(
    val data: Movie? = null,
    val isLoading: Boolean? = null,
)