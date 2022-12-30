package com.example.moviesrus.ui.discover.discovered_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviesrus.databinding.ListItemDiscoveredMovieBinding
import com.example.moviesrus.domain.models.Movie
import com.example.moviesrus.util.MOVIE_IMAGE_URL
import com.squareup.picasso.Picasso

class DiscoveredAdapter(
    private val movies: List<Movie>,
    private val clickListener: (Movie) -> Unit
) : Adapter<DiscoveredAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: ListItemDiscoveredMovieBinding) :
        ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                textviewTitle.text = movie.title
                textviewReleaseDate.text = movie.releaseDate.substring(0, 4)
                Picasso.get().load(MOVIE_IMAGE_URL + (movie.backdropPath ?: "/zuW6fOiusv4X9nnW3paHGfXcSll.jpg")).into(imageviewBackdrop)
            }
            itemView.setOnClickListener {
                clickListener(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ListItemDiscoveredMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}