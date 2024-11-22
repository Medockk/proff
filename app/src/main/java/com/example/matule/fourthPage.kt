package com.example.matule

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.matule.ui.theme.Masiva40012_2B2B2B
import com.example.matule.ui.theme.Masiva40012_707B81
import com.example.matule.ui.theme.Masiva40016_2B2B2B
import com.example.matule.ui.theme.Raleway50016White
import com.example.matule.ui.theme.Raleway60012_48B2E7
import com.example.matule.ui.theme.Raleway60020_2B2B2B
import com.example.matule.ui.theme.Raleway70020White
import com.example.matule.ui.theme._48B2E7
import com.example.matule.ui.theme._F7F7F9
import kotlinx.coroutines.launch

val storyList = listOf(
    "New Shoes",
    "Nike Top Shoes",
    "Nike Air Force",
    "Shoes",
    "Snakers Nike Shoes",
    "Regular Shoes"
)

@Composable
fun Background() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(_48B2E7),
        contentAlignment = Alignment.CenterEnd
    ) {
        Image(
            painter = painterResource(R.drawable.back_ground_for_fourth_page),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(600.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun t() {
    val c = LocalContext.current
    Profile(c, Users()){

    }
}
@Composable
fun SideMenu(
    profileClick: () -> Unit,
    cartClick: () -> Unit,
    favoriteClick: () -> Unit,
    ordersClick: () -> Unit,
    notificationClick: () -> Unit,
    settingClick: () -> Unit,
    signOutClick: () -> Unit
) {
    Background()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, bottom = 100.dp, top = 50.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        ProfileImage(style = Raleway70020White)
        ProfileIcon(
            profileClick = profileClick,
            cartClick = cartClick,
            favoriteClick = favoriteClick,
            ordersClick = ordersClick,
            notificationClick = notificationClick,
            settingClick = settingClick,
            signOutClick = signOutClick
        )
    }
}

@Composable
fun ProfileImage(
    showText: Boolean = true, style: TextStyle,
    center: Boolean = false, img: MutableState<ByteArray?>? = null,
    storageBitMap: Bitmap? = null, data: Users? = null
) {
    val supa = SupaBase()
    Box {
        Column(
            horizontalAlignment = if (center) {
                Alignment.CenterHorizontally
            } else {
                Alignment.Start
            }
        ) {
            if (img != null) { //при выборе изображения
                if (img.value != null) {
                    Image(
                        bitmap = supa.imageToBitMap(img.value).asImageBitmap(),
                        contentDescription = "profile image",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(96.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            } else {
                if (storageBitMap != null) { //загрузка с бд иконки
                    Image(
                        bitmap = storageBitMap.asImageBitmap(),
                        contentDescription = "icon",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(96.dp),
                        contentScale = ContentScale.Crop
                    )
                } else { //вариант по умолчанию
                    Image(
                        painter = painterResource(R.drawable.profile_img),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(96.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Text(
                text = if (showText) {
                    data?.name ?: userData.name.value
                } else {
                    ""
                },
                style = style
            )
        }
    }
}

@Composable
fun ProfileIcon(
    profileClick: () -> Unit,
    cartClick: () -> Unit,
    favoriteClick: () -> Unit,
    ordersClick: () -> Unit,
    notificationClick: () -> Unit,
    settingClick: () -> Unit,
    signOutClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            profileClick()
        }) {
        Icon(
            Icons.Default.Person,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.weight(0.1f)
        )
        Spacer(Modifier.weight(0.01f))
        Text(
            "Профиль", style = Raleway50016White,
            modifier = Modifier.weight(0.89f)
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            cartClick()
        }
    ) {
        Image(
            painter = painterResource(R.drawable.shop_bag2), contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                .size(24.dp)
                .weight(0.1f)
        )
        Spacer(Modifier.weight(0.01f))
        Text(modifier = Modifier.weight(0.89f), text = "Корзина", style = Raleway50016White)
    }
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            favoriteClick()
        }) {
        Image(
            painterResource(R.drawable.unselected_heart),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
                .size(24.dp)
                .weight(0.1f)
        )
        Spacer(Modifier.weight(0.01f))
        Text(
            modifier = Modifier.weight(0.89f), text = "Избранное",
            style = Raleway50016White
        )
    }
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            ordersClick()
        }) {
        Image(
            painterResource(R.drawable.order_car),
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .weight(0.1f)
        )
        Spacer(Modifier.weight(0.01f))
        Text(
            modifier = Modifier.weight(0.89f), text = "Заказы",
            style = Raleway50016White
        )
    }
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            notificationClick()
        }) {
        Icon(
            Icons.Default.Notifications,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .weight(0.1f)
        )
        Spacer(Modifier.weight(0.01f))
        Text(modifier = Modifier.weight(0.89f), text = "Уведомления", style = Raleway50016White)
    }
    Column {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                settingClick()
            }) {
            Icon(
                Icons.Default.Settings,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .weight(0.1f)
            )
            Spacer(Modifier.weight(0.01f))
            Text(
                modifier = Modifier.weight(0.89f), text = "Настройки",
                style = Raleway50016White
            )
        }
        Spacer(
            Modifier
                .padding(end = 30.dp, bottom = 15.dp, top = 15.dp)
                .border(2.dp, _F7F7F9)
                .fillMaxWidth()
                .height(1.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                signOutClick()
            }) {
            Icon(
                painter = painterResource(R.drawable.exid),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .weight(0.1f)
            )
            Spacer(Modifier.weight(0.01f))
            Text(
                modifier = Modifier.weight(0.89f), text = "Выйти",
                style = Raleway50016White
            )
        }
    }
}

