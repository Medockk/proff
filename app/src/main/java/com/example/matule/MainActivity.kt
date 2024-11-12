package com.example.matule

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.clearCompositionErrors
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matule.ui.theme.MatuleTheme
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import java.util.Date
import kotlin.concurrent.timer

const val postgrest = "Users"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            val navController = rememberNavController()
//            NavHost(navController = navController, startDestination = Navigation.firstPage.route){
//                composable(Navigation.firstPage.route){
//                    FirstPage {
//                        navController.navigate(Navigation.onBoard1.route){
//                            popUpTo(Navigation.firstPage.route){
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.onBoard1.route){
//                    OnBoard1{
//                        navController.navigate(Navigation.onBoard2.route){
//                            popUpTo(Navigation.onBoard1.route){
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.onBoard2.route){
//                    OnBoard2{
//                        navController.navigate(Navigation.onBoard3.route){
//                            popUpTo(Navigation.onBoard2.route){
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.onBoard3.route){
//                    OnBoard3 {
//                        navController.navigate(Navigation.SignIn.route){
//                            popUpTo(Navigation.onBoard3.route){
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.SignIn.route){
//                    SignIn{
//                        navController.navigate(Navigation.RegisterAccount.route){
//                            popUpTo(Navigation.onBoard3.route){
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.RegisterAccount.route){
//                    RegisterAccount{
//                        navController.navigate(Navigation.ForgotPassword.route){
//                            popUpTo(Navigation.SignIn.route){
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.ForgotPassword.route){
//                    ForgotPassword{
//                        navController.navigate(Navigation.Verification.route){
//                            popUpTo(Navigation.RegisterAccount.route){
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.Verification.route){
//                    Verification{
//                        navController.navigate(Navigation.Home.route){
//                            popUpTo(Navigation.ForgotPassword.route){
//                                inclusive = true
//                            }
//                        }
//                    }
//                }
//                composable(Navigation.Home.route){
//                    Home{
//
//                    }
//                }
//            }
            Main()
        }
    }

    @Composable
    private fun Main() {
        val coroutineScope = rememberCoroutineScope()
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                {
                    lifecycleScope.launch(Dispatchers.Main){
                        val supa = SupaBase()
                        val data = Users("name", "email", "password")
                        val client = supa.createSupabaseClient()
                        Toast.makeText(this@MainActivity, "inserted", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {

            }
        }
    }
}

@Serializable
data class Users(
    val name: String,
    val email: String,
    val password: String,
)