package com.example.matule

sealed class Navigation(val route: String) {
    object firstPage: Navigation("FirstPage")
    object onBoard1: Navigation("OnBoard1")
    object onBoard2: Navigation("OnBoard2")
    object onBoard3: Navigation("OnBoard3")
    object SignIn: Navigation("SignIn")
    object RegisterAccount: Navigation("RegisterAccount")
    object ForgotPassword: Navigation("ForgotPassword")
    object Verification: Navigation("Verification")
    object Home: Navigation("Home")
    object CheckOut: Navigation("CheckOut")
    object YandexMapKit: Navigation("YandexMapKit")
}