package com.merabk.moviesapplicationtm.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merabk.moviesapplicationtm.domain.model.MainContentDomainModel
import com.merabk.moviesapplicationtm.domain.usecase.GetAllMoviesUseCase
import com.merabk.moviesapplicationtm.domain.usecase.SearchMoviesUseCase
import com.merabk.moviesapplicationtm.presentation.DataState
import com.merabk.moviesapplicationtm.util.Dispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val dispatchers: Dispatchers
) :
    ViewModel() {

    private val _allMoviesData
            : MutableSharedFlow<DataState<List<MainContentDomainModel>>> =
        MutableSharedFlow(
            replay = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST,
        )
    val allMoviesData = _allMoviesData.asSharedFlow()


    private val _searchMoviesData: MutableStateFlow<DataState<List<MainContentDomainModel>>> =
        MutableStateFlow(DataState.Loading)
    val searchMoviesData = _searchMoviesData.asStateFlow()

    init {
        getAllMovies()
    }

    private fun getAllMovies() {
        dispatchers.launchBackground(viewModelScope) {
            val result = getAllMoviesUseCase()
            result.onSuccess { data ->
                _allMoviesData.tryEmit(DataState.Success(data))
            }
            result.onFailure {
                _allMoviesData.tryEmit(DataState.Error(it.message ?: "An error occurred"))
            }
        }
    }

    fun searchTv(query: String?) {
        query?.ifEmpty {
            getAllMovies()
            return
        }
        dispatchers.launchBackground(viewModelScope) {
            val result = searchMoviesUseCase(query.orEmpty())
            result.onSuccess { data ->
                _searchMoviesData.emit(DataState.Success(data))
            }
            result.onFailure {
                _searchMoviesData.emit(DataState.Error(it.message ?: "An error occurred"))
            }
        }
    }
}