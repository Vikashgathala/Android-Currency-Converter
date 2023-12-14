package com.vikash.currencyconverter

import android.graphics.Paint.Style
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults.shape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun screenView(modifier: Modifier= Modifier){
    val context= LocalContext.current
    val stateDescriptor: MainViewModel= viewModel()
    val finalData by stateDescriptor.stateHolderExposer
    val keyboardController= LocalSoftwareKeyboardController.current
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)){
        if (finalData.isLoading){
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(150.dp),
                color = Color.Gray
            )
        }else if (finalData.error!=null){
            Text(text = "Error is ${finalData.error}", style = TextStyle(fontSize = 24.sp, color = Color.White))
        }else{
            Column (
                modifier= Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ){

                Spacer(modifier = Modifier.height(50.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(text = "Convert currency",
                        style = TextStyle(
                            fontSize = 30.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier= Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier){
                        Button(onClick = { stateDescriptor.runner.fromMenuIsExpanded=true
                                         stateDescriptor.runner.usdReference= stateDescriptor.stateHolderExposer.value.dataObject.data.USD},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            )
                            ,
                            modifier= Modifier
                                .width(150.dp)
                                .height(50.dp)
                                .background(Color.White, shape = RoundedCornerShape(20))
                                .fillMaxSize()) {
                            Text(text = stateDescriptor.runner.fromButtonString,
                                style = TextStyle(fontSize = 20.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold))
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                        DropdownMenu(expanded = stateDescriptor.runner.fromMenuIsExpanded, onDismissRequest = {stateDescriptor.runner.fromMenuHider()},
                            modifier= Modifier
                                .background(color = Color(android.graphics.Color.parseColor("#080808")))
                                .border(
                                    width = 0.8.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .width(150.dp)
                        ) {
                            DropdownMenuItem(text = {Text("AUD", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.AUD
                                stateDescriptor.runner.fromButtonString= "AUD"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("BGN", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.BGN
                                stateDescriptor.runner.fromButtonString= "BGN"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("BRL", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.BRL
                                stateDescriptor.runner.fromButtonString= "BRL"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("CAD", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.CAD
                                stateDescriptor.runner.fromButtonString= "CAD"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("CHF", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.CHF
                                stateDescriptor.runner.fromButtonString= "CHF"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("CNY", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.CNY
                                stateDescriptor.runner.fromButtonString= "CNY"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("CZK", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.CZK
                                stateDescriptor.runner.fromButtonString= "CZK"
                                stateDescriptor.runner.fromMenuHider()})
                            DropdownMenuItem(text = {Text("DKK", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.DKK
                                stateDescriptor.runner.fromButtonString= "DKK"
                                stateDescriptor.runner.fromMenuHider()})
                            DropdownMenuItem(text = {Text("EUR", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.EUR
                                stateDescriptor.runner.fromButtonString= "EUR"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("GBP", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.GBP
                                stateDescriptor.runner.fromButtonString= "GBP"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("HKD", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.HKD
                                stateDescriptor.runner.fromButtonString= "HKD"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("HRK", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.HRK
                                stateDescriptor.runner.fromButtonString= "HRK"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("HUF", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.HUF
                                stateDescriptor.runner.fromButtonString= "HUF"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("IDR", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.IDR
                                stateDescriptor.runner.fromButtonString= "IDR"
                                stateDescriptor.runner.fromMenuHider()})
                            DropdownMenuItem(text = {Text("ILS", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.ILS
                                stateDescriptor.runner.fromButtonString= "ILS"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("INR", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.INR
                                stateDescriptor.runner.fromButtonString= "INR"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("ISK", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.ISK
                                stateDescriptor.runner.fromButtonString= "ISK"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("JPY", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.JPY
                                stateDescriptor.runner.fromButtonString= "JPY"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("KRW", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.KRW
                                stateDescriptor.runner.fromButtonString= "KRW"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("MXN", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.MXN
                                stateDescriptor.runner.fromButtonString= "MXN"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("MYR", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.MYR
                                stateDescriptor.runner.fromButtonString= "MYR"
                                stateDescriptor.runner.fromMenuHider()})
                            DropdownMenuItem(text = {Text("NOK", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.NOK
                                stateDescriptor.runner.fromButtonString= "NOK"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("NZD", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.NZD
                                stateDescriptor.runner.fromButtonString= "NZD"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("PHP", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.PHP
                                stateDescriptor.runner.fromButtonString= "PHP"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("PLN", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.PLN
                                stateDescriptor.runner.fromButtonString= "PLN"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("RON", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.RON
                                stateDescriptor.runner.fromButtonString= "RON"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("RUB", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.RUB
                                stateDescriptor.runner.fromButtonString= "RUB"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("SEK", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.SEK
                                stateDescriptor.runner.fromButtonString= "SEK"
                                stateDescriptor.runner.fromMenuHider()})
                            DropdownMenuItem(text = {Text("SGD", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.SGD
                                stateDescriptor.runner.fromButtonString= "SGD"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("THB", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.THB
                                stateDescriptor.runner.fromButtonString= "THB"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("TRY", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.TRY
                                stateDescriptor.runner.fromButtonString= "TRY"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("USD", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.USD
                                stateDescriptor.runner.fromButtonString= "USD"
                                stateDescriptor.runner.fromMenuHider()
                            })
                            DropdownMenuItem(text = {Text("ZAR", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.fromChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.ZAR
                                stateDescriptor.runner.fromButtonString= "ZAR"
                                stateDescriptor.runner.fromMenuHider()
                            })
                        }
                    }
                        Spacer(modifier = Modifier.width(30.dp))
                    Box(modifier = Modifier){
                        Button(onClick = { stateDescriptor.runner.toMenuIsExpanded=true
                            stateDescriptor.runner.usdReference= stateDescriptor.stateHolderExposer.value.dataObject.data.USD},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            )
                            ,
                            modifier= Modifier
                                .width(150.dp)
                                .height(50.dp)
                                .background(Color.White, shape = RoundedCornerShape(20))
                                .fillMaxSize()) {
                            Text(text = stateDescriptor.runner.toButtonString,
                                style = TextStyle(fontSize = 20.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                        DropdownMenu(expanded = stateDescriptor.runner.toMenuIsExpanded, onDismissRequest = {stateDescriptor.runner.toMenuHider()},
                            modifier= Modifier
                                .background(color = Color(android.graphics.Color.parseColor("#080808")))
                                .border(
                                    width = 0.8.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(15.dp)
                                )
                                .width(150.dp)) {
                            DropdownMenuItem(text = {Text("AUD", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.AUD
                                stateDescriptor.runner.toButtonString= "AUD"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("BGN", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.BGN
                                stateDescriptor.runner.toButtonString= "BGN"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("BRL", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.BRL
                                stateDescriptor.runner.toButtonString= "BRL"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("CAD", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.CAD
                                stateDescriptor.runner.toButtonString= "CAD"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("CHF", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.CHF
                                stateDescriptor.runner.toButtonString= "CHF"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("CNY", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.CNY
                                stateDescriptor.runner.toButtonString= "CNY"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("CZK", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.CZK
                                stateDescriptor.runner.toButtonString= "CZK"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("DKK", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.DKK
                                stateDescriptor.runner.toButtonString= "DKK"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("EUR", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.EUR
                                stateDescriptor.runner.toButtonString= "EUR"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("GBP", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.GBP
                                stateDescriptor.runner.toButtonString= "GBP"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("HKD", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.HKD
                                stateDescriptor.runner.toButtonString= "HKD"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("HRK", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.HRK
                                stateDescriptor.runner.toButtonString= "HRK"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("HUF", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.HUF
                                stateDescriptor.runner.toButtonString= "HUF"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("IDR", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.IDR
                                stateDescriptor.runner.toButtonString= "IDR"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("ILS", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.ILS
                                stateDescriptor.runner.toButtonString= "ILS"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("INR", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.INR
                                stateDescriptor.runner.toButtonString= "INR"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("ISK", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.ISK
                                stateDescriptor.runner.toButtonString= "ISK"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("JPY", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.JPY
                                stateDescriptor.runner.toButtonString= "JPY"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("KRW", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.KRW
                                stateDescriptor.runner.toButtonString= "KRW"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("MXN", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.MXN
                                stateDescriptor.runner.toButtonString= "MXN"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("MYR", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.MYR
                                stateDescriptor.runner.toButtonString= "MYR"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("NOK", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.NOK
                                stateDescriptor.runner.toButtonString= "NOK"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("NZD", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.NZD
                                stateDescriptor.runner.toButtonString= "NZD"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("PHP", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.PHP
                                stateDescriptor.runner.toButtonString= "PHP"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("PLN", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.PLN
                                stateDescriptor.runner.toButtonString= "PLN"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("RON", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.RON
                                stateDescriptor.runner.toButtonString= "RON"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("RUB", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.RUB
                                stateDescriptor.runner.toButtonString= "RUB"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("SEK", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.SEK
                                stateDescriptor.runner.toButtonString= "SEK"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("SGD", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.SGD
                                stateDescriptor.runner.toButtonString= "SGD"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("THB", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.THB
                                stateDescriptor.runner.toButtonString= "THB"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("TRY", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.TRY
                                stateDescriptor.runner.toButtonString= "TRY"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("USD", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.USD
                                stateDescriptor.runner.toButtonString= "USD"
                                stateDescriptor.runner.toMenuHider()
                            })
                            DropdownMenuItem(text = {Text("ZAR", style = TextStyle(fontSize = 20.sp, color = Color.White))}, onClick = {
                                stateDescriptor.runner.toChosenValue= stateDescriptor.stateHolderExposer.value.dataObject.data.ZAR
                                stateDescriptor.runner.toButtonString= "ZAR"
                                stateDescriptor.runner.toMenuHider()
                            })
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier= Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(modifier = Modifier.width(80.dp)){
                        if (stateDescriptor.runner.fromButtonString== "FROM" || stateDescriptor.runner.toButtonString=="TO"){
                            Text(text = "")
                        }else{
                            Text(text = "${stateDescriptor.runner.fromButtonString} :",
                                style = TextStyle(fontSize = 22.sp,
                                    color = Color.White))
                        }
                    }

                    OutlinedTextField(value = stateDescriptor.runner.showValueHolder.toString(), onValueChange = {
                        stateDescriptor.runner.setUserInputValue(it)
                        stateDescriptor.runner.finalInputConverter()},
                        shape = RoundedCornerShape(CornerSize(15.dp)),
                        textStyle = TextStyle(fontSize = 25.sp),
                        modifier = Modifier
                            .background(
                                color = Color(android.graphics.Color.parseColor("#323232")),
                                shape = MaterialTheme.shapes.large
                            )
                            .border(
                                width = 0.dp,
                                color = Color.Transparent
                            ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
                
               // Text(text = "${stateDescriptor.runner.convertedAmount}")
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier= Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(modifier = Modifier.width(80.dp)){
                        if (stateDescriptor.runner.fromButtonString== "FROM" || stateDescriptor.runner.toButtonString=="TO"){
                            Text(text = "")
                        }else{
                            Text(text = "${stateDescriptor.runner.toButtonString} :",
                                style = TextStyle(fontSize = 22.sp,
                                    color = Color.White))
                        }
                    }
                    Box(modifier = Modifier
                        .background(
                            color = Color(android.graphics.Color.parseColor("#323232")),
                            shape = RoundedCornerShape(20)
                        )
                        .border(BorderStroke(0.dp, Color.Transparent))
                        .offset(y = 200.dp)
                        .height(60.dp)
                        .width(280.dp)
                    ){
                        if (stateDescriptor.runner.convertedAmount.isNaN()){
                            Text(
                                text = "0",
                                style = TextStyle(
                                    color = Color(android.graphics.Color.parseColor("#949494")),
                                    fontSize = 24.sp
                                ),
                                modifier = Modifier.offset(x= 10.dp ,y = -187.dp)
                            )
                        }else {
                            if (stateDescriptor.runner.convertedAmount == 0.0) {
                                Text(
                                    text = "0",
                                    style = TextStyle(
                                        color = Color(android.graphics.Color.parseColor("#949494")),
                                        fontSize = 24.sp
                                    ),
                                    modifier = Modifier.offset(x = 10.dp, y = -187.dp)
                                )
                            } else {
                                Text(
                                    text = "${stateDescriptor.runner.convertedAmount}",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 24.sp
                                    ),
                                    modifier = Modifier.offset(x = 10.dp, y = -187.dp)
                                )
                            }
                        }
                    }

                }
            }

        }
    }

}