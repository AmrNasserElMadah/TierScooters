package com.tier.scooters.screens.main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.tier.scooters.base.presentation.view.activity.BaseActivity
import com.tier.scooters.base.presentation.view.screens.Screen
import com.tier.scooters.screens.mapscooters.presentation.view.screen.ScootersMapScreen
import com.tier.scooters.screens.mapscooters.presentation.viewmodel.ScootersMapViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    activity: BaseActivity,
    scootersMapViewModel: ScootersMapViewModel
) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.ScootersMapScreen.route
    ) {
        appAnimatedComposable(
            route = Screen.ScootersMapScreen.route,
            isPopEnterTransitionExist = false,
            isPopExitTransitionExist = false
        ) {
            ScootersMapScreen(
                activity = activity,
                scootersMapViewModel = scootersMapViewModel,
            )
        }
    }
}