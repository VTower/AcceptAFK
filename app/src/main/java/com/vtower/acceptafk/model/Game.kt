package com.vtower.acceptafk.model

import androidx.annotation.DrawableRes

data class Game(
    @DrawableRes val gameImage: Int,
    val gameName: String,
)