package com.example.moviesrus.ui.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.moviesrus.databinding.ListItemGenreBinding
import com.example.moviesrus.domain.models.Genre

class GenreAdapter(
    private val genres: List<Genre>,
    private val clickListener: (Genre) -> Unit
): Adapter<GenreAdapter.GenreViewHolder>() {

    inner class GenreViewHolder(private val binding: ListItemGenreBinding): ViewHolder(binding.root) {
        fun bind(genre: Genre) {
            binding.textviewGenre.text = genre.name
            itemView.setOnClickListener {
                clickListener(genre)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = ListItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount() = genres.size
}