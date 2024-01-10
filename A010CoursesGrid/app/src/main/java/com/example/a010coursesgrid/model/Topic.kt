package com.example.a010coursesgrid.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val name: Int,
    val associatedCourses: Int,
    @DrawableRes val image: Int
)
