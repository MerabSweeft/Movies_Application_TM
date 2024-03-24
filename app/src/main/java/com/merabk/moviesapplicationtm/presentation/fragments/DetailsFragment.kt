package com.merabk.moviesapplicationtm.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.merabk.moviesapplicationtm.databinding.FragmentDetailsBinding
import com.merabk.moviesapplicationtm.presentation.DataState
import com.merabk.moviesapplicationtm.presentation.adapter.PhotosAdapter
import com.merabk.moviesapplicationtm.presentation.vm.DetailsPageViewModel
import com.merabk.moviesapplicationtm.util.ViewModelFactoryProvider.factoryViewModel
import com.merabk.moviesapplicationtm.util.collectFlow
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val args by navArgs<DetailsFragmentArgs>()

    @Inject
    lateinit var factory: DetailsPageViewModel.Factory

    private val viewModel by factoryViewModel {
        factory.create(args.movieId)
    }

    private var photosAdapter = PhotosAdapter()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectDataState()
    }

    @SuppressLint("SetTextI18n")
    private fun collectDataState() {
        collectFlow(viewModel.movieDetailsState) { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                }

                is DataState.Success -> {
                    val data = dataState.data
                    photosAdapter.submitList(data.productionCompanies)
                    binding.apply {
                        Glide.with(this@DetailsFragment)
                            .load(data.posterPath)
                            .into(movieImage)
                        movieTitle.text = ("${data.originalName} ${data.voteAverage}")
                        movieDescription.text = data.genres
                        movieReleaseDate.text = data.releaseDate
                        rvCompanyPhotos.adapter = photosAdapter
                    }

                }

                is DataState.Error -> {
                    val errorMessage = dataState.message
                    Log.d("errorMessage", "collectAllMoviesData: $errorMessage")
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}