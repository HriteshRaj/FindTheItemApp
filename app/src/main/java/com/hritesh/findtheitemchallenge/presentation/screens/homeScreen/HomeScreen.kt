package com.hritesh.findtheitemchallenge.presentation.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hritesh.findtheitemchallenge.navigation.NavGraph
import com.hritesh.findtheitemchallenge.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()

    ) {
        Column(modifier=Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Are you Ready ?",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                ))
            CommonButtons(text = "Yes",modifier= Modifier.align(Alignment.CenterHorizontally)

                .padding(16.dp)) {
                navController.navigate(Screen.ChooseOption.route)

            }
        }


    }


}

@Composable
fun CommonButtons(modifier: Modifier = Modifier,text:String,onClick:()->Unit) {
    Button(onClick =  onClick,
        modifier=modifier) {
        Text(text = text, style = MaterialTheme.typography.titleLarge.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        ))

    }


    
}

@Preview(showBackground = true)
@Composable
fun preview(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    HomeScreen(navController = navController)

}