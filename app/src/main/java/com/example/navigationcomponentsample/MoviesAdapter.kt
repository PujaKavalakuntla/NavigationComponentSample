package com.example.navigationcomponentsample

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.navigationcomponentsample.databinding.MoviesListItemBinding
import com.example.navigationcomponentsample.model.Movies
import com.example.navigationcomponentsample.ui.MoviesDetailedActivity


class MoviesAdapter : RecyclerView.Adapter<MoviesViewHolder>() {

    var movies = mutableListOf<Movies>()
    fun setMovieList(movies: List<Movies>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = MoviesListItemBinding.inflate(inflater, parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.tvMovieName.text = movie.name
        holder.binding.tvMovieCategory.text = movie.category
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.ivMovieImg)

        holder.itemView.setOnClickListener {

            val intent = Intent(holder.itemView.context, MoviesDetailedActivity::class.java)
            intent.putExtra("name", movie.name)
            intent.putExtra("category",movie.category)
            intent.putExtra("desc",movie.desc)
            intent.putExtra("imageUrl",movie.imageUrl)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

}

class MoviesViewHolder(val binding: MoviesListItemBinding) : RecyclerView.ViewHolder(binding.root)
