package com.merabk.moviesapplicationtm.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merabk.moviesapplicationtm.domain.model.MainContentDomainModel
import com.merabk.moviesapplicationtm.domain.usecase.GetAllMoviesUseCase
import com.merabk.moviesapplicationtm.presentation.DataState
import com.merabk.moviesapplicationtm.util.Dispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val dispatchers: Dispatchers
) :
    ViewModel() {

    private val _dataState: MutableStateFlow<DataState<List<MainContentDomainModel>>> =
        MutableStateFlow(DataState.Loading)
    val dataState: StateFlow<DataState<List<MainContentDomainModel>>> = _dataState

    private val _filmId: MutableStateFlow<Int> =
        MutableStateFlow(0)
    val filmId: StateFlow<Int> = _filmId

    init {
        fetchData()
    }

    private fun fetchData() {
        dispatchers.launchBackground(viewModelScope) {
            val result = getAllMoviesUseCase()
            result.onSuccess {
                _dataState.emit(DataState.Success(it))
            }
            result.onFailure {
                _dataState.emit(DataState.Error(it.message ?: "An error occurred"))
                Log.d("SHECHEMAAA", "onCreate: ${it.stackTrace}")
            }
        }
    }
}