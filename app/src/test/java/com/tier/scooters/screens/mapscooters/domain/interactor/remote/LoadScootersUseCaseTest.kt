package com.tier.scooters.screens.mapscooters.domain.interactor.remote

import com.tier.scooters.base.data.fake.DataGenerator
import com.tier.scooters.base.data.remote.model.Scooter
import com.tier.scooters.base.data.remote.network.response.NetworkResponse
import com.tier.scooters.base.data.remote.network.response.error.GenericError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class LoadScootersUseCaseTest {

    @Mock
    private lateinit var loadScootersUseCase: LoadScootersUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `when the response success and returns empty list`() {
        runBlockingTest {
            //Given
            val response = NetworkResponse.Success(emptyList<Scooter>())
            val channel = Channel<NetworkResponse<List<Scooter>, GenericError>>()
            val flow = channel.consumeAsFlow()

            //When
            `when`(loadScootersUseCase.build()).thenReturn(flow)
            launch {
                channel.send(response)
            }

            //Then
            val responseLiveData = loadScootersUseCase.build().first()
            Assert.assertTrue(responseLiveData is NetworkResponse.Success)
            Assert.assertTrue((responseLiveData as NetworkResponse.Success).body?.isEmpty() == true)
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
            `when`(loadScootersUseCase.build()).thenReturn(flow)
            launch {
                channel.send(response)
            }

            //Then
            val responseLiveData = loadScootersUseCase.build().first()
            Assert.assertTrue(responseLiveData is NetworkResponse.ApiBodyError)
            Assert.assertTrue((responseLiveData as NetworkResponse.ApiBodyError).code == 403)
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
            `when`(loadScootersUseCase.build()).thenReturn(flow)
            launch {
                channel.send(response)
            }

            //Then
            val responseLiveData = loadScootersUseCase.build().first()
            Assert.assertTrue(responseLiveData is NetworkResponse.Success)
            Assert.assertTrue((responseLiveData as NetworkResponse.Success).body?.isNotEmpty() == true)
            Assert.assertTrue(
                responseLiveData.body?.get(0)?.displayName == response.body?.get(
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