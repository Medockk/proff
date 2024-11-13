package com.example.matule

import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.clearCompositionErrors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matule.ui.theme.MatuleTheme
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import ru.sulgik.mapkit.compose.YandexMap
import ru.sulgik.mapkit.compose.bindToLifecycleOwner
import ru.sulgik.mapkit.compose.rememberAndInitializeMapKit
import ru.sulgik.mapkit.compose.rememberCameraPositionState
import ru.sulgik.mapkit.geometry.Point
import ru.sulgik.mapkit.map.CameraPosition
import java.util.Date
import kotlin.concurrent.timer

const val postgrest = "Users"
const val postEmail = "email"
const val postPassword = "password"


class MainActivity : ComponentActivity() {
    val pointObj = PointObj(this, this)
    val local = object : LocationListener {
        override fun onLocationUpdated(p0: Location) {
            PointObj.myLatitude.value = p0.position.latitude
            PointObj.myLongitude.value = p0.position.longitude
        }

        override fun onLocationStatusUpdated(p0: LocationStatus) {

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey("eed2a724-fc95-4e5d-935a-8e0c346df956")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
//            NavHost(navController = navController, startDestination = Navigation.firstPage.route) {
//                composable(Navigation.firstPage.route) {
//                    FirstPage {
//                        navController.navigate(Navigation.onBoard1.route) {
//                            popUpTo(Navigation.firstPage.route) {
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.onBoard1.route) {
//                    OnBoard1 {
//                        navController.navigate(Navigation.onBoard2.route) {
//                            popUpTo(Navigation.onBoard1.route) {
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.onBoard2.route) {
//                    OnBoard2 {
//                        navController.navigate(Navigation.onBoard3.route) {
//                            popUpTo(Navigation.onBoard2.route) {
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.onBoard3.route) {
//                    OnBoard3 {
//                        navController.navigate(Navigation.SignIn.route) {
//                            popUpTo(Navigation.onBoard3.route) {
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.SignIn.route) {
//                    SignIn {
//                        navController.navigate(Navigation.RegisterAccount.route) {
//                            popUpTo(Navigation.onBoard3.route) {
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.RegisterAccount.route) {
//                    RegisterAccount {
//                        navController.navigate(Navigation.ForgotPassword.route) {
//                            popUpTo(Navigation.SignIn.route) {
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.ForgotPassword.route) {
//                    ForgotPassword {
//                        navController.navigate(Navigation.Verification.route) {
//                            popUpTo(Navigation.RegisterAccount.route) {
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.Verification.route) {
//                    Verification {
//                        navController.navigate(Navigation.Home.route) {
//                            popUpTo(Navigation.ForgotPassword.route) {
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.Home.route) {
//                    Home {
//
//                    }
//                }
//            }
            pointObj.requestLocationPermission()
            Toast.makeText(this, "${PointObj.myLatitude.value}", Toast.LENGTH_SHORT).show()
            NavHost(navController = navController, startDestination = Navigation.CheckOut.route) {
                composable(Navigation.CheckOut.route) {
                    CheckOut {
                        navController.navigate(Navigation.YandexMapKit.route) {
                            popUpTo(Navigation.CheckOut.route) {
                                inclusive = true
                            }
                        }
                    }
                }
                composable(Navigation.YandexMapKit.route){
                    YandexMapKit()
                }
            }
        }
    }

    val supa = SupaBase()

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    private fun Main(vm: UserDataViewModel = viewModel()) {
        val coroutineScope = rememberCoroutineScope()
        val name: MutableState<String> = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { n ->
                        name.value = n
                    },
                    label = {
                        Text("name")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 5.dp)
                )
                OutlinedTextField(
                    value = email.value,
                    onValueChange = { e ->
                        email.value = e
                    },
                    label = {
                        Text("email")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 5.dp)
                )
                OutlinedTextField(
                    value = password.value,
                    onValueChange = { p ->
                        password.value = p
                    },
                    label = {
                        Text("password")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 5.dp)
                )
                Button(
                    onClick = {
                        //insertUserData(name = name, email = email, password = password)
                        //checkUsers(email, password)
//                        updateData(password)
//                        getData(password = password, email = email)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                ) {
                    Text(text = "Supa!", fontSize = 20.sp)
                }
            }
        }
    }

    private fun insertUserData(
        name: MutableState<String>,
        email: MutableState<String>,
        password: MutableState<String>
    ) {
        lifecycleScope.launch {
            val client = supa.createSupabaseClient()
            val insertNewData = Users(
                name = name.value, email = email.value, password = password.value
            )
            client.postgrest[postgrest].insert(insertNewData)
            Toast.makeText(this@MainActivity, "All OK", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkUsers(email: MutableState<String>, password: MutableState<String>) {
        lifecycleScope.launch {
            val client = supa.createSupabaseClient()
            val andCheckedUsers =
                client.postgrest[postgrest].select(columns = Columns.list("$postEmail, $postPassword")) {
                    filter {
                        and {
                            eq(postEmail, email.value)
                            eq(postPassword, password.value)
                        }
                    }
                }.decodeList<Users>()
            if (andCheckedUsers.isNotEmpty()) {
                Toast.makeText(this@MainActivity, "Founded", Toast.LENGTH_SHORT).show()
                sharedPreferences()
            } else {
                val checkedUsers =
                    client.postgrest[postgrest].select(columns = Columns.list("$postEmail, $postPassword")) {
                        filter {
                            eq(postEmail, email.value)
                        }
                    }.decodeList<Users>()
                if (checkedUsers.isNotEmpty()) {
                    Toast.makeText(this@MainActivity, "Wrong Password", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@MainActivity, "wrong email", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun sharedPreferences() {
        val sp: SharedPreferences = getSharedPreferences("Data About User", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sp.edit()
        editor.putBoolean("SignUp", true)
        editor.apply()
    }

    private fun updateData(password: MutableState<String>) {
        lifecycleScope.launch {
            val client = supa.createSupabaseClient()
            val updatingData = Users(password = password.value)
            client.postgrest[postgrest].update(updatingData) {
                filter {
                    eq(postPassword, "pppp")
                }
            }
            Toast.makeText(this@MainActivity, "ok", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData(password: MutableState<String>, email: MutableState<String>) {
        lifecycleScope.launch {
            val clientData = supa.createSupabaseClient()
            val data = clientData.postgrest[postgrest].select() {
                filter {
                    or {
                        eq(postPassword, password.value)
                        eq(postEmail, email.value)
                    }
                }
            }.decodeList<Users>()
            if (data.isNotEmpty()) {
                data.forEach {
                    Toast.makeText(
                        this@MainActivity,
                        "${it.name}; ${it.email}; ${it.password}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun getClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseUrl = "https://pekaezvbbpwirvnempls.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBla2FlenZiYnB3aXJ2bmVtcGxzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjEzNzA4NzcsImV4cCI6MjAzNjk0Njg3N30.Gmm9j4GYKyudV5p07qJ4DUbQr0AhXEfftyiHobiCYMo"
        ) {
            install(Postgrest)
            install(Auth)
            install(Functions)
            install(Realtime)
            install(Storage)
        }
    }
}