package com.example.moviesrus.ui.discover

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesrus.R
import com.example.moviesrus.databinding.FragmentDiscoverBinding
import com.example.moviesrus.domain.models.Genre
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
    }

    private fun onGenreClicked(genre: Genre) {
        val action = DiscoverFragmentDirections.actionDiscoverFragmentToDiscoveredListFragment(genre)
        findNavController().navigate(action)
    }

}