package com.example.matule

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
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
                 center: Boolean = false, img: MutableState<ByteArray?>? = null,
                 storageBitMap: Bitmap? = null, context: Context? = null) {
    val supa = SupaBase()
    val coroutineScope = rememberCoroutineScope()
    Box {
        Column(horizontalAlignment = if (center){Alignment.CenterHorizontally}
        else{Alignment.Start}) {
            if (img != null) { //при выборе изображения
                if (img.value != null){
                    Image(
                        bitmap = supa.imageToBitMap(img.value).asImageBitmap(),
                        contentDescription = "profile image",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(96.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }else{
                if (storageBitMap != null){ //загрузка с бд иконки
                    Image(
                        bitmap = storageBitMap.asImageBitmap(),
                        contentDescription = "icon",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(96.dp),
                        contentScale = ContentScale.Crop
                    )
                }else{ //вариант по умолчанию
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
fun EditProfile(homeOnClick: () -> Unit){
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
                cartScreen = true,
                backOcClick = homeOnClick
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
                    SetButton("Сохранить", onClick = homeOnClick)
                }
            }
            Spacer(Modifier.weight(0.3f))
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Profile(context: Context){
    val heightScreen = LocalConfiguration.current.screenHeightDp / 20
    var bitmap: Bitmap? = null
    var width = remember { mutableStateOf(0.dp) }
    var height = remember { mutableStateOf(0.dp) }
    var bytes = remember {
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
        ){
            TopBar(
                painter = painterResource(R.drawable.eye),
                text = "Профиль",
                icon = 1,
                secondText = "Готово",
                context = context
            )
        }
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Column {
                if (bytes.value != null){//выбор изображения
                    ProfileImage(style = Raleway60020_2B2B2B, center = true,
                        img = bytes, context = context)
                }else{//если не выбрали изображения
                    var bitmap1: MutableState<Bitmap?> = remember { mutableStateOf(null) }
                    val cor = remember { mutableStateOf(true) }
                    if (cor.value){ //загрузка с бд
                        coroutineScope.launch {
                            bitmap1.value = supa.getImageFromStorage()
                            cor.value = false
                        }
                    }else{ //выставление иконки с бд
                        ProfileImage(style = Raleway60020_2B2B2B, center = true,
                            storageBitMap = storageIcon()
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
            Box(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 15.dp)
                .height(70.dp).onGloballyPositioned {
                    width.value = it.size.width.dp
                    height.value = it.size.height.dp
                }){
                val barCode = BarCode()
                bitmap = barCode.createBarCode("https://github.com",
                    width.value, height.value)
                if (bitmap != null){
                    Image(
                        bitmap = bitmap!!.asImageBitmap(),
                        contentDescription = "barCode",
                        modifier = Modifier.fillMaxSize()

                    )
                }
            }
            Box(
                modifier = Modifier.padding(bottom = heightScreen.dp)
            ){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly) {
                    val familiaText = MutableStateOf.getMutableStateOf(familia)
                    val nameText = MutableStateOf.getMutableStateOf(name)
                    val addressText = MutableStateOf.getMutableStateOf(address)
                    val phone = MutableStateOf.getMutableStateOf(phone)
                    if (nameText != null) {
                        RegisterData(
                            text = "Имя", nameText, true,
                            imageVector = Icons.Default.Check)
                    }
                    if (familiaText != null) {
                        RegisterData(
                            text = "Фамилия", familiaText, true,
                            imageVector = Icons.Default.Check)
                    }
                    if (addressText != null) {
                        RegisterData(
                            text = "Адрес", addressText, true,
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
lateinit var icon: Bitmap
fun storageIcon(bitmap: Bitmap? = null): Bitmap?{
    if (bitmap != null){
        icon = bitmap
        return null
    }else{
        return icon
    }
}

@Composable
fun LoadImage(bytes: MutableState<ByteArray?>) : ManagedActivityResultLauncher<String, Uri?> {
    val supa = SupaBase()
    val context = LocalContext.current

    val coroutineScope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { result ->
        if (result != null){
            val item = context.contentResolver.openInputStream(result)
            bytes.value = item?.readBytes()
            item?.close()
        }
    }
    return launcher
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
