package com.merabk.moviesapplicationtm.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.merabk.moviesapplicationtm.databinding.FragmentDetailsBinding
import com.merabk.moviesapplicationtm.presentation.DataState
import com.merabk.moviesapplicationtm.presentation.vm.DetailsPageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private val viewModel: DetailsPageViewModel by viewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectDataState()
    }

    private fun collectDataState() {
        lifecycleScope.launch {
            viewModel.dataState.collectLatest { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                    }

                    is DataState.Success -> {
                        val data = dataState.data
//                        moviesAdapter.submitList(data)
//                        binding.filmsRv.adapter = moviesAdapter
                        Log.d("SHECHEMAAA", "onCreate: $data")

                    }

                    is DataState.Error -> {
                        val errorMessage = dataState.message
                        Log.d("SHECHEMAAA", "onCreate: $errorMessage")

                    }
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