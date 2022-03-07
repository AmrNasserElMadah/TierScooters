package com.tier.scooters.screens.mapscooters

import com.tier.scooters.screens.mapscooters.domain.interactor.remote.LoadScootersUseCaseTest
import com.tier.scooters.screens.mapscooters.presentation.viewmodel.ScootersMapViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    ScootersMapViewModelTest::class,
    LoadScootersUseCaseTest::class,
)
class TestsGroup