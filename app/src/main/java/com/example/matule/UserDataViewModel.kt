package com.example.matule

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserDataViewModel : ViewModel() {
    data class UserData(
        var name: MutableState<String> = mutableStateOf(""),
        var familia: MutableState<String> = mutableStateOf(""),
        var address: MutableState<String> = mutableStateOf(""),
        var phone: MutableState<String> = mutableStateOf(""),
        var email: MutableState<String> = mutableStateOf(""),
        var password: MutableState<String> = mutableStateOf("")
    )
}