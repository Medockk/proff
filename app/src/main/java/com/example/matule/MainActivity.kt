package com.example.matule

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toIcon
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.OTP
import io.github.jan.supabase.auth.providers.builtin.Phone
import io.github.jan.supabase.auth.signInAnonymously
import io.github.jan.supabase.auth.status.SessionSource
import io.github.jan.supabase.auth.status.SessionStatus
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import io.github.jan.supabase.realtime.realtime
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.sulgik.mapkit.compose.bindToLifecycleOwner
import ru.sulgik.mapkit.compose.rememberAndInitializeMapKit
import ru.sulgik.mapkit.compose.rememberCameraPositionState
import ru.sulgik.mapkit.geometry.Point
import ru.sulgik.mapkit.map.CameraPosition
import java.io.ByteArrayOutputStream
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    lateinit var locationManager: android.location.LocationManager
    lateinit var pointObj: PointObj
    lateinit var cameraPosition: CameraPosition
    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey("eed2a724-fc95-4e5d-935a-8e0c346df956")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        pointObj = PointObj(this, this)
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
//                    SignIn(
//                        onClick = {
//                            navController.navigate(Navigation.Home.route) {
//                                popUpTo(Navigation.SignIn.route) {
//                                    inclusive = true
//                                }
//                            }
//                        },
//                        registerAccountOnClick = {
//                            navController.navigate(Navigation.RegisterAccount.route){
//                                popUpTo(Navigation.SignIn.route){
//                                    inclusive = true
//                                }
//                            }
//                        }
//                    )
//                }
//                composable(Navigation.RegisterAccount.route) {
//                    RegisterAccount(
//                        onClick = {
//                            navController.navigate(Navigation.Home.route) {
//                                popUpTo(Navigation.RegisterAccount.route) {
//                                    inclusive = true
//                                }
//                            }
//                        },
//                        signInOnClick = {
//                            navController.navigate(Navigation.SignIn.route){
//                                popUpTo(Navigation.RegisterAccount.route){
//                                    inclusive = true
//                                }
//                            }
//                        }
//                    )
//                }
//                composable(Navigation.ForgotPassword.route) {
//                    ForgotPassword {
//                        navController.navigate(Navigation.Verification.route) {
//                            popUpTo(Navigation.ForgotPassword.route) {
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.Verification.route) {
//                    Verification {
//                        navController.navigate(Navigation.Home.route) {
//                            popUpTo(Navigation.Verification.route){
//                                inclusive = true
//                            }
//                            popUpTo(Navigation.SetAlertDialog.route){
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.Home.route) {
//                    Home(
//                        favoriteOnClick = {
//                            navController.navigate(Navigation.Favorite.route) {
//                                popUpTo(Navigation.Home.route){
//                                    inclusive = true
//                                }
//                            }
//                        },
//                        myCartOnClick = {
//                            navController.navigate(Navigation.MyCart.route) {
//                                popUpTo(Navigation.Home.route) {
//                                    inclusive = true
//                                }
//                            }
//                        }
//                    )
//                }
//                composable(Navigation.Favorite.route) {
//                    Favorite(
//                        homeOnClick = {
//                            navController.navigate(Navigation.Home.route) {
//                                popUpTo(Navigation.Favorite.route) {
//                                    inclusive = true
//                                }
//                            }
//                        },
//                        myCartOnClick = {
//                            navController.navigate(Navigation.MyCart.route){
//                                popUpTo(Navigation.Favorite.route){
//                                    inclusive = true
//                                }
//                            }
//                        }
//                    )
//                }
//                composable(Navigation.YandexMapKit.route) {
//                    YandexMapKit(
//                        PointObj.myLatitude,
//                        PointObj.myLongitude,
//                        cameraPosition,
//                        this@MainActivity
//                    )
//                }
//                composable(Navigation.Profile.route) {
//                    Profile()
//                }
//                composable(Navigation.CheckOut.route) {
//                    CheckOut {
//                        navController.navigate(Navigation.YandexMapKit.route) {
//                            popUpTo(Navigation.CheckOut.route) {
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.MyCart.route) {
//                    MyCart(
//                        backOcClick = {
//                            navController.navigate(Navigation.Home.route){
//                                popUpTo(Navigation.MyCart.route){
//                                    inclusive = true
//                                }
//                            }
//                        }
//                    )
//                }
//            }
            val coroutineScope = rememberCoroutineScope()
            var data: Users? = null
//            NavHost(navController, startDestination = Navigation.RegisterAccount.route){
//                composable(Navigation.RegisterAccount.route){
//                    RegisterAccount(
//                        onClick = {
//                            coroutineScope.launch {
//                                supa.insertUserData(
//                                    name = userData.name.value,
//                                    email = userData.email.value,
//                                    password = userData.password.value,
//                                    context = this@MainActivity
//                                )
//                                data = supa.getData(
//                                    password = userData.password.value,
//                                    email = userData.email.value,
//                                    context = this@MainActivity
//                                )
//                            }
//                            navController.navigate(Navigation.Profile.route)
//                        }
//                    )
//                }
//                composable(Navigation.Profile.route){
//                    Profile(this@MainActivity, data = data)
//                }
//            }
            NavHost(navController, startDestination = "t"){
                composable("t"){
                    t {
                        navController.navigate(Navigation.YandexMapKit.route)
                    }
                }
                composable(Navigation.YandexMapKit.route){
                    YandexMapKit(
                        PointObj.myLatitude,
                        PointObj.myLongitude,
                        cameraPosition,
                        this@MainActivity
                    )
                }
            }


            //if your gps off
            locationManager = getSystemService(LOCATION_SERVICE) as android.location.LocationManager
            pointObj.requestLocationPermission()
        }
    }

    @Composable
    fun t(o: () -> Unit) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Button(
                {o()}
            ) { }
        }
    }
    val supa = SupaBase()
    var first = true
    private lateinit var o: Users

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        locationManager = getSystemService(LOCATION_SERVICE) as android.location.LocationManager
        locationManager.requestLocationUpdates(
            android.location.LocationManager.NETWORK_PROVIDER, 500, 10f,
            locationListener
        )
        locationManager.requestLocationUpdates(
            android.location.LocationManager.GPS_PROVIDER,
            500, 10f, locationListener
        )
    }

    private val locationListener: android.location.LocationListener =
        object : android.location.LocationListener {
            override fun onLocationChanged(location: android.location.Location) {
                PointObj.myLatitude.value = location.latitude
                PointObj.myLongitude.value = location.longitude
                if (first) {
                    cameraPosition = CameraPosition(
                        target = Point(PointObj.myLatitude.value, PointObj.myLongitude.value),
                        zoom = 16f,
                        azimuth = 0f,
                        tilt = 0f
                    )
                    first = false
                }
            }

            override fun onProviderDisabled(provider: String) {
            }

            override fun onProviderEnabled(provider: String) {
                if (ActivityCompat.checkSelfPermission(
                        this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION
                    )
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            }
        }

    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    private fun Main(vm: UserDataViewModel = viewModel()) {
        val coroutineScope = rememberCoroutineScope()
        val name1: MutableState<String> = remember { mutableStateOf("") }
        val email1 = remember { mutableStateOf("") }
        val password1 = remember { mutableStateOf("") }
        val showImage = remember { mutableStateOf(false) }
        val i = ImageBitmap.imageResource(R.drawable.red_heart)
        var img= remember { mutableStateOf(i) }
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showImage.value) {
                    Image(
                        bitmap = img.value,
                        contentDescription = "icon"
                    )
                }
                OutlinedTextField(
                    value = name1.value,
                    onValueChange = { n ->
                        name1.value = n
                    },
                    label = {
                        Text("name")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 5.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
                OutlinedTextField(
                    value = email1.value,
                    onValueChange = { e ->
                        email1.value = e
                    },
                    label = {
                        Text("email")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 5.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                OutlinedTextField(
                    value = password1.value,
                    onValueChange = { p ->
                        password1.value = p
                    },
                    label = {
                        Text("password")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 5.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                Row {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                supa.signUpWithEmail(
                                    name = name1.value,
                                    email1 = email1.value,
                                    password1 = password1.value,
                                    context = this@MainActivity
                                )
                            }
                        },
                    ) {
                        Text(text = "sign up!", fontSize = 20.sp)
                    }
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                supa.signInWithEmail(
                                    email1 = email1.value,
                                    password1 = password1.value,
                                    context = this@MainActivity
                                )
                            }
                        },
                    ) {
                        Text(text = "sign in!", fontSize = 20.sp)
                    }
                    Button(
                        onClick = {
                            coroutineScope.launch{
                                try{
                                    supa.subscribeToChannel(this@MainActivity, coroutineScope)
                                }catch(ex:Exception){
                                    Toast.makeText(this@MainActivity, ex.message.toString(), Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                    ) {
                        Text(text = "sign out!", fontSize = 20.sp)
                    }
                }
            }
        }
    }

    var go = MutableStateOf.go()

    @Composable
    fun f(onClick: () -> Unit){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column {
                OutlinedTextField(
                    value = go.name.value,
                    onValueChange = {text ->
                        go.name.value = text
                    },
                    label = {
                        Text("name")
                    }
                )
                OutlinedTextField(
                    value = go.email.value,
                    onValueChange = {text ->
                        go.email.value = text
                    },
                    label = {
                        Text("email")
                    }
                )
                OutlinedTextField(
                    value = go.pass.value,
                    onValueChange = {text ->
                        go.pass.value = text
                    },
                    label = {
                        Text("password")
                    }
                )
                Button(
                    {
                        Toast.makeText(this@MainActivity, go.pass.value, Toast.LENGTH_SHORT).show()
                    onClick()
                }) {
                    Text("f")
                }
            }
        }
    }

    @Composable
    fun s(o: () -> Unit){
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column {
                OutlinedTextField(
                    value = go.name.value,
                    onValueChange = {
                        go.name.value = it
                    },
                    label = {
                        Text("name")
                    }
                )
                OutlinedTextField(
                    value = go.email.value,
                    onValueChange = {
                        go.email.value = it
                    },
                    label = {
                        Text("email")
                    }
                )
                OutlinedTextField(
                    value = go.pass.value,
                    onValueChange = {
                        go.pass.value = it
                    },
                    label = {
                        Text("pass")
                    }
                )
                Button({o()}) {
                    Text("s")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val supa = SupaBase()
        lifecycleScope.launch {
            var icon = supa.getImageFromStorage()
            storageIcon(icon)
        }
    }
}