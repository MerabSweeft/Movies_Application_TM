package com.merabk.moviesapplicationtm.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merabk.moviesapplicationtm.domain.model.DetailsContentDomainModel
import com.merabk.moviesapplicationtm.domain.usecase.GetMoviesDetailsUseCase
import com.merabk.moviesapplicationtm.presentation.DataState
import com.merabk.moviesapplicationtm.util.Dispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class DetailsPageViewModel @Inject constructor(
    private val getMoviesDetailsUseCase: GetMoviesDetailsUseCase,
    private val dispatchers: Dispatchers
) :
    ViewModel() {


    private val _dataState: MutableStateFlow<DataState<DetailsContentDomainModel>> =
        MutableStateFlow(DataState.Loading)
    val dataState: StateFlow<DataState<DetailsContentDomainModel>> = _dataState

    fun getMovieDetails(id: Int) {
        dispatchers.launchBackground(viewModelScope) {
            val result = getMoviesDetailsUseCase(id)
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