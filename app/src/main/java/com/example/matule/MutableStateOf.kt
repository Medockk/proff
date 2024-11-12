package com.example.matule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

sealed class MutableStateOf {
    companion object{
        @Composable
        @JvmStatic
        fun getMutableStateOf(text: String): MutableState<String>? {
            val nameText = remember { mutableStateOf("Эдуард риаир") }
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
        @Composable
        @JvmStatic
        fun gget(text: String): MutableState<Boolean>?{
            var state = remember { mutableStateOf(false) }
            when(text){
                state1 -> return state
            }
            return null
        }
    }

}