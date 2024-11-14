package com.example.matule

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserDataViewModel : ViewModel() {
    val _name = mutableStateOf("nnnnn")
    val name = _name
    var _email= mutableStateOf("eeeee")
    val email = _email
    var _password = mutableStateOf("ppppp")
    val password = _password

    var latitude = 0.0
    var longitude = 0.0

    val emailText = mutableStateOf("")
    val passwordText = mutableStateOf("")
}