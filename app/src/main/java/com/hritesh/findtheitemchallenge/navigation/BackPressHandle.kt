package com.hritesh.findtheitemchallenge.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.hritesh.findtheitemchallenge.viewmodel.InputViewModel

@Composable
fun HandleBackPress(navController: NavController, inputViewModel: InputViewModel) {

BackHandler {
    inputViewModel.resetScore()
    navController.popBackStack(Screen.Home.route, inclusive = false)


}


}