package com.vikash.currencyconverter

import android.graphics.Paint.Style
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun screenView(modifier: Modifier= Modifier){
    val stateDescriptor: MainViewModel= viewModel()
    val finalData by stateDescriptor.stateHolderExposer
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)){
        if (finalData.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }else if (finalData.error!=null){
            Text(text = "Error is ${finalData.error}", style = TextStyle(fontSize = 24.sp, color = Color.White))
        }else{
            Column (
                modifier= Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){

            }
        }
    }

}