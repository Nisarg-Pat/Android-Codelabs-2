package com.example.a020mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.a020mycity.R

enum class Category(@StringRes val nameId: Int, @DrawableRes val backgroundImage: Int) {
    COFFEE_SHOP(R.string.category_coffee_shop, R.drawable.coffee),
    RESTAURANT(R.string.category_restaurant, R.drawable.restaurant),
    PARK(R.string.category_park, R.drawable.park),
    SHOPPING_CENTER(R.string.category_shopping_center, R.drawable.shopping)
}