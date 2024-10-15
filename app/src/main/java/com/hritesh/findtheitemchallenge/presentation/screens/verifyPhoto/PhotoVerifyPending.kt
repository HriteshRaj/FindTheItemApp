package com.hritesh.findtheitemchallenge.presentation.screens.verifyPhoto

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hritesh.findtheitemchallenge.navigation.HandleBackPress
import com.hritesh.findtheitemchallenge.navigation.Screen
import com.hritesh.findtheitemchallenge.viewmodel.InputViewModel
import com.hritesh.findtheitemchallenge.viewmodel.PredictionViewModel
import kotlinx.coroutines.delay

@Composable
fun PhotoVerificationPending(navController: NavController,inputViewModel: InputViewModel,predictionViewModel: PredictionViewModel) {





    val predictedName = predictionViewModel.predictedName.collectAsState()

    HandleBackPress(navController = navController, inputViewModel =inputViewModel )

    val actualItem = inputViewModel.choosenItem.collectAsState()

        LaunchedEffect(predictedName.value) {

            delay(2000)
            val formattedPredictedName = predictedName.value!!.trim().lowercase().replace(Regex("[^a-zA-Z0-9]"),"")
            val formattedActualItem = actualItem.value!!.trim().lowercase()
            Log.d("PhotoVerifyPending", "Predicted: $formattedPredictedName, Current Item: $formattedActualItem")
            if(formattedActualItem==formattedPredictedName){


                navController.navigate(Screen.PhotoVerificationSuccess.route)




                }else{
                navController.navigate(Screen.PhotoVerificationFailure.route)
                Log.d("ItemValidatingScreen", "Predicted name does not match the current item")


                }




            }


    Column(modifier = Modifier.fillMaxWidth()){
        Column(modifier=Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            CircularProgressIndicator(
                color = Color.Cyan, strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round

            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Please Wait", style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            ))
        }




    }








}