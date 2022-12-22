package com.example.moviesrus.ui.discover.discovered_list

import androidx.lifecycle.ViewModel
import com.example.moviesrus.domain.models.Movie
import com.example.moviesrus.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DiscoveredListViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies: MutableStateFlow<MovieUiState> = MutableStateFlow(MovieUiState())
    val movies: StateFlow<MovieUiState> = _movies

    fun getMoviesByGenre(genreId: String) {

    }

}

data class MovieUiState(
    val data: List<Movie> = emptyList(),
    val isLoading: Boolean? = null,
)