package com.merabk.moviesapplicationtm.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.merabk.moviesapplicationtm.databinding.FragmentMainBinding
import com.merabk.moviesapplicationtm.domain.model.MainContentDomainModel
import com.merabk.moviesapplicationtm.presentation.DataState
import com.merabk.moviesapplicationtm.presentation.adapter.RvAdapter
import com.merabk.moviesapplicationtm.presentation.vm.MainPageViewModel
import com.merabk.moviesapplicationtm.util.DebouncingQueryTextListener
import com.merabk.moviesapplicationtm.util.collectFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class MainFragment : Fragment(), RvAdapter.MovieItemClickListener {

    private val viewModel: MainPageViewModel by viewModels()
    private var moviesAdapter: RvAdapter = RvAdapter(this)
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val debouncingQueryTextListener by lazy {
        DebouncingQueryTextListener(
            lifecycle = this@MainFragment.lifecycle,
            onDebouncingQueryTextChange = viewModel::searchTv
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        collectData()
    }

    private fun setListeners() = with(binding) {
        searchView.setOnQueryTextListener(debouncingQueryTextListener)
    }

    private fun collectData() = with(viewModel) {
        collectAllMoviesData(allMoviesData)
        collectSearchMoviesData(searchMoviesData)
    }

    private fun collectSearchMoviesData(searchMoviesData: StateFlow<DataState<List<MainContentDomainModel>>>) {
        collectFlow(searchMoviesData) { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                }

                is DataState.Success -> {
                    val data = dataState.data
                    moviesAdapter.submitList(data)
                    binding.filmsRv.adapter = moviesAdapter

                }

                is DataState.Error -> {
                    val errorMessage = dataState.message
                    Log.d("errorMessage", "onCreate: $errorMessage")

                }
            }
        }
    }

    private fun collectAllMoviesData(allMoviesData: SharedFlow<DataState<List<MainContentDomainModel>>>) {
        collectFlow(allMoviesData) { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                }

                is DataState.Success -> {
                    val data = dataState.data
                    moviesAdapter.submitList(data)
                    binding.filmsRv.adapter = moviesAdapter
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
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieItemClicked(movieId: Int) {
        val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(movieId)
        findNavController().navigate(action)
    }
}