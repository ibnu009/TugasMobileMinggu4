package com.ibnu.tugasmobileminggu4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.ibnu.tugasmobileminggu4.databinding.ItemMovieBinding
import com.ibnu.tugasmobileminggu4.network.Movie
import com.ibnu.tugasmobileminggu4.utils.ItemClickHandler

class MovieAdapter(
    private val listMovie: List<Movie>,
    private val itemClickHandler: ItemClickHandler
) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])

    }

    override fun getItemCount(): Int = listMovie.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvMovieName.text = movie.title
            binding.spinnerMovie.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    when (p2) {
                        0 -> {

                        }
                        1 -> {
                            itemClickHandler.onSpinnerClicked(movie.title, "hapus")
                        }
                        2 -> {
                            itemClickHandler.onSpinnerClicked(movie.title, "lupakan")
                        }
                        else -> {

                        }
                    }
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }

}