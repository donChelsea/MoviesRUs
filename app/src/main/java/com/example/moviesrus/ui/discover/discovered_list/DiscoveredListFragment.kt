package com.example.moviesrus.ui.discover.discovered_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.moviesrus.databinding.FragmentDiscoveredListBinding
import com.example.moviesrus.domain.models.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoveredListFragment : Fragment() {
    private lateinit var binding: FragmentDiscoveredListBinding
    private val viewModel by viewModels<DiscoveredListViewModel>()
    private val args: DiscoveredListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscoveredListBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (args.genre != null) {
            args.genre?.let {
                lifecycleScope.launchWhenStarted {
                    viewModel.getMoviesByGenre(it.id)
                }
                (activity as AppCompatActivity).supportActionBar?.title = it.name
            }
        } else {
            lifecycleScope.launchWhenStarted {
                viewModel.searchMovie(args.query.toString())
            }
            (activity as AppCompatActivity).supportActionBar?.title = args.query
        }



        lifecycleScope.launchWhenStarted {
            viewModel.movies.collect {
                val adapter = DiscoveredAdapter(it.data) { movie -> onMovieClicked(movie) }
                binding.recyclerviewMovies.adapter = adapter
            }
        }
    }

    private fun onMovieClicked(movie: Movie) {
        Log.d("DiscoveredListFragment: ", movie.toString())
    }

}