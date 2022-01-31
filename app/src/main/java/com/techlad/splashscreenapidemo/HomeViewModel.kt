package com.techlad.splashscreenapidemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * Created by umair.khalid on 01,February,2022
 **/

class HomeViewModel : ViewModel() {

    private val _apiResponse = MutableStateFlow(false)
    val apiResponse = _apiResponse.asStateFlow()

    init {
        fetchSomeInitialResult()
    }

    private fun fetchSomeInitialResult() {

        viewModelScope.launch {
            // Add fake delay for demo
            delay(4000)

            // Usually a usecases is executed here to check/fetch some app configurations
            // Now Update state if when result is back from api
            _apiResponse.value = true
        }
    }
}