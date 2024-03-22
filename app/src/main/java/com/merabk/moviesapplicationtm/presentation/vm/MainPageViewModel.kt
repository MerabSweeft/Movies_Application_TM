package com.merabk.moviesapplicationtm.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merabk.moviesapplicationtm.domain.model.ContentDomainModel
import com.merabk.moviesapplicationtm.domain.usecase.GetAllMoviesUseCase
import com.merabk.moviesapplicationtm.presentation.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainPageViewModel @Inject constructor(private val getAllMoviesUseCase: GetAllMoviesUseCase) :
    ViewModel() {

    private val _dataState: MutableStateFlow<DataState<List<ContentDomainModel>>> =
        MutableStateFlow(DataState.Loading)
    val dataState: StateFlow<DataState<List<ContentDomainModel>>> = _dataState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val result = getAllMoviesUseCase.invoke()
            result.onSuccess {
                _dataState.emit(DataState.Success(it))
            }
            result.onFailure {
                _dataState.value = DataState.Error(it.message ?: "An error occurred")
                Log.d("SHECHEMAAA", "onCreate: ${it.stackTrace}")
            }
        }
    }
}