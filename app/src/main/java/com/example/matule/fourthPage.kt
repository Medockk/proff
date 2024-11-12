package com.example.matule

import android.provider.ContactsContract.Profile
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
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

@Composable
fun SideMenu() {
    Background()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, bottom = 100.dp, top = 50.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        ProfileImage(style = Raleway70020White)
        ProfileIcon()
    }
}

@Composable
fun ProfileImage(showText: Boolean = true, style: TextStyle,
                 center: Boolean = false) {
    Box {
        Column(horizontalAlignment = if (center){Alignment.CenterHorizontally}
        else{Alignment.Start}) {
            Image(
                painter = painterResource(R.drawable.profile_img),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(96.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = if (showText){
                    val name = MutableStateOf.getMutableStateOf(name)
                    name?.value ?: ""
                }else{""},
                style = style
            )
        }
    }
}

@Composable
fun ProfileIcon() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            Icons.Default.Person,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.weight(0.1f)
        )
        Spacer(Modifier.weight(0.01f))
        Text("Профиль", style = Raleway50016White,
            modifier = Modifier.weight(0.89f))
    }
    Row (verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.shop_bag2), contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.size(24.dp)
                .weight(0.1f)
        )
        Spacer(Modifier.weight(0.01f))
        Text(modifier = Modifier.weight(0.89f), text = "Корзина", style = Raleway50016White)
    }
    Row (verticalAlignment = Alignment.CenterVertically) {
        Image(
            painterResource(R.drawable.unselected_heart),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier.size(24.dp)
                .weight(0.1f)
        )
        Spacer(Modifier.weight(0.01f))
        Text(modifier = Modifier.weight(0.89f),text ="Избранное",
            style = Raleway50016White)
    }
    Row(verticalAlignment = Alignment.CenterVertically)  {
        Image(
            painterResource(R.drawable.order_car),
            colorFilter = ColorFilter.tint(Color.White),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
                .weight(0.1f)
        )
        Spacer(Modifier.weight(0.01f))
        Text(modifier = Modifier.weight(0.89f),text ="Заказы",
            style = Raleway50016White)
    }
    Row (verticalAlignment = Alignment.CenterVertically) {
        Icon(
            Icons.Default.Notifications,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
                .weight(0.1f)
        )
        Spacer(Modifier.weight(0.01f))
        Text(modifier = Modifier.weight(0.89f),text ="Уведомления", style = Raleway50016White)
    }
    Column {
        Row (verticalAlignment = Alignment.CenterVertically) {
            Icon(
                Icons.Default.Settings,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
                    .weight(0.1f)
            )
            Spacer(Modifier.weight(0.01f))
            Text(modifier = Modifier.weight(0.89f),text ="Настройки",
                style = Raleway50016White)
        }
        Spacer(
            Modifier
                .padding(end = 30.dp, bottom = 15.dp, top = 15.dp)
                .border(2.dp, _F7F7F9)
                .fillMaxWidth()
                .height(1.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.exid),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
                    .weight(0.1f)
            )
            Spacer(Modifier.weight(0.01f))
            Text(modifier = Modifier.weight(0.89f),text = "Выйти",
                style = Raleway50016White
            )
        }
    }
}

@Composable
fun EditProfile(onClick: () -> Unit){
    Column {
        val screen = LocalConfiguration.current.screenWidthDp / 1.65
        Box(
            modifier = Modifier.width(screen.dp)
                .padding(top = 15.dp)
        ){
            TopBar(
                painter = painterResource(R.drawable.eye),
                text = "Профиль",
                icon = 1,
                cartScreen = true
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier.size(80.dp).weight(0.1f),
                contentAlignment = Alignment.BottomCenter
            ){
                ProfileImage(showText = false, style = Raleway70020White)
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(20.dp)
                        .offset(x = 20.dp)
                        .background(_48B2E7, shape = CircleShape)
                ){
                    Icon(painter = painterResource(R.drawable.pen),
                        contentDescription = null,
                        modifier = Modifier.size(7.dp),
                        tint = Color.White)
                }
            }
            Box(
                modifier = Modifier.weight(0.5f)
            ){
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    val nameText = MutableStateOf.getMutableStateOf(name)
                    val emailText = MutableStateOf.getMutableStateOf(email)
                    val passwordText = MutableStateOf.getMutableStateOf(password)
                    if (nameText != null && emailText != null && passwordText != null){
                        RegisterData(text = "Ваше Имя",nameText)
                        RegisterData(text = "Email",emailText)
                        RegisterData(text = "Пароль", passwordText)
                    }
                    Recover("Восстановить пароль")
                    SetButton("Сохранить", onClick = onClick)
                }
            }
            Spacer(Modifier.weight(0.3f))
        }
    }
}

