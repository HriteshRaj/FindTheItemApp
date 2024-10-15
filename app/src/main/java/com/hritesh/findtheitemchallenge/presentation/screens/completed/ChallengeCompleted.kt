package com.hritesh.findtheitemchallenge.presentation.screens.completed

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.hritesh.findtheitemchallenge.navigation.HandleBackPress
import com.hritesh.findtheitemchallenge.navigation.Screen
import com.hritesh.findtheitemchallenge.presentation.screens.homeScreen.CommonButtons
import com.hritesh.findtheitemchallenge.viewmodel.InputViewModel
import com.hritesh.findtheitemchallenge.viewmodel.PredictionViewModel
import kotlinx.coroutines.delay

@Composable
fun ChallengeCompletedScreen(
    navController: NavController,
    inputViewModel: InputViewModel,
    predictionViewModel: PredictionViewModel
) {
    HandleBackPress(navController = navController, inputViewModel =inputViewModel )
    var shouldNavigate by remember { mutableStateOf(false) }
    val totalScore = inputViewModel.score.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
       
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontStyle = FontStyle.Normal,fontWeight = FontWeight.Bold, shadow = Shadow(Color.LightGray), fontSize = 20.sp)){append("Your Score is : ")}
            withStyle(style = SpanStyle(color = Color.Magenta, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)){append(" ${totalScore.value} ")}
        }

            )
        if(shouldNavigate){
            LaunchedEffect(Unit) {
                delay(1500)
                navController.navigate(Screen.ChooseOption.route)
                inputViewModel.resetScore()
                shouldNavigate= false

            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        CommonButtons(text = "Try a different Challenge ") {




            shouldNavigate = true



        }
        
    }


}