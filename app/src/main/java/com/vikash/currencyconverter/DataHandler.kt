package com.vikash.currencyconverter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class CurrencyData(
    val AUD: Double,
    val BGN: Double,
    val BRL: Double,
    val CAD: Double,
    val CHF: Double,
    val CNY: Double,
    val CZK: Double,
    val DKK: Double,
    val EUR: Double,
    val GBP: Double,
    val HKD: Double,
    val HRK: Double,
    val HUF: Double,
    val IDR: Double,
    val ILS: Double,
    val INR: Double,
    val ISK: Double,
    val JPY: Double,
    val KRW: Double,
    val MXN: Double,
    val MYR: Double,
    val NOK: Double,
    val NZD: Double,
    val PHP: Double,
    val PLN: Double,
    val RON: Double,
    val RUB: Double,
    val SEK: Double,
    val SGD: Double,
    val THB: Double,
    val TRY: Double,
    val USD: Double,
    val ZAR: Double
)

class FinalDataHolder {
    val data : CurrencyData= CurrencyData(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0)
}

class RunnerData{
    private var _userInputValue: String? by mutableStateOf("")


    var showValueHolder: String? by mutableStateOf(_userInputValue)


    fun setUserInputValue(value: String?){
        _userInputValue= value
        showValueHolder=_userInputValue
    }

    var fromMenuIsExpanded by mutableStateOf(false)

    fun fromMenuHider(){
        fromMenuIsExpanded=false
    }

    var toMenuIsExpanded by mutableStateOf(false)

    fun toMenuHider(){
        toMenuIsExpanded=false
    }

    var fromChosenValue: Double= 0.0
    var toChosenValue: Double= 0.0
    var fromButtonString: String= "FROM"
    var toButtonString: String= "TO"
}