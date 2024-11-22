package com.example.matule

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.media.Image
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.core.content.res.ResourcesCompat
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.resolveAccessToken
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.functions.functions
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import io.github.jan.supabase.realtime.realtime
import io.github.jan.supabase.realtime.selectAsFlow
import io.github.jan.supabase.serializer.KotlinXSerializer
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import io.ktor.client.plugins.websocket.WebSockets
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.io.ByteArrayOutputStream
import kotlin.random.Random

private const val postgrest = "Users"
private const val postEmail = "email"
private const val postPassword = "password"
private const val postName = "name"
private const val token = "sbp_v0_ffc4943eea2b120930ef51b98f004459fa5a650b"
private var accessToken: String? = ""
const val myEmail = "andreev.arsenij2020@gmail.com"
var user = Users()

class SupaBase {
    @OptIn(SupabaseInternal::class)
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
            httpConfig {
                this.install(WebSockets)
            }
            defaultSerializer = KotlinXSerializer(Json)
        }
    }

    suspend fun insertUserData(
        name: String,
        email: String,
        password: String,
        context: Context
    ) {
        try {
            val client = createSupabaseClient()
            val insertNewData = Users(
                name = name, email = email, password = password
            )
            client.postgrest[postgrest].insert(insertNewData)

        } catch (ex: Exception) {
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun checkUsers(
        email: String,
        password: String,
        context: Context
    ) {
        try {
            val client = createSupabaseClient()
            user =
                client.postgrest[postgrest]
                    .select(columns = Columns.list("$postEmail, $postPassword")) {
                        filter {
                            and {
                                eq(postEmail, email)
                                eq(postPassword, password)
                            }
                        }
                    }.decodeSingle<Users>()
            if (user.email.isEmpty() && user.password.isEmpty()) {
                user =
                    client.postgrest[postgrest]
                        .select(columns = Columns.list("$postEmail, $postPassword")) {
                            filter {
                                eq(postEmail, email)
                            }
                        }.decodeSingle<Users>()
            }

        } catch (ex: Exception) {
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun updatePassword(
        password: String,
        context: Context
    ) {
        try {
            val client = createSupabaseClient()
            val updatingData = Users(password = password)
            client.from(postgrest).update(updatingData) {
                filter {
                    eq(postPassword, "pppp")
                }
            }

        } catch (ex: Exception) {
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun getData(
        email: String,
        password: String,
        context: Context
    ): Users? {
        try {
            val clientData = createSupabaseClient()
            val data = clientData.postgrest[postgrest].select {
                filter {
                    or {
                        eq(postPassword, password)
                        eq(postEmail, email)
                    }
                }
            }.decodeSingle<Users>()
            return data
        } catch (_: Exception) {
            Toast.makeText(context, "user data not found", Toast.LENGTH_SHORT).show()
            return null
        }
    }

    @OptIn(SupabaseInternal::class)
    suspend fun sendOTP(
        email: String,
        context: Context
    ) {
        val client = createSupabaseClient()
        try {
            supabaseToken(context = context)
            var captchaToken = ""
            for (i in 0..5) {
                captchaToken += Random.nextInt(0, 10)
            }
            val t = client.resolveAccessToken().toString()
            client.auth.resendEmail(
                type = OtpType.Email.SIGNUP,
                email = email
            )
        } catch (ex: Exception) {
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun signUpWithEmail(
        name: String,
        email1: String,
        password1: String,
        context: MainActivity
    ) {
        val client = createSupabaseClient()
        try {
            val user = client.auth.signUpWith(Email) {
                email = email1
                password = password1
            }
            insertUserData(
                name = name,
                email = email1,
                password = password1,
                context = context
            )
            Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show()
        } catch (ex: Exception) {
            Toast.makeText(context, ex.message, Toast.LENGTH_SHORT).show()
        }

    }

    suspend fun signInWithEmail(
        email1: String,
        password1: String,
        context: MainActivity
    ) {
        try {
            val client = createSupabaseClient()
            client.auth.signInWith(Email) {
                email = email1
                password = password1
            }
            Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show()
        } catch (ex: Exception) {
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    suspend fun signOut(
        context: MainActivity
    ) {
        try {
            val client = createSupabaseClient()
            client.auth.signOut()
            Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show()
        } catch (ex: Exception) {
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }

    }

    suspend fun sendAResetPassword(
        email: String,
        context: Context
    ) {
        try {
            val client = createSupabaseClient()
            client.auth.resetPasswordForEmail(email = email)

            Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show()
        } catch (ex: Exception) {
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun supabaseToken(
        context: Context
    ) {
        try {
            val client = createSupabaseClient()
            accessToken += client.auth.currentAccessTokenOrNull()

        } catch (ex: Exception) {
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }


    //worked!!!!!
    suspend fun storage(
        iconName: String,
        img: MutableState<ByteArray?>,
        context: Context
    ) {
        try {
            val client = createSupabaseClient()
            val bucket = client.storage.from("avatars")
            if (img.value != null) {
                bucket.upload("icon.png", img.value!!) {
                    upsert = true
                }
                Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show()
            }

        } catch (ex: Exception) {
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun imageToByteArray(context: Context, img: Int): ByteArray {
        val bitmap = BitmapFactory.decodeResource(context.resources, img)
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, baos)
        return baos.toByteArray()
    }

    private lateinit var byteArray: ByteArray

    suspend fun getImageFromStorage(): Bitmap {
        val client = createSupabaseClient()
        val bucket = client.storage.from("avatars")
        byteArray = bucket.downloadPublic("icon.png")
        return imageToBitMap(byteArray)
    }


    fun imageToBitMap(byteArray: ByteArray?): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
    }

    @OptIn(SupabaseExperimental::class)
    suspend fun realTime(context: MainActivity) {
        try {
            val client = createSupabaseClient()
            val flow = client.from(postgrest).selectAsFlow(Users::id)
            flow.collect {
                for (i in it) {
                    Log.e("supa", i.name)
                }
            }
        } catch (ex: Exception) {
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun subscribeToChannel(context: Context, coroutineScope: CoroutineScope) {
        val client = createSupabaseClient()
        val channel = client.realtime.channel("Users") {
            //optional config
        }
        val changeFlow = channel.postgresChangeFlow<PostgresAction>(schema = "public")

//Collect the flow
        changeFlow.onEach {
            when (it) {
                is PostgresAction.Delete -> Log.e("Deleted:", "${it.oldRecord}")
                is PostgresAction.Insert -> Log.e("Inserted:", "${it.record}")
                is PostgresAction.Select -> Log.e("Selected:", "${it.record}")
                is PostgresAction.Update -> Log.e("Updated:", "${it.oldRecord} with ${it.record}")
            }
        }.launchIn(coroutineScope) // launch a new coroutine to collect the flow

        channel.subscribe()

    }

    suspend fun updateUserData(
        name: String,
        email: String,
        password: String,
        context: Context,
        data: Users
    ) {
        try {
            val client = createSupabaseClient()
            client.from(postgrest).update(data) {
                filter {
                    and {
                        eq(postName, name)
                        eq(postEmail, email)
                        eq(postPassword, password)
                    }
                }
            }
        } catch (ex: Exception) {
            Toast.makeText(context, ex.message.toString(), Toast.LENGTH_SHORT).show()
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