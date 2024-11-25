package com.example.matule

sealed class Navigation(val route: String) {
    object onBoard1: Navigation("OnBoard1")
    object WearMe: Navigation("WearMe")
    object onBoard2: Navigation("OnBoard2")
    object onBoard3: Navigation("OnBoard3")
    object SignIn: Navigation("SignIn")
    object RegisterAccount: Navigation("RegisterAccount")
    object ForgotPassword: Navigation("ForgotPassword")
    object Verification: Navigation("Verification")
    object SetAlertDialog: Navigation("SetAlertDialog")

    object Home: Navigation("Home")
    object Favorite: Navigation("Favorite")
    object MyCart: Navigation("MyCart")
    object Orders: Navigation("Orders")
    object Popular: Navigation("Popular")
    object ListingOutDoor: Navigation("ListingOutDoor")
    object Details: Navigation("Details")
    object CheckOut: Navigation("CheckOut")
    object YandexMapKit: Navigation("YandexMapKit")

    object Profile: Navigation("Profile")
    object SideMenu: Navigation("SideMenu")
    object EditProfile: Navigation("EditProfile")
    object Notification: Navigation("Notification")
    object Search: Navigation("Search")
}