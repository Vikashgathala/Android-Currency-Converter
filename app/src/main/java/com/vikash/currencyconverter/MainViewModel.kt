package com.vikash.currencyconverter

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {
    private val _stateHolder= mutableStateOf(DataState())
    val stateHolderExposer: State<DataState> = _stateHolder

    init {
        fetchDataHandler()
    }

    fun fetchDataHandler(){
        viewModelScope.launch {
            try {
                val tempDataHolder= ApiService.getCurrencyData(
                    "fca_live_eUKsoUXHhF1RdmGFFmf3rZWUsPb7tKGspWvwVPiI"
                )
                _stateHolder.value= _stateHolder.value.copy(
                    isLoading = false,
                    dataObject = tempDataHolder
                )
            }catch (e: Exception){
                _stateHolder.value= _stateHolder.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }
}

data class DataState(
    val isLoading: Boolean= true,
    val dataObject: FinalDataHolder= FinalDataHolder(),
    val error: String?= null
)