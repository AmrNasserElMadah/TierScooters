package com.tier.scooters.screens.mapscooters.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.base.data.remote.network.response.NetworkResponse
import com.tier.scooters.base.data.remote.util.Response
import com.tier.scooters.base.presentation.viewmodel.BaseViewModel
import com.tier.scooters.screens.mapscooters.domain.interactor.remote.LoadScootersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScootersMapViewModel @Inject constructor(
    private val loadScootersUseCase: LoadScootersUseCase
) : BaseViewModel() {
    private val _response = mutableStateOf<Response<List<Scooter>>>(Response.Loading(true))
    val response: State<Response<List<Scooter>>> = _response

    fun loadScooters() {
        initializeJob(viewModelScope.launch {
            loadScootersUseCase.build()
                .onStart {
                    _response.value = Response.Loading(true)
                }
                .catch {
                    _response.value = Response.UnknownError(it)
                }
                .onCompletion {
                    _response.value = Response.Loading(false)
                }
                .collect {
                    when (it) {
                        is NetworkResponse.Success -> {
                            it.body?.let { scooters ->
                                _response.value = Response.Success(scooters.results)
                            }
                        }
                        else -> {
                            _response.value = Response.KnownError(it)
                        }
                    }
                }
        })
    }

    fun clearData() {
        _response.value = Response.Loading(true)
    }
}