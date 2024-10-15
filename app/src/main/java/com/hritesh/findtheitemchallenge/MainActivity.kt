package com.hritesh.findtheitemchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hritesh.findtheitemchallenge.navigation.NavGraph
import com.hritesh.findtheitemchallenge.presentation.screens.homeScreen.HomeScreen
import com.hritesh.findtheitemchallenge.presentation.screens.onBoarding.OnBoardingPager

import com.hritesh.findtheitemchallenge.ui.theme.FindTheItemChallengeTheme
import com.hritesh.findtheitemchallenge.viewmodel.InputViewModel
import com.hritesh.findtheitemchallenge.viewmodel.PredictionViewModel


class MainActivity : ComponentActivity() {
    private val userinputViewModel: InputViewModel by viewModels()
    private val predictionViewModel :PredictionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {



            FindTheItemChallengeTheme {



                Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->

                 NavGraph(modifier=Modifier.padding(innerPadding),userinputViewModel,predictionViewModel)





                }
            }
        }
    }

}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FindTheItemChallengeTheme {
        Greeting("Android")
    }
}