@Composable
fun EditProfile(homeOnClick: () -> Unit, resetPasswordClick: () -> Unit) {
    Column {
        val screen = LocalConfiguration.current.screenWidthDp / 1.65
        Box(
            modifier = Modifier
                .width(screen.dp)
                .padding(top = 15.dp)
        ) {
            TopBar(
                painter = painterResource(R.drawable.eye),
                text = "Профиль",
                icon = 1,
                cartScreen = true,
                backOnClick = homeOnClick
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .weight(0.1f),
                contentAlignment = Alignment.BottomCenter
            ) {
                ProfileImage(showText = false, style = Raleway70020White)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(20.dp)
                        .offset(x = 20.dp)
                        .background(_48B2E7, shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.pen),
                        contentDescription = null,
                        modifier = Modifier.size(7.dp),
                        tint = Color.White
                    )
                }
            }
            Box(
                modifier = Modifier.weight(0.5f)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    RegisterData(text = "Ваше Имя", userData.name)
                    RegisterData(text = "Email", userData.email)
                    RegisterData(text = "Пароль", userData.password)

                    Recover("Восстановить пароль", resetPasswordClick)
                    SetButton("Сохранить", onClick = homeOnClick)
                }
            }
            Spacer(Modifier.weight(0.3f))
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Profile(context: Context, data: Users?, backOnClick: () -> Unit) {
    val heightScreen = LocalConfiguration.current.screenHeightDp / 20
    var bitmap: Bitmap? = null
    var width = remember { mutableStateOf(0.dp) }
    var height = remember { mutableStateOf(0.dp) }
    val bytes = remember {
        mutableStateOf<ByteArray?>(null)
    }
    val coroutineScope = rememberCoroutineScope()
    val supa = SupaBase()
    val launcher = LoadImage(bytes)
    Column(
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier.padding(top = heightScreen.dp)
        ) {
            TopBar(
                painter = painterResource(R.drawable.eye),
                text = "Профиль",
                icon = 1,
                secondText = "Готово",
                context = context,
                data = data,
                backOnClick = backOnClick,
                img = bytes
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column {
                if (bytes.value != null) {//выбор изображения
                    ProfileImage(
                        style = Raleway60020_2B2B2B, center = true,
                        img = bytes, data = data
                    )
                } else {//если не выбрали изображения
                    var bitmap1: MutableState<Bitmap?> = remember { mutableStateOf(null) }
                    val cor = remember { mutableStateOf(true) }
                    if (cor.value) { //загрузка с бд
                        coroutineScope.launch {
                            bitmap1.value = supa.getImageFromStorage()
                            cor.value = false
                        }
                    } else { //выставление иконки с бд
                        ProfileImage(
                            style = Raleway60020_2B2B2B, center = true,
                            storageBitMap = storageIcon(), data = data
                        )
                    }
                }
                Text(
                    text = "Изменить фото профиля",
                    style = Raleway60012_48B2E7,
                    modifier = Modifier.clickable {
                        bytes.value = null
                        launcher.launch("image/*")
                    }
                )
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 15.dp)
                    .height(70.dp)
                    .onGloballyPositioned {
                        width.value = it.size.width.dp
                        height.value = it.size.height.dp
                    }) {
                val barCode = BarCode()
                bitmap = barCode.createBarCode(
                    "https://github.com",
                    width.value, height.value
                )
                if (bitmap != null) {
                    Image(
                        bitmap = bitmap!!.asImageBitmap(),
                        contentDescription = "barCode",
                        modifier = Modifier.fillMaxSize()

                    )
                }
            }
            Box(
                modifier = Modifier.padding(bottom = heightScreen.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    RegisterData(
                        text = "Имя", userData.name, true,
                        imageVector = Icons.Default.Check, data = data
                    )
                    RegisterData(
                        text = "Фамилия", userData.familia, true,
                        imageVector = Icons.Default.Check, data = data
                    )
                    RegisterData(
                        text = "Адрес", userData.address, true,
                        imageVector = Icons.Default.Check, data = data
                    )

                    RegisterData(
                        text = "Телефон", userData.phone, true,
                        imageVector = Icons.Default.Check, data = data
                    )

                }
            }
        }
    }
}

