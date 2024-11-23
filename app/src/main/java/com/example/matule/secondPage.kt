package com.example.matule

import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.matule.ui.theme.Raleway60016_2B2B2B
import com.example.matule.ui.theme._48B2E7
import com.example.matule.ui.theme._6A6A6A
import com.example.matule.ui.theme._707B81
import com.example.matule.ui.theme._7D848D
import com.example.matule.ui.theme._F7F7F9

const val name = "name"
const val email = "email"
const val password = "password"
const val familia = "familia"
const val address = "address"
const val phone = "phone"
const val search = "search"
var userData = UserDataViewModel.UserData()

@Composable
fun SignIn(
    onClick: () -> Unit,
    registerAccountOnClick: (() -> Unit)? = null,
    forgotPasswordClick: () -> Unit
) {
    val error = remember { mutableStateOf(false) }
    Column {
        SetIconScreen()
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    )
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 20.dp,
                                end = 20.dp,
                            )
                    ) {
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            SetTextScreen(
                                firstText = "Привет!",
                                secondText = "Заполните Свои Данные Или",
                                thirdText = "Продолжите Через Социальные Медиа"
                            )
                            Box(modifier = Modifier.padding(top = 50.dp)) {
                                Column {
                                    SetTextField(
                                        text = "Email",
                                        placeholder = "xyz@gmail.com",
                                        mutableState = userData.email,
                                        keyboardType = KeyboardType.Email,
                                        size = 50,
                                        error = error
                                    )
                                    SetTextField(
                                        text = "Пароль",
                                        placeholder = "******",
                                        mutableState = userData.password,
                                        keyboardType = KeyboardType.Password,
                                        painter = painterResource(R.drawable.eye),
                                        size = 48,
                                        error = error
                                    )
                                }
                            }
                            Recover{
                                forgotPasswordClick()
                            }

                        }
                    }
                    SetButton(s = "Войти", onClick = onClick, error = error)
                }
            }
            SetBottomText(
                firstText = "Вы впервые?",
                secondText = "Создать пользователя",
                registerAccountOnClick = registerAccountOnClick
            )
        }
    }
}

@Composable
fun Recover(text: String = "Восстановить", resetPasswordClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = fontFamilyPoppins,
                fontSize = 12.sp,
                color = _707B81,
                textAlign = TextAlign.Right
            ),
            modifier = Modifier.clickable {
                resetPasswordClick()
            }
        )
    }
}

