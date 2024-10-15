package com.hritesh.findtheitemchallenge.presentation.screens.verifyPhoto

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hritesh.findtheitemchallenge.navigation.HandleBackPress
import com.hritesh.findtheitemchallenge.navigation.Screen
import com.hritesh.findtheitemchallenge.presentation.screens.homeScreen.CommonButtons
import com.hritesh.findtheitemchallenge.viewmodel.InputViewModel
import com.hritesh.findtheitemchallenge.viewmodel.PredictionViewModel

@Composable
fun PhotoVerificationFailure(navController: NavController,inputViewModel: InputViewModel,predictionViewModel: PredictionViewModel) {

    val reward = 10
    val score = inputViewModel.score.collectAsState()
    HandleBackPress(navController = navController, inputViewModel =inputViewModel )

    LaunchedEffect(Unit) {
        inputViewModel.setScoreWhenWrong(reward)
        Log.d("In photo failure","${score}")
    }


    Column(modifier=Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
       Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {



           Text(
               text = "You are wrong !",
               style = MaterialTheme.typography.titleLarge
           )

           Spacer(modifier = Modifier.height(10.dp))
           Text(
               text = "-$reward",
               style = MaterialTheme.typography.displayMedium.copy(
                   color = Color.Green,
                   fontWeight = FontWeight.W800), color = Color.Red
           )
           Spacer(modifier = Modifier.height(8.dp))


           CommonButtons(text = "Retry") {
               navController.navigate(Screen.PhotoUpload.route)

           }








       }
    }









}