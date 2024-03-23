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
import com.merabk.moviesapplicationtm.presentation.DataState
import com.merabk.moviesapplicationtm.presentation.adapter.RvAdapter
import com.merabk.moviesapplicationtm.presentation.vm.MainPageViewModel
import com.merabk.moviesapplicationtm.util.collectFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), RvAdapter.MovieItemClickListener {

    private val viewModel: MainPageViewModel by viewModels()
    private var moviesAdapter: RvAdapter = RvAdapter(this)
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectDataState()
    }

    private fun collectDataState() {
        collectFlow(viewModel.dataState) { dataState ->
            when (dataState) {
                is DataState.Loading -> {
                }

                is DataState.Success -> {
                    val data = dataState.data
                    moviesAdapter.submitList(data)
                    binding.filmsRv.adapter = moviesAdapter
                    Log.d("SHECHEMAAA", "onCreate: $data")

                }

                is DataState.Error -> {
                    val errorMessage = dataState.message
                    Log.d("SHECHEMAAA", "onCreate: $errorMessage")

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