@Composable
fun SetButton(
    s: String, mutableState: MutableState<Boolean>? = null, checked: Boolean? = null,
    error: MutableState<Boolean> = mutableStateOf(false),
    onClick: () -> Unit,
) {
    if (mutableState != null) {
        if (mutableState.value) {
            SetAlertDialog(mutableState, onClick)
        }
    }
    Button(
        onClick = {
            if (mutableState != null) {
                mutableState.value = true
            } else {
                if (checked != null){
                    if (userData.email.value != "" && userData.password.value != "" && userData.name.value != "" && checked) {
                        if (userData.password.value.length < 8){
                            error.value = true
                            return@Button
                        }
                        onClick()
                        return@Button
                    }
                }
                if (userData.password.value.length < 8){
                    error.value = true
                    return@Button
                }
                onClick()
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 50.dp,
                start = 20.dp,
                end = 20.dp
            ),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonColors(
            contentColor = Color.White,
            containerColor = _48B2E7,
            disabledContentColor = Color.White,
            disabledContainerColor = _48B2E7
        )
    ) {
        Text(
            text = s,
            style = TextStyle(
                fontFamily = fontFamilyRaleway,
                fontSize = 14.sp,
                color = Color.White
            )
        )
    }
}

@Preview
@Composable
private fun t() {
    val t = remember { mutableStateOf(false) }
    SetAlertDialog(t) {

    }
}

@Composable
fun SetAlertDialog(mutableState: MutableState<Boolean>, onClick: () -> Unit) {
    AlertDialog(
        onDismissRequest = {
            mutableState.value = false
            onClick()
        },
        confirmButton = { onClick() },
        icon = {
            Image(
                painter = painterResource(R.drawable.email),
                contentDescription = "emailImg",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .background(_48B2E7)
                    .size(44.dp)
            )
        },
        title = {
            Text(
                text = "Проверьте Ваш Email",
                style = TextStyle(
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = fontFamilyRaleway,
                    color = Color.Black,
                    fontWeight = FontWeight.W700
                ),
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Text(
                text = "Мы Отправили Код Восстановления Пароля На Вашу Электронную Почту.",
                maxLines = 2,
                fontFamily = fontFamilyPoppins,
                color = _707B81,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W400
            )
        }
    )
}

@Composable
fun SetBottomText(
    firstText: String, secondText: String,
    registerAccountOnClick: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp, start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = firstText,
            style = TextStyle(
                fontFamily = fontFamilyRaleway,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = _707B81
            )
        )
        TextButton(
            onClick = {
                registerAccountOnClick?.invoke()
            }
        ) {
            Text(
                text = secondText,
                style = TextStyle(
                    fontFamily = fontFamilyRaleway,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun RegisterAccount(
    onClick: () -> Unit, signInOnClick: (() -> Unit)? = null
) {
    val checked = remember { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.SpaceAround) {
        SetIconScreen()
        SetTextScreen(
            firstText = "Регистрация",
            secondText = "Заполните Свои Данные Или",
            thirdText = "Продолжите Через Социальные Медиа"
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 25.dp)
        ) {
            RegisterData(text = "Ваше имя", userData.name)
            RegisterData(text = "Email", userData.email)
            RegisterData(text = "Пароль", userData.password)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checked.value,
                    onCheckedChange = { state ->
                        checked.value = state
                    },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = _48B2E7,
                        uncheckedColor = Color.Gray,
                        checkedColor = Color.Transparent
                    )
                )
                Text(
                    text = "Даю согласие на обработку персональных данных",
                    maxLines = 2,
                    style = TextStyle(
                        color = _6A6A6A,
                        fontFamily = fontFamilyRaleway,
                        fontSize = 16.sp
                    )
                )
            }
            SetButton("Зарегистрироваться", onClick = onClick, checked = checked.value)

            SetBottomText(
                firstText = "Есть аккаунт?",
                secondText = "Войти",
                registerAccountOnClick = signInOnClick
            )
        }
    }
}

@Composable
fun RegisterData(
    text: String,
    mutableState: MutableState<String>,
    blackText: Boolean = false,
    imageVector: ImageVector? = null
) {
    Column {
        Box(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                SetTextField(
                    text = text,
                    placeholder = "xxxxxxxx",
                    mutableState = mutableState,
                    keyboardType = KeyboardType.Text,
                    size = 50,
                    blackText = blackText,
                    imageVector = imageVector
                )
            }
        }
    }
}

@Composable
fun ForgotPassword(onClick: () -> Unit) {
    val emailText = remember { mutableStateOf("") }
    val mutableState = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SetIconScreen()
        SetTextScreen(
            firstText = "Забыл Пароль",
            secondText = "Введи Свою Учетную Запись",
            thirdText = "Для Свброса"
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            SetTextField(
                text = "",
                placeholder = "xyz@gmail.com",
                mutableState = emailText,
                keyboardType = KeyboardType.Email,
                size = 50
            )
        }
        SetButton("Отправить", mutableState, onClick = onClick)
    }
}

@Composable
fun Verification(onClick: () -> Unit) {
    val firstNum = remember { mutableStateOf("") }
    val secNum = remember { mutableStateOf("") }
    val thirdNum = remember { mutableStateOf("") }
    val fourNum = remember { mutableStateOf("") }
    val fiveNum = remember { mutableStateOf("") }
    val sixNum = remember { mutableStateOf("") }
    val timer = remember { mutableStateOf(30) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                onClick()
            }
    ) {
        SetIconScreen()
        SetTextScreen(
            firstText = "OTP проверка",
            secondText = "Пожалуйста, Проверьте Свою",
            thirdText = "Электронную Почту, Чтобы Увидеть",
            fourthText = "Код Подтверждения"
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "OTP Код",
                style = TextStyle(
                    fontWeight = FontWeight.W600,
                    fontFamily = fontFamilyRaleway,
                    fontSize = 21.sp,
                    color = Color.Black
                )
            )
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TexF(firstNum)
                TexF(secNum)
                TexF(thirdNum)
                TexF(fourNum)
                TexF(fiveNum)
                TexF(sixNum)
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = "Отправить заново",
                style = TextStyle(
                    color = _7D848D,
                    fontFamily = fontFamilyRaleway,
                    fontWeight = FontWeight.W400,
                    fontSize = 12.sp
                ),
                modifier = Modifier.clickable {
                    timer(timer)
                }
            )
            Text(
                text = if (timer.value == 0) {
                    "00:${timer.value}0"
                } else {
                    "00:${timer.value}"
                },
                style = TextStyle(
                    color = _7D848D,
                    fontFamily = fontFamilyRaleway,
                    fontWeight = FontWeight.W400,
                    fontSize = 12.sp
                )
            )
        }
    }
}

