package com.tier.scooters.base.data.fake

import com.tier.scooters.base.data.remote.model.Scooter

class DataGenerator {
    companion object TestDataGenerator {
        fun getFakeScootersList(): ArrayList<Scooter> {
            val scootersList = arrayListOf<Scooter>()
            for (i in 0..10) {
                scootersList.add(
                    Scooter("", "Test$i", 30.0 + i, 30.0 + i)
                )
            }
            return scootersList
        }
    }
}