@Composable
fun Profile(){
    val heightScreen = LocalConfiguration.current.screenHeightDp / 20
    Column {
        Box(
            modifier = Modifier.padding(top = heightScreen.dp)
        ){
            TopBar(
                painter = painterResource(R.drawable.eye),
                text = "Профиль",
                icon = 1,
                secondText = "Готово"
            )
        }
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            ProfileImage(style = Raleway60020_2B2B2B, center = true)
            Text(
                text = "Изменить фото профиля",
                style = Raleway60012_48B2E7
            )
            Box(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 15.dp)
                .background(Color.Blue).height(70.dp))
            Box{
                Column(
                    modifier = Modifier.fillMaxSize()) {
                    val familiaText = MutableStateOf.getMutableStateOf(familia)
                    val nameText = MutableStateOf.getMutableStateOf(name)
                    val addressText = MutableStateOf.getMutableStateOf(address)
                    val phone = MutableStateOf.getMutableStateOf(phone)
                    if (nameText != null) {
                        RegisterData(text = "Имя", nameText, true,
                            imageVector = Icons.Default.Check)
                    }
                    if (familiaText != null) {
                        RegisterData(text = "Фамилия", familiaText, true,
                            imageVector = Icons.Default.Check)
                    }
                    if (addressText != null) {
                        RegisterData(text = "Адрес", addressText, true,
                            imageVector = Icons.Default.Check)
                    }
                    if (phone != null) {
                        RegisterData(
                            text = "Телефон", phone, true,
                            imageVector = Icons.Default.Check)
                    }
                }
            }
        }
    }
}

@Composable
fun Search(){
    BackGround()
    Column {
        val screen = LocalConfiguration.current.screenWidthDp / 1.65
        val screenHeight = LocalConfiguration.current.screenHeightDp / 20
        Box(
            Modifier.width(screen.dp).padding(top = screenHeight.dp)
        ){
            TopBar(
                painter = painterResource(R.drawable.eye),
                text = "Поиск",
                icon = 1,
                cartScreen = true
            )
        }
        Box(Modifier.padding(top = (screenHeight/2).dp)){
            TopBarSearch(painterResource(R.drawable.microfon))
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            items(storyList){item->
                Box(
                    modifier = Modifier.padding(bottom = 15.dp)
                ){
                    ShowSearchStory(item)
                }
            }
        }
    }
}

@Composable
fun ShowSearchStory(item: String) {
    Row(verticalAlignment = Alignment.CenterVertically){
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Notification(){
    val screen = LocalConfiguration.current.screenWidthDp / 1.65
    val screenHeight = LocalConfiguration.current.screenHeightDp / 20
    Column(
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Box(Modifier.width(screen.dp).padding(top = screenHeight.dp)){
            TopBar(
                painter = painterResource(R.drawable.eye),
                text = "Уведомления",
                icon = 1,
                cartScreen = true
            )
        }
        Column(
            modifier = Modifier.padding(bottom = (screenHeight+50).dp, top = 25.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                items(6){
                    ShowNotification()
                }
            }
        }
    }
}

@Composable
fun ShowNotification() {
    Box(
        modifier = Modifier.padding(horizontal = 24.dp)
            .background(_F7F7F9, shape = RoundedCornerShape(24.dp)),
        contentAlignment = Alignment.Center
    ){
        Column {
            Column(modifier = Modifier.fillMaxWidth().padding(15.dp)) {
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
                modifier = Modifier.fillMaxWidth(0.5f).padding(start = 15.dp, bottom = 15.dp)
            )
        }
    }
}
