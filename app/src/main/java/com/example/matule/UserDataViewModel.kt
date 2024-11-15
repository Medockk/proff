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
    private val _name = mutableStateOf("nnnnn")
    val name = _name
    private var _email= mutableStateOf("eeeee")
    val email = _email
    private var _password = mutableStateOf("ppppp")
    val password = _password

    val emailText = mutableStateOf("")
    val passwordText = mutableStateOf("")
}