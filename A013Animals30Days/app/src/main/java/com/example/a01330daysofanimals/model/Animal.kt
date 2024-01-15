package com.example.a01330daysofanimals.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Animal(
    val id: Int,
    @StringRes val animalName: Int,
    @DrawableRes val animalImage: Int,
    @StringRes val animalDescription: Int
)
