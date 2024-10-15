package com.hritesh.findtheitemchallenge.presentation.screens.optionsScreen

import com.hritesh.findtheitemchallenge.R

data class LazyColumnItemsData(val imageRes: Int, val title: String)

fun getOptionsItem():List<LazyColumnItemsData> {
   return listOf(
            LazyColumnItemsData(R.drawable.food, "Food"),
            LazyColumnItemsData(R.drawable.flowers, "Flowers"),
            LazyColumnItemsData(R.drawable.home, "Home"),
            LazyColumnItemsData(R.drawable.location, "Location"),
            LazyColumnItemsData(R.drawable.outdoor, "Outdoor"),
            LazyColumnItemsData(R.drawable.pets, "Pets"),
            LazyColumnItemsData(R.drawable.vehicle, "Vehicle"),
            LazyColumnItemsData(R.drawable.random, "Others")
        )

}




