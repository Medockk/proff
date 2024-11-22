package com.example.matule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class MutableStateOf : ViewModel() {
    @Composable
    fun getMutableStateOf(text: String): MutableState<String>? {
        val nameText = remember { mutableStateOf("") }
        val emailText = remember { mutableStateOf("") }
        val passwordText = remember { mutableStateOf("") }
        val familiaText = remember { mutableStateOf("") }
        val addressText = remember { mutableStateOf("") }
        val phoneText = remember { mutableStateOf("") }
        val searchText = remember { mutableStateOf("") }
        when (text) {
            name -> return nameText
            email -> return emailText
            password -> return passwordText
            familia -> return familiaText
            address -> return addressText
            phone -> return phoneText
            search -> return searchText
        }
        return null
    }

    data class go(
        var name: MutableState<String> = mutableStateOf(""),
        var email: MutableState<String> = mutableStateOf(""),
        var pass: MutableState<String> = mutableStateOf("")
    )

    val name1: MutableState<String> = mutableStateOf("")
    val email1: MutableState<String> = mutableStateOf("")
    val password1: MutableState<String> = mutableStateOf("")
}