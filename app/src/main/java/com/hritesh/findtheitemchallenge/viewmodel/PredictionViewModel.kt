package com.hritesh.findtheitemchallenge.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.hritesh.findtheitemchallenge.API_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PredictionViewModel:ViewModel(){


    private val _capturedImage = MutableLiveData<Bitmap?>()
    val capturedImage = _capturedImage

    private val _predictedName = MutableStateFlow<String?>(null)
    val predictedName = _predictedName

    fun setCaputedImage(bitmap: Bitmap?){

        _capturedImage.value = bitmap

    }


    fun predictImage(){


        viewModelScope.launch {


            try {

                val bitmap = _capturedImage.value
                if(bitmap!=null){
                    val generativeModel= GenerativeModel("gemini-1.5-flash",
                        API_KEY)

                    val inputContent = content {
                        image(bitmap)
                        text("Prove a single-word name for the item in this image")


                    }
                    val response = generativeModel.generateContent(inputContent)
                    val predictedName = response.text

                    _predictedName.value= predictedName
                    Log.d("Prediction View Model","${_predictedName.value}")









                }






                }catch (e:Exception){

                Log.e("PredictionViewModel", "Error predicting image name", e)
                _predictedName.value = "Prediction failed"




            }





        }


    }


    fun resetPredctedName(){
        _predictedName.value= null

    }




















}