package com.example.a020mycity.data

import androidx.annotation.StringRes

data class Recommendation(
    val id: Long,
    val category: Category,
    @StringRes val name: Int,
    @StringRes val details: Int,
    @StringRes val location: Int
)