@Composable
fun SetTextField(
    text: String,
    placeholder: String,
    mutableState: MutableState<String>,
    painter: Painter? = null,
    keyboardType: KeyboardType,
    size: Int = 0,
    blackText: Boolean = false,
    imageVector: ImageVector? = null,
    error: MutableState<Boolean> = mutableStateOf(false)
) {
    Box(modifier = Modifier.padding(top = 30.dp)) {
        Column {
            Text(
                text = text,
                style = if (blackText) {
                    Raleway60016_2B2B2B
                } else {
                    TextStyle(
                        fontFamily = fontFamilyRaleway,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            )
            TextFieldForNameOrECT(
                placeholder = placeholder,
                mutableState = mutableState,
                painter = painter,
                keyboardType = keyboardType,
                size = size,
                imageVector = imageVector,
                error = error
            )
        }
    }
}

@Composable
fun TextFieldForNameOrECT(
    placeholder: String,
    mutableState: MutableState<String>,
    painter: Painter? = null,
    keyboardType: KeyboardType,
    size: Int = 0,
    imageVector: ImageVector? = null,
    error: MutableState<Boolean> = mutableStateOf(false)
) {
    Box {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(14.dp))
                .background(_F7F7F9)
                .size(size.dp),
            value = mutableState.value,
            onValueChange = { text ->
                mutableState.value = text
            },
            isError = error.value,
            placeholder = {
                if (painter == null) {
                    Text(
                        text = placeholder,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = fontFamilyPoppins,
                            color = _6A6A6A
                        )
                    )
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = placeholder,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = fontFamilyPoppins,
                                color = _6A6A6A
                            )
                        )
                        IconButton(onClick = {

                        }) {
                            Image(
                                painter = painter,
                                contentDescription = "showPassword",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        if (imageVector != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = fontFamilyPoppins,
                        color = _6A6A6A
                    )
                )
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = "showPassword",
                        modifier = Modifier.size(20.dp),
                        tint = _48B2E7
                    )
                }
            }
        }
    }
}

@Composable
fun TexF(mutableState: MutableState<String>) {
    Row {
        TextField(
            value = mutableState.value,
            onValueChange = { text ->
                mutableState.value = text
            },
            modifier = Modifier
                .size(width = 40.dp, height = 100.dp)
                .background(_F7F7F9),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = _F7F7F9,
                unfocusedContainerColor = _F7F7F9,
                focusedIndicatorColor = _F7F7F9,
                unfocusedIndicatorColor = _F7F7F9
            ),
            placeholder = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "0",
                        style = TextStyle(
                            fontFamily = fontFamilyPoppins,
                            fontWeight = FontWeight.W600,
                            fontSize = 18.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        )
    }


}

@Composable
fun SetTextScreen(
    firstText: String,
    secondText: String,
    thirdText: String,
    fourthText: String = ""
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
    ) {
        Text(
            text = firstText,
            style = TextStyle(
                fontFamily = fontFamilyRaleway,
                fontSize = 32.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W700
            )
        )
        Text(
            modifier = Modifier.padding(top = 15.dp),
            text = secondText,
            style = TextStyle(
                fontFamily = fontFamilyPoppins,
                color = _707B81,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            )
        )
        Text(
            text = thirdText,
            style = TextStyle(
                fontFamily = fontFamilyPoppins,
                color = _707B81,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            )
        )
        if (fourthText != "") {
            Text(
                text = fourthText,
                style = TextStyle(
                    fontFamily = fontFamilyPoppins,
                    color = _707B81,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                )
            )
        }
    }
}

@Composable
fun SetIconScreen() {
    IconButton(
        modifier = Modifier.padding(top = 30.dp),
        onClick = {

        }
    ) {
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "iconBack",
            modifier = Modifier
                .clip(shape = CircleShape)
                .background(_F7F7F9),
        )
    }
}

fun timer(mutableState: MutableState<Int>) {
    val timer = object : CountDownTimer(30000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            mutableState.value--
        }

        override fun onFinish() {
            mutableState.value = 0
        }
    }
    timer.start()
}