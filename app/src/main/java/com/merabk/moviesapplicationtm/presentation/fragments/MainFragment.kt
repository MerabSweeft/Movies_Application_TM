package com.merabk.moviesapplicationtm.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.merabk.moviesapplicationtm.R
import com.merabk.moviesapplicationtm.databinding.FragmentMainBinding
import com.merabk.moviesapplicationtm.presentation.DataState
import com.merabk.moviesapplicationtm.presentation.adapter.RvAdapter
import com.merabk.moviesapplicationtm.presentation.vm.MainPageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainPageViewModel by activityViewModels()
    private var moviesAdapter: RvAdapter = RvAdapter {
        findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
    }
    private var _binding: FragmentMainBinding? = null
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
}