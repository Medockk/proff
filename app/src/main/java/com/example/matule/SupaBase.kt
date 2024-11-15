package com.example.matule

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlin.random.Random

private const val postgrest = "Users"
private const val postEmail = "email"
private const val postPassword = "password"
private const val token = "sbp_3700007c4c2bc66715d549b66647255acfe97c9a"

class SupaBase {
    fun createSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InN2c2dveGdwZGdkeGhqcWVwbWViIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzEzOTQ0NDAsImV4cCI6MjA0Njk3MDQ0MH0.xgs5FumXvayNsztlTayIoRixucmPtzk8P5w-uYcnkgo",
            supabaseUrl = "https://svsgoxgpdgdxhjqepmeb.supabase.co"
        ) {
            install(Postgrest)
            install(Auth)
            install(Functions)
            install(Realtime)
            install(Storage)
        }
    }

    fun insertUserData(
        name: String,
        email: String,
        password: String,
        context: Context,
        lifecycleScope: CoroutineScope
    ) {
        try {
            lifecycleScope.launch {
                val client = createSupabaseClient()
                val insertNewData = Users(
                    name = name, email = email, password = password
                )
                client.postgrest[postgrest].insert(insertNewData)
            }
        }catch (ex: Exception){
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun checkUsers(
        email: String,
        password: String,
        context: Context,
        lifecycleScope: CoroutineScope
    ) {
        try {
            lifecycleScope.launch {
                val client = createSupabaseClient()
                val andCheckedUsers =
                    client.postgrest[postgrest].select(columns = Columns.list("$postEmail, $postPassword")) {
                        filter {
                            and {
                                eq(postEmail, email)
                                eq(postPassword, password)
                            }
                        }
                    }.decodeList<Users>()
                if (andCheckedUsers.isEmpty()) {
                    val checkedUsers =
                        client.postgrest[postgrest].select(columns = Columns.list("$postEmail, $postPassword")) {
                            filter {
                                eq(postEmail, email)
                            }
                        }.decodeList<Users>()
                }
            }
        }catch (ex: Exception){
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun updatePassword(
        password: String,
        context: Context,
        lifecycleScope: CoroutineScope
    ) {
        try {
            lifecycleScope.launch {
                val client = createSupabaseClient()
                val updatingData = Users(password = password)
                client.postgrest[postgrest].update(updatingData) {
                    filter {
                        eq(postPassword, "pppp")
                    }
                }
            }
        }catch (ex: Exception){
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun getData(
        password: String,
        email: String,
        context: Context,
        lifecycleScope: CoroutineScope
    ) {
        try {
            lifecycleScope.launch {
                val clientData = createSupabaseClient()
                val data = clientData.postgrest[postgrest].select() {
                    filter {
                        or {
                            eq(postPassword, password)
                            eq(postEmail, email)
                        }
                    }
                }.decodeList<Users>()
            }
        }catch (ex: Exception){
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun sendOTP(
        email: String,
        context: Context,
        coroutineScope: CoroutineScope
    ) {
        coroutineScope.launch {
            val client = createSupabaseClient()
            try {
                var captchaToken = ""
                for (i in 0..5) {
                    captchaToken += Random.nextInt(0, 10)
                }
                client.auth.verifyEmailOtp(
                    type = OtpType.Email.EMAIL,
                    email = email,
                    token = token
                )
            } catch (ex: Exception) {
                Toast.makeText(context, "${ex.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signUpWithEmail(
        name: String,
        email1: String,
        password1: String,
        context: MainActivity,
        coroutineScope: CoroutineScope
    ) {
        coroutineScope.launch {
            val client = createSupabaseClient()
            try{
                val user = client.auth.signUpWith(Email){
                    email = email1
                    password = password1
                }
                insertUserData(
                    name = name,
                    email = email1,
                    password = password1,
                    context = context,
                    lifecycleScope = coroutineScope
                )
                Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show()
            }catch (ex: Exception){
                Toast.makeText(context, ex.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signInWithEmail(
        email1: String,
        password1: String,
        context: MainActivity,
        coroutineScope: CoroutineScope
    ) {
        coroutineScope.launch {
            try {
                val client = createSupabaseClient()
                client.auth.signInWith(Email){
                    email = email1
                    password = password1
                }
                Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show()
            }catch (ex:Exception){
                Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signOut(
        context: MainActivity,
        coroutineScope: CoroutineScope
    ) {
        coroutineScope.launch {
            try{
                val client = createSupabaseClient()
                client.auth.signOut()
                Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show()
            }catch (ex:Exception){
                Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Serializable
data class Users(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val password: String = "",
)