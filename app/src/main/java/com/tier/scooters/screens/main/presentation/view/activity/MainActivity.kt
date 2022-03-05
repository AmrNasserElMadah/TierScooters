package com.tier.scooters.screens.main.presentation.view.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tier.scooters.base.presentation.view.activity.BaseActivity
import com.tier.scooters.base.presentation.view.theme.TierScootersAppTheme
import com.tier.scooters.screens.main.navigation.Navigation
import com.tier.scooters.screens.mapscooters.presentation.viewmodel.ScootersMapViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val scootersMapViewModel: ScootersMapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TierScootersAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(
                        activity = this@MainActivity,
                        scootersMapViewModel = scootersMapViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TierScootersAppTheme {
        Greeting("Android")
    }
}