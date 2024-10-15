package com.hritesh.findtheitemchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.hritesh.findtheitemchallenge.API_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

class InputViewModel : ViewModel() {

    private val _selectOption = MutableStateFlow<String?>(null)
    val selectedOption = _selectOption

    private val _generatedItems= MutableStateFlow<List<String>>(emptyList())
    val generatedItems:StateFlow<List<String>> = _generatedItems

    private val _choosenItem = MutableStateFlow<String?>(null)
    val choosenItem:StateFlow<String?> = _choosenItem

    private var currentindex =0
    var onFinished:(()->Unit)?=null

    private val _itemsLeft = MutableStateFlow(0)
    val itemsLeft: StateFlow<Int> = _itemsLeft

    private val _score = MutableStateFlow<Int>(0)
    val score = _score

    fun setScore(reward:Int){
        _score.value += reward
    }
    fun selectOption(input:String){
        _selectOption.value= input
        generateItems(input)


    }

    fun pickNextItem(){
        viewModelScope.launch {
            if(currentindex < _generatedItems.value.size){
                _choosenItem.value = _generatedItems.value[currentindex]

                _itemsLeft.value = _generatedItems.value.size - currentindex
                currentindex++
            }else{
                onFinished?.invoke()
                _choosenItem.value = "No more items"
                _itemsLeft.value = 0
                Log.d("input view model", "No more items to pick")
            }


        }
    }
    fun setScoreWhenWrong(reward:Int){
        _score.value -= reward
    }


    private fun generateItems(input: String) {
        viewModelScope.launch {
            val randomNumber = (1..1000).random()

            val prompt = when (input) {

                "Home" -> "List 10 unique single-word items that are commonly found in a home.Just list the items separated by commas, without any introductory sentences. Example :Couch,Table, etc.Random number :$randomNumber"
                "Food" -> "List 10 unique single-word food items that are commonly found in a home.Just list the items separated by commas, without any introductory sentences. Example :Cookies,Cake,Vegetable,Fruits, etc.Random number :$randomNumber"
                "Flowers" -> "List 10 unique single-word flowers that are commonly found in a garden.Just list the items separated by commas, without any introductory sentences. Example :Rose,Tulip, etc.Random number :$randomNumber"
                "Outdoor" -> "List 10 unique single-word outdoor objects  that are commonly found outside of home.Just list the items separated by commas, without any introductory sentences. Example :fire hydrant,road side sign boards, etc.Random number :$randomNumber"
                "Location" -> "List 10 unique single-word places that are commonly found outside.Just list the items separated by commas, without any introductory sentences. Example :Rivers,Temples,Church, etc.Random number :$randomNumber"
                "Pets" -> "List 10 unique single-word pets that are commonly found in a home.Just list the items separated by commas, without any introductory sentences. Example :Dogs,Cats,Parrots, etc.Random number :$randomNumber"
                "Vehicle" -> "List 10 unique single-word vehicles that are commonly found on a road.Just list the items separated by commas, without any introductory sentences. Example :Bus,Truck,Bikes,Cars,Cycle, etc.Random number :$randomNumber"
                "Others" -> "List 10 unique single-word items that are commonly randomly with all categories .Just list the items separated by commas, without any introductory sentences. Example :Carrot,Mobile,Comb, etc.Random number :$randomNumber"
                else -> ""


            }

            val generativeModel = GenerativeModel("gemini-1.5-flash", API_KEY)

            val response = generativeModel.generateContent(prompt)

            val items= response.text
                ?.split(",")
                ?.map { it.trim() }
                ?.filter { it.isNotBlank() }
                ?.map { it.replace(Regex("[^A-Za-z ]"), "").trim() }

            Log.d("Input viewmodel","Gnerated items in viewmodel $input , $items")
            if(items!=null){
                _generatedItems.value= items
                currentindex=0
                _itemsLeft.value=items.size
                pickNextItem()


            }


        }





    }
    fun resetScore(){
        _score.value= 0
    }


}