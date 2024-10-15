package com.hritesh.findtheitemchallenge.presentation.screens.backgroundload

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hritesh.findtheitemchallenge.R
import com.hritesh.findtheitemchallenge.navigation.HandleBackPress
import com.hritesh.findtheitemchallenge.navigation.Screen
import com.hritesh.findtheitemchallenge.presentation.screens.homeScreen.CommonButtons
import com.hritesh.findtheitemchallenge.viewmodel.InputViewModel
import kotlinx.coroutines.delay


@Composable
fun BackGroundLoadingScreen(navController: NavController,inputViewModel: InputViewModel) {


    HandleBackPress(navController = navController, inputViewModel =inputViewModel )

    val items = inputViewModel.generatedItems.collectAsState()


    LaunchedEffect(items.value) {



        if(items.value.isNotEmpty()){
            delay(500)
            Log.d("BackGround Loading", "Items generated ${items.value}")


        }

    }
    Column(modifier=Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {

        Image(modifier= Modifier
            .height(100.dp)
            .align(Alignment.CenterHorizontally), contentScale = ContentScale.Crop,painter = painterResource(id = R.drawable.machine), contentDescription = null)
    Spacer(modifier = Modifier.height(16.dp))

        if(items.value.isEmpty()){

            CircularProgressIndicator(
                modifier=Modifier.align(Alignment.CenterHorizontally)
                , color = Color.Cyan
                , strokeCap = StrokeCap.Round,
                strokeWidth = 4.dp
            )

        }else{
                Column(modifier=Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    CommonButtons(text ="Start Finding" ) {
                        navController.navigate(Screen.PhotoUpload.route)


                    }
                }

        }






    }








}