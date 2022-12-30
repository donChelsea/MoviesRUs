package com.example.moviesrus.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.moviesrus.R
import com.example.moviesrus.databinding.FragmentDetailsBinding
import com.example.moviesrus.domain.models.Video
import com.example.moviesrus.util.MOVIE_IMAGE_URL
import com.example.moviesrus.util.YOUTUBE_API_KEY
import com.google.android.youtube.player.YouTubeStandalonePlayer
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel by viewModels<DetailsViewModel>()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        viewModel.updateMovie(args.movieId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleScope.launchWhenStarted {
                viewModel.movie.collect { state ->
                    state.movie?.let { movie ->
                        textviewTitle.text = movie.title
                        textviewYear.text = movie.releaseDate
                        textviewRuntime.text = movie.runtime.toString()
                        textviewOverviewText.text = movie.overview
                        textviewProduction.text = getString(R.string.production, movie.production?.joinToString(", ") { it.name })
                        textviewLanguages.text = resources.getQuantityString(R.plurals.languages, movie.languages.orEmpty().size, movie.languages?.joinToString(", ") { it.name })
                        textviewGenres.text = movie.genres?.joinToString(", ") { it.name }
                        Picasso.get().load(MOVIE_IMAGE_URL + movie.posterPath).into(imageviewBackdrop)
                        buttonPlay.setOnClickListener {
                            playVideo(state.video)
                        }
                    }
                }
            }
        }
    }

    private fun playVideo(video: Video?) {
        val intent = YouTubeStandalonePlayer.createVideoIntent(
            requireActivity(),
            YOUTUBE_API_KEY,
            video?.key,
            0,
            true,
            false
        )
        startActivity(intent)
    }

}