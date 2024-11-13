package com.example.matule

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserDataViewModel : ViewModel() {
    val nameText = { mutableStateOf("Эдуард риаир") }
    val emailText = { mutableStateOf("") }
    val passwordText = { mutableStateOf("") }
    val familiaText = { mutableStateOf("") }
    val addressText = { mutableStateOf("") }
    val phoneText = { mutableStateOf("") }
    val searchText = { mutableStateOf("") }
}