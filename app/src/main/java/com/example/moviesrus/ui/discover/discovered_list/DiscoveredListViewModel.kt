package com.example.moviesrus.ui.discover.discovered_list

import android.util.Log
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
class DiscoveredListViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies: MutableStateFlow<DiscoveredUiState> = MutableStateFlow(DiscoveredUiState())
    val movies: StateFlow<DiscoveredUiState> = _movies

    fun getMoviesByGenre(genreId: Int) {
        viewModelScope.launch {
            repository.getMoviesByGenre(genreId).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { movies ->
                            _movies.value = _movies.value.copy(data = movies)
                        }
                    }
                    is Resource.Error -> {
                        Log.d("DiscoveredListViewModel: Error: ", result.message.toString())
                    }
                    is Resource.Loading -> {
                        _movies.value = _movies.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

    fun searchMovie(query: String) {
        viewModelScope.launch {
            repository.searchMovie(query).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { movies ->
                            _movies.update { it.copy(data = movies) }
                        }
                    }
                    is Resource.Error -> {
                        Log.d("DiscoveredListViewModel: Error: ", result.message.toString())
                    }
                    is Resource.Loading -> {
                        _movies.value = _movies.value.copy(isLoading = true)
                    }
                }
            }
        }
    }

}

data class DiscoveredUiState(
    val data: List<Movie> = emptyList(),
    val isLoading: Boolean? = null,
)