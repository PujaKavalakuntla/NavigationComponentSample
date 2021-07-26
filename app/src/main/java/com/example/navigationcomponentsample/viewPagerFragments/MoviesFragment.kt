package com.example.navigationcomponentsample.viewPagerFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.navigationcomponentsample.*
import com.example.navigationcomponentsample.api.MoviesApi
import com.example.navigationcomponentsample.databinding.FragmentMoviesBinding


class MoviesFragment : Fragment() {
    lateinit var viewModel: MoviesViewModel
    private lateinit var binding: FragmentMoviesBinding
    private val retrofitService = MoviesApi.getInstance()
    private val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMoviesBinding.bind(view)
        viewModel =
            ViewModelProvider(this, MoviesViewModelFactory(MoviesRepository(retrofitService))).get(
                MoviesViewModel::class.java
            )
        binding.recyclerview.adapter = adapter
        viewModel.movieList.observe(requireActivity(), {
            adapter.setMovieList(it)
        })
        viewModel.errorMessage.observe(requireActivity(), {})
        viewModel.getAllMovies()
        val bundle = Bundle()
        bundle.putInt("Position",adapter.itemCount)

    }
}