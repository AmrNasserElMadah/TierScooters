package com.tier.scooters.screens.mapscooters.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.tier.scooters.base.data.mapper.ScooterMapper
import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.base.data.remote.network.response.NetworkResponse
import com.tier.scooters.base.data.remote.util.Response
import com.tier.scooters.base.presentation.viewmodel.BaseViewModel
import com.tier.scooters.screens.mapscooters.data.local.ScooterClusterItem
import com.tier.scooters.screens.mapscooters.domain.interactor.remote.LoadScootersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScootersMapViewModel @Inject constructor(
    private val loadScootersUseCase: LoadScootersUseCase,
    private val mapper: ScooterMapper
) : BaseViewModel() {
    private val _response =
        mutableStateOf<Response<List<ScooterClusterItem>>>(Response.Loading(true))
    val response: State<Response<List<ScooterClusterItem>>> = _response

    private val _selectedScooterMarker =
        mutableStateOf(Scooter())
    val selectedScooterMarker: State<Scooter> = _selectedScooterMarker

    init {
        loadScooters()
    }

    fun loadScooters() {
        initializeJob(viewModelScope.launch {
            loadScootersUseCase.build()
                .onStart {
                    _response.value = Response.Loading(true)
                }
                .catch {
                    _response.value = Response.Error()
                }
                .collect {
                    when (it) {
                        is NetworkResponse.Success -> {
                            it.body?.let { scooters ->
                                _response.value = Response.Success(scooters.results.map { scooter ->
                                    mapper.to(scooter)
                                })
                            }
                        }
                        else -> {
                            simulateList()
//                            _response.value = Response.Error(it)
                        }
                    }
                }
        })
    }

    fun setSelectedScooterMarker(scooter: Scooter) {
        _selectedScooterMarker.value = scooter
    }

    fun convertToScooterModel(scooterClusterItem: ScooterClusterItem) =
        mapper.from(scooterClusterItem)

    //Todo delete this function when the api is working fine
    private fun simulateList() {
        val scootersList = arrayListOf<Scooter>()
        for (i in 0..10) {
            scootersList.add(
                Scooter("", "Test$i", 30.0 + i, 30.0 + i)
            )
        }
        _response.value =
            Response.Success(scootersList.map { scooter -> mapper.to(scooter) })
//
    }
}