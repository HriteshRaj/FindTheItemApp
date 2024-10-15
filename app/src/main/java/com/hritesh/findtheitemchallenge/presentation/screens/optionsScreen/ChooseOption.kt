package com.hritesh.findtheitemchallenge.presentation.screens.optionsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.hritesh.findtheitemchallenge.R
import com.hritesh.findtheitemchallenge.navigation.HandleBackPress
import com.hritesh.findtheitemchallenge.navigation.Screen
import com.hritesh.findtheitemchallenge.viewmodel.InputViewModel

@Composable
fun ChooseOption(navController: NavController, inputViewModel: InputViewModel) {
    val list = getOptionsItem()
    HandleBackPress(navController = navController, inputViewModel =inputViewModel )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                painter = painterResource(id = R.drawable.illustration2),
                contentDescription = null
            )
            Text(text = "Choose Any One",style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(items = list) {
                    LazyItemCard(Icon = it.imageRes, text = it.title) {
                        inputViewModel.selectOption(it.title)
                        navController.navigate(Screen.BackGroundLoad.route)

                    }
                }

            }

        }


    }


}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LazyItemCard(Icon: Int, text: String, onClick: () -> Unit) {


    Surface(
        modifier = Modifier
            .height(150.dp)
            .padding(10.dp, 10.dp), shape = RoundedCornerShape(16.dp),
        elevation = 16.dp,
        onClick = onClick
    ) {
        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray.copy(alpha = 0.7f)),


            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(painter = painterResource(id = Icon), contentDescription = null)
            Text(text = text, style = MaterialTheme.typography.headlineMedium)
        }
    }


}








