package com.tier.scooters.base.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {
    private var currentJob: Job? = null

    fun initializeJob(job: Job) {
        currentJob = job
    }

    override fun onCleared() {
        super.onCleared()
        currentJob?.cancel()
        if (currentJob?.isActive == true) {
            currentJob?.cancel()
        }
    }
}
