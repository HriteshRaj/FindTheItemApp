package com.hritesh.findtheitemchallenge.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController

import com.hritesh.findtheitemchallenge.presentation.screens.verifyPhoto.PhotoVerificationSuccess
import com.hritesh.findtheitemchallenge.presentation.screens.backgroundload.BackGroundLoadingScreen
import com.hritesh.findtheitemchallenge.presentation.screens.completed.ChallengeCompletedScreen
import com.hritesh.findtheitemchallenge.presentation.screens.homeScreen.HomeScreen
import com.hritesh.findtheitemchallenge.presentation.screens.onBoarding.OnBoardingPager
import com.hritesh.findtheitemchallenge.presentation.screens.optionsScreen.ChooseOption
import com.hritesh.findtheitemchallenge.presentation.screens.photoupload.PhotoUpload
import com.hritesh.findtheitemchallenge.presentation.screens.verifyPhoto.PhotoVerificationFailure
import com.hritesh.findtheitemchallenge.presentation.screens.verifyPhoto.PhotoVerificationPending
import com.hritesh.findtheitemchallenge.viewmodel.InputViewModel
import com.hritesh.findtheitemchallenge.viewmodel.PredictionViewModel

@Composable
fun NavGraph(
    modifier: Modifier,
    inputViewModel: InputViewModel,
    predictionViewModel: PredictionViewModel
) {


    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.OnBoarding.route,
        enterTransition = { fadeIn(animationSpec = tween(2000)) }) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.OnBoarding.route) {
            OnBoardingPager(navController)
        }
        composable(Screen.ChooseOption.route) { ChooseOption(navController, inputViewModel) }
        composable(Screen.BackGroundLoad.route) {
            BackGroundLoadingScreen(
                navController,
                inputViewModel
            )
        }

        composable(Screen.PhotoUpload.route) {
            PhotoUpload(
                navController,
                inputViewModel,
                predictionViewModel
            )
        }
        composable(Screen.PhotoVerificationSuccess.route) {
            PhotoVerificationSuccess(
                navController,
                inputViewModel,
                predictionViewModel
            )
        }
        composable(Screen.PhotoVerificationPending.route) {
            PhotoVerificationPending(
                navController,
                inputViewModel,
                predictionViewModel
            )
        }
        composable(Screen.PhotoVerificationFailure.route) {
            PhotoVerificationFailure(
                navController,
                inputViewModel,
                predictionViewModel
            )
        }
        composable(Screen.CompletedChallenge.route) {
            ChallengeCompletedScreen(
                navController,
                inputViewModel,
                predictionViewModel
            )
        }


    }


}

sealed class Screen(val route: String) {

    data object Home : Screen("Home")
    data object OnBoarding : Screen("OnBoarding")
    data object ChooseOption : Screen("ChooseOption")
    data object BackGroundLoad : Screen("BackGroundLoad")

    data object PhotoUpload : Screen("PhotoUpload")
    data object PhotoVerificationPending : Screen("PhotoVerificationPending")
    data object PhotoVerificationSuccess : Screen("PhotoVerificationSuccess")
    data object PhotoVerificationFailure : Screen("PhotoVerificationFailure")
    data object CompletedChallenge : Screen("ChallengeCompleted")


}
