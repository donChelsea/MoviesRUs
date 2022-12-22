package com.example.moviesrus.ui.discover

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.moviesrus.R
import com.example.moviesrus.databinding.FragmentDiscoverBinding
import com.example.moviesrus.domain.models.Genre
import com.example.moviesrus.ui.discover.discovered_list.DiscoveredListFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverFragment : Fragment() {
    private lateinit var binding: FragmentDiscoverBinding
    private val viewModel by viewModels<DiscoverViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiscoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.genres.collect { state ->
                val adapter = GenreAdapter(state.data) { onGenreClicked(it) }
                binding.recyclerViewGenre.adapter = adapter
            }
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) onMovieSearch(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun onGenreClicked(genre: Genre) {
        val action = DiscoverFragmentDirections.actionDiscoverFragmentToDiscoveredListFragment(genre = genre, query = null)
        findNavController().navigate(action)
    }

    private fun onMovieSearch(query: String) {
        val action = DiscoverFragmentDirections.actionDiscoverFragmentToDiscoveredListFragment(query = query, genre = null)
        findNavController().navigate(action)
    }

}