var icon: Bitmap? = null
fun storageIcon(bitmap: Bitmap? = null): Bitmap? {
    if (bitmap != null) {
        icon = bitmap
        return null
    } else {
        return icon
    }
}

@Composable
fun LoadImage(bytes: MutableState<ByteArray?>): ManagedActivityResultLauncher<String, Uri?> {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { result ->
        if (result != null) {
            val item = context.contentResolver.openInputStream(result)
            bytes.value = item?.readBytes()
            item?.close()
        }
    }
    return launcher
}

@Composable
fun Search(backOnClick: () -> Unit) {
    BackGround()
    Column {
        val screen = LocalConfiguration.current.screenWidthDp / 1.65
        val screenHeight = LocalConfiguration.current.screenHeightDp / 20
        Box(
            Modifier
                .width(screen.dp)
                .padding(top = screenHeight.dp)
        ) {
            TopBar(
                painter = painterResource(R.drawable.eye),
                text = "Поиск",
                icon = 1,
                cartScreen = true,
                backOnClick = backOnClick
            )
        }
        Box(Modifier.padding(top = (screenHeight / 2).dp)) {
            TopBarSearch(painterResource(R.drawable.microfon))
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            items(storyList) { item ->
                Box(
                    modifier = Modifier.padding(bottom = 15.dp)
                ) {
                    ShowSearchStory(item)
                }
            }
        }
    }
}

@Composable
fun ShowSearchStory(item: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.watch),
            contentDescription = null,
            modifier = Modifier.size(22.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.width(10.dp))
        Text(text = item)

    }
}

@Composable
fun Notification(backOnClick: () -> Unit) {
    val screen = LocalConfiguration.current.screenWidthDp / 1.65
    val screenHeight = LocalConfiguration.current.screenHeightDp / 20
    Column(
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            Modifier
                .width(screen.dp)
                .padding(top = screenHeight.dp)
        ) {
            TopBar(
                painter = painterResource(R.drawable.eye),
                text = "Уведомления",
                icon = 1,
                cartScreen = true,
                backOnClick = backOnClick
            )
        }
        Column(
            modifier = Modifier.padding(bottom = (screenHeight + 50).dp, top = 25.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                items(6) {
                    ShowNotification()
                }
            }
        }
    }
}

@Composable
fun ShowNotification() {
    Box(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .background(_F7F7F9, shape = RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Text(
                    text = "Заголовок",
                    style = Masiva40016_2B2B2B
                )
                Text(
                    text = "Lorem ipsum dolor sit amet consectetur.\n" +
                            "Venenatis pulvinar lobortis risus consectetur \nmorbi egestas id in bibendum. \n" +
                            "Volutpat nulla urna sit sed diam nulla.",
                    modifier = Modifier.padding(top = 8.dp),
                    style = Masiva40012_2B2B2B
                )
            }
            Text(
                text = "27.01.2024, 15:42",
                style = Masiva40012_707B81,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(start = 15.dp, bottom = 15.dp)
            )
        }
    }
}
