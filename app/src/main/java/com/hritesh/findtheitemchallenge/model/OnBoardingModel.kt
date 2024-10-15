package com.hritesh.findtheitemchallenge.model

import androidx.annotation.DrawableRes

data class OnBoardingModel(

    val title: String,
    @DrawableRes val image: Int?,
    val description: String


)