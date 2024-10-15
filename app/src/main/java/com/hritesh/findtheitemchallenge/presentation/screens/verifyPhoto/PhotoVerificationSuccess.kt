package com.hritesh.findtheitemchallenge.presentation.screens.verifyPhoto

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
fun PhotoVerificationSuccess(navController: NavController,inputViewModel: InputViewModel,predictionViewModel: PredictionViewModel) {



    val reward= 50
    val itemCount = inputViewModel.itemsLeft.collectAsState()
    HandleBackPress(navController = navController, inputViewModel =inputViewModel )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "You found it!",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "+$reward",
            style = MaterialTheme.typography.displayMedium.copy(
                color = Color.Magenta,
                fontWeight = FontWeight.W800)
        )


            CommonButtons(text = "Next Item") {
                inputViewModel.setScore(reward)
                inputViewModel.pickNextItem()
                predictionViewModel.resetPredctedName()
                navController.navigate(Screen.PhotoUpload.route)




            }
        }
        Spacer(modifier = Modifier.height(35.dp))
        val score = inputViewModel.score.collectAsState()
        Log.d("In photo success","${score.value}")

    }


