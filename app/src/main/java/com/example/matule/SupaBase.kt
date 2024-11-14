package com.example.matule

import androidx.compose.runtime.MutableState
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
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

private const val postgrest = "Users"
private const val postEmail = "email"
private const val postPassword = "password"

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
        name: MutableState<String>,
        email: MutableState<String>,
        password: MutableState<String>,
        lifecycleScope: CoroutineScope
    ) {
        lifecycleScope.launch {
            val client = createSupabaseClient()
            val insertNewData = Users(
                name = name.value, email = email.value, password = password.value
            )
            client.postgrest[postgrest].insert(insertNewData)
        }
    }

    fun checkUsers(
        email: MutableState<String>,
        password: MutableState<String>,
        lifecycleScope: CoroutineScope
    ) {
        lifecycleScope.launch {
            val client = createSupabaseClient()
            val andCheckedUsers =
                client.postgrest[postgrest].select(columns = Columns.list("$postEmail, $postPassword")) {
                    filter {
                        and {
                            eq(postEmail, email.value)
                            eq(postPassword, password.value)
                        }
                    }
                }.decodeList<Users>()
            if (andCheckedUsers.isEmpty()) {
                val checkedUsers =
                    client.postgrest[postgrest].select(columns = Columns.list("$postEmail, $postPassword")) {
                        filter {
                            eq(postEmail, email.value)
                        }
                    }.decodeList<Users>()
            }
        }
    }

    fun updateData(
        password: MutableState<String>,
        lifecycleScope: CoroutineScope
    ) {
        lifecycleScope.launch {
            val client = createSupabaseClient()
            val updatingData = Users(password = password.value)
            client.postgrest[postgrest].update(updatingData) {
                filter {
                    eq(postPassword, "pppp")
                }
            }
        }
    }

    fun getData(
        password: MutableState<String>,
        email: MutableState<String>,
        lifecycleScope: CoroutineScope
    ) {
        lifecycleScope.launch {
            val clientData = createSupabaseClient()
            val data = clientData.postgrest[postgrest].select() {
                filter {
                    or {
                        eq(postPassword, password.value)
                        eq(postEmail, email.value)
                    }
                }
            }.decodeList<Users>()
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