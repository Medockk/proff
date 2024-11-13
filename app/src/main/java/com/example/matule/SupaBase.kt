package com.example.matule

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage
import kotlinx.serialization.Serializable

class SupaBase {
    fun createSupabaseClient():SupabaseClient{
        return createSupabaseClient(
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InN2c2dveGdwZGdkeGhqcWVwbWViIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzEzOTQ0NDAsImV4cCI6MjA0Njk3MDQ0MH0.xgs5FumXvayNsztlTayIoRixucmPtzk8P5w-uYcnkgo",
            supabaseUrl = "https://svsgoxgpdgdxhjqepmeb.supabase.co"
        ){
            install(Postgrest)
            install(Auth)
            install(Functions)
            install(Realtime)
            install(Storage)
        }
    }
}
@Serializable
data class Client(
    val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
)
@Serializable
data class Users(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val password: String = "",
)