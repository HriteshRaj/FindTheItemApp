package com.hritesh.findtheitemchallenge.presentation.screens.onBoarding



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.hritesh.findtheitemchallenge.R
import com.hritesh.findtheitemchallenge.model.OnBoardingModel
import com.hritesh.findtheitemchallenge.navigation.Screen

import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPager(navController: NavController
                    ) {

    val pagerState = rememberPagerState(initialPage = 0)
    val list = getListOfPager()
    val isNextVisible = remember {
        derivedStateOf {
            pagerState.currentPage != list.size - 1
        }
    }

    val isPreviousVisible = remember {
        derivedStateOf {
            pagerState.currentPage != 0
        }
    }
    val isFinishVisible = remember{
        derivedStateOf {
            pagerState.currentPage == list.size-1
        }
    }
    val scope = rememberCoroutineScope()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center, modifier = Modifier.padding(top= 8.dp).padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.80f)
                .fillMaxWidth()
        ) {

            com.google.accompanist.pager.HorizontalPager(
                count = list.size,
                state = pagerState
            ) { page ->

                ContentDetails(list, page)

            }

        }
        HorizontalPagerIndicator(pagerState = pagerState)
        PagerButtons(
            isNextVisible = isNextVisible.value,
            isPreviousVisible = isPreviousVisible.value,
            onNextClicked = {
                scope.launch {



                        // Navigate to the home screen when on the last page
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)

                }
                            }
            , onPreviousClicked ={
                scope.launch {


                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
            },
            isFinishVisible= isFinishVisible.value,
            onFinishClicked = {
                scope.launch {
                   navController.navigate(Screen.Home.route)
                }


            }


        )


    }


}

fun getListOfPager(): List<OnBoardingModel> {
    return listOf(
        OnBoardingModel(
            "Find Items",
            R.drawable.illustration1,
            "And Take A Photo"
        ),
        OnBoardingModel(
            "Challenge",
            R.drawable.illustration2,
            "Artificial Intelligence"
        ),
        OnBoardingModel(
            "Lets",
            R.drawable.illustration3,
            "Start"
        )


    )
}

@Composable
fun ContentDetails(list: List<OnBoardingModel>, currentPage: Int) {


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(modifier=Modifier.align(Alignment.CenterHorizontally),
            text = list[currentPage].title,  style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            ))
        Spacer(modifier = Modifier.height(8.dp))

        AsyncImage(
            model = list[currentPage].image, contentDescription = null,
            modifier = Modifier
                .width(400.dp)
                .height(480.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(modifier=Modifier.align(Alignment.CenterHorizontally),
            text = list[currentPage].description,
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Spacer(modifier = Modifier.height(8.dp))


    }

}

@Composable
fun PagerButtons(
    isNextVisible: Boolean, isPreviousVisible: Boolean,
    onNextClicked: () -> Unit,
    onPreviousClicked: () -> Unit,isFinishVisible:Boolean,onFinishClicked:()->Unit
) {


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        if (isNextVisible) {


            Button(onClick = onNextClicked) {
                Text(text = "Next")

            }
        }
        if (isPreviousVisible) {
            Button(onClick = onPreviousClicked) {
                Text(text = "Previous")
            }
        }
        if(isFinishVisible) {
            // Show "Finish" button on the last page
            Button(onClick = onFinishClicked) {
                Text(text = "Finish")
            }

        }

    }
}


