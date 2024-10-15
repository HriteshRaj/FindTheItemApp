package com.hritesh.findtheitemchallenge.presentation.screens.photoupload

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun PhotoUpload(
    navController: NavController,
    inputViewModel: InputViewModel,
    predictionViewModel: PredictionViewModel
) {

    HandleBackPress(navController = navController, inputViewModel =inputViewModel )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        predictionViewModel.setCaputedImage(bitmap)
        predictionViewModel.predictImage()
        navController.navigate(Screen.PhotoVerificationPending.route)


    }




    val choosenItem = inputViewModel.choosenItem.collectAsState()

    LaunchedEffect(inputViewModel) {
        delay(3000)
    }
      inputViewModel.onFinished=
         {
               navController.navigate(Screen.CompletedChallenge.route)
           }


    //}

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {



                Text(
                    text = "Next item to find:",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
                Text(
                    text = choosenItem.value ?: "No item",
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = 35.sp,
                        fontWeight = FontWeight.W800
                    )
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    OutlinedButton(onClick = {
                        //if (choosenItem.value == null) {
                          //  navController.navigate(Screen.CompletedChallenge.route)
                        //}
                        inputViewModel.pickNextItem()
                    }) {
                        Text(text = "Skip")
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(onClick = { launcher.launch(null) }) {
                        Text(text = "Take photo")
                    }
                }

                Spacer(modifier = Modifier.height(35.dp))

            }
        }


}










