package com.merabk.moviesapplicationtm.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merabk.moviesapplicationtm.domain.model.DetailsContentDomainModel
import com.merabk.moviesapplicationtm.domain.usecase.GetMoviesDetailsUseCase
import com.merabk.moviesapplicationtm.presentation.DataState
import com.merabk.moviesapplicationtm.util.Dispatchers
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class DetailsPageViewModel @AssistedInject constructor(
    private val getMoviesDetailsUseCase: GetMoviesDetailsUseCase,
    private val dispatchers: Dispatchers,
    @Assisted private val id: Int,
) :
    ViewModel() {

    init {
        getMovieDetails()
    }

    private val _movieDetailsState: MutableStateFlow<DataState<DetailsContentDomainModel>> =
        MutableStateFlow(DataState.Loading)
    val movieDetailsState = _movieDetailsState.asStateFlow()

    private fun getMovieDetails() {
        dispatchers.launchBackground(viewModelScope) {
            val result = getMoviesDetailsUseCase(id)
            result.onSuccess {
                _movieDetailsState.emit(DataState.Success(it))
            }
            result.onFailure {
                _movieDetailsState.value = DataState.Error(it.message ?: "An error occurred")
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: Int): DetailsPageViewModel
    }
}