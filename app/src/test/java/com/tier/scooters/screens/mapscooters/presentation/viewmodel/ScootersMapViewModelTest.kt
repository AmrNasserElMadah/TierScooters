package com.tier.scooters.screens.mapscooters.presentation.viewmodel

import com.tier.scooters.base.data.fake.DataGenerator
import com.tier.scooters.base.data.mapper.ScooterMapper
import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.base.data.remote.network.response.NetworkResponse
import com.tier.scooters.base.data.remote.network.response.error.GenericError
import com.tier.scooters.screens.mapscooters.domain.interactor.remote.LoadScootersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class ScootersMapViewModelTest {

    @Mock
    private lateinit var loadScootersUseCase: LoadScootersUseCase

    private lateinit var scootersMapViewModel: ScootersMapViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        scootersMapViewModel = ScootersMapViewModel(loadScootersUseCase, ScooterMapper())
    }

    @Test
    fun `when the response success and returns empty list`() {
        runBlockingTest {
            //Given
            val response = NetworkResponse.Success(emptyList<Scooter>())
            val channel = Channel<NetworkResponse<List<Scooter>, GenericError>>()
            val flow = channel.consumeAsFlow()

            //When
            Mockito.`when`(loadScootersUseCase.build()).thenReturn(flow)
            launch {
                channel.send(response)
            }

            //Then
            scootersMapViewModel.loadScooters()
            assertTrue(scootersMapViewModel.response.value.data?.isEmpty() == true)
        }
    }

    @Test
    fun `when the response api error with 403 error code`() {
        runBlockingTest {
            //Given
            val response = NetworkResponse.ApiBodyError(GenericError("Test Error", true), 403)
            val channel = Channel<NetworkResponse<List<Scooter>, GenericError>>()
            val flow = channel.consumeAsFlow()

            //When
            Mockito.`when`(loadScootersUseCase.build()).thenReturn(flow)
            launch {
                channel.send(response)
            }

            //Then
            scootersMapViewModel.loadScooters()
            assertTrue(scootersMapViewModel.response.value.error is NetworkResponse.ApiBodyError)
            assertTrue((scootersMapViewModel.response.value.error as NetworkResponse.ApiBodyError).code == 403)
        }
    }

    @Test
    fun `when the response success with data not empty`() {
        runBlockingTest {
            //Given
            val response = NetworkResponse.Success(DataGenerator.getFakeScootersList())
            val channel = Channel<NetworkResponse<List<Scooter>, GenericError>>()
            val flow = channel.consumeAsFlow()

            //When
            Mockito.`when`(loadScootersUseCase.build()).thenReturn(flow)
            launch {
                channel.send(response)
            }

            //Then
            scootersMapViewModel.loadScooters()
            assertTrue(scootersMapViewModel.response.value.data?.isNotEmpty() == true)
            assertTrue(
                scootersMapViewModel.response.value.data?.get(0)?.title ==
                        response.body?.get(
                            0
                        )?.displayName
            )
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}