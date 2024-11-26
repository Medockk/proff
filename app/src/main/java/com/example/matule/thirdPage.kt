package com.example.matule

import android.content.Context
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.matule.ui.theme.Raleway70015_48B2E7
import com.example.matule.ui.theme.Raleway70020White
import com.example.matule.ui.theme._000000
import com.example.matule.ui.theme._000000_33
import com.example.matule.ui.theme._0D6EFD
import com.example.matule.ui.theme._2363F6_100
import com.example.matule.ui.theme._2B2B2B
import com.example.matule.ui.theme._48B2E7
import com.example.matule.ui.theme._674DC5
import com.example.matule.ui.theme._6A6A6A
import com.example.matule.ui.theme._707B81
import com.example.matule.ui.theme._D9D9D966_40
import com.example.matule.ui.theme._DFEFFF
import com.example.matule.ui.theme._F7F7F9
import com.example.matule.ui.theme._F87265
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.DrivingOptions
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.directions.driving.DrivingRouterType
import com.yandex.mapkit.directions.driving.DrivingSession.DrivingRouteListener
import com.yandex.mapkit.directions.driving.VehicleOptions
import com.yandex.mapkit.geometry.SubpolylineHelper
import com.yandex.mapkit.transport.TransportFactory
import com.yandex.mapkit.transport.masstransit.FilterVehicleTypes
import com.yandex.mapkit.transport.masstransit.FitnessOptions
import com.yandex.mapkit.transport.masstransit.Route
import com.yandex.mapkit.transport.masstransit.RouteOptions
import com.yandex.mapkit.transport.masstransit.SectionMetadata.SectionData
import com.yandex.mapkit.transport.masstransit.Session.RouteListener
import com.yandex.mapkit.transport.masstransit.TimeOptions
import com.yandex.mapkit.transport.masstransit.TransitOptions
import com.yandex.mapkit.transport.masstransit.Transport
import com.yandex.runtime.Error
import com.yandex.runtime.network.NetworkError
import com.yandex.runtime.network.RemoteError
import kotlinx.coroutines.launch
import ru.sulgik.mapkit.compose.MapEffect
import ru.sulgik.mapkit.compose.Placemark
import ru.sulgik.mapkit.compose.YandexMap
import ru.sulgik.mapkit.compose.bindToLifecycleOwner
import ru.sulgik.mapkit.compose.rememberAndInitializeMapKit
import ru.sulgik.mapkit.compose.rememberCameraPositionState
import ru.sulgik.mapkit.compose.rememberPlacemarkState
import ru.sulgik.mapkit.geometry.Point
import ru.sulgik.mapkit.geometry.toNative
import ru.sulgik.mapkit.map.CameraPosition
import ru.sulgik.mapkit.map.ImageProvider
import ru.sulgik.mapkit.map.InputListener
import ru.sulgik.mapkit.map.Map
import ru.sulgik.mapkit.map.MapObject
import ru.sulgik.mapkit.map.MapObjectDragListener
import ru.sulgik.mapkit.map.PolylineMapObject
import ru.sulgik.mapkit.map.fromResource
import ru.sulgik.mapkit.map.toCommon

@Composable
fun Home(
    favoriteOnClick: (() -> Unit),
    myCartOnClick: (() -> Unit),
    sideMenuClick: () -> Unit,
    ordersOnClick: () -> Unit,
    editProfileClick: () -> Unit,
    outdoorClick: () -> Unit,
    searchClick: () -> Unit,
    showDetailsClick: () -> Unit,
    popularClick: () -> Unit,
    cartClick: () -> Unit
) {
    BackGround()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(
            painter = painterResource(R.drawable.profile),
            text = "Главная",
            painter2 = painterResource(R.drawable.shop_bag),
            firstPage = true,
            sideMenuClick = sideMenuClick,
            cartClick = cartClick
        )
        TopBarSearch(searchClick = searchClick)
        Categories {
            outdoorClick()
        }
        TextAll("Популярное", popularClick = popularClick)
        BootCard(
            whitePlus = painterResource(R.drawable.white_plus),
            whiteCart = painterResource(R.drawable.white_cart),
            showDetailsClick = showDetailsClick
        )
        TextAll("Акции", popularClick = popularClick)
        CardAction()
        BottomVector(
            true, favoriteOnClick = favoriteOnClick,
            myCartOnClick = myCartOnClick, ordersOnClick = ordersOnClick,
            editProfileClick = editProfileClick
        )
    }
}

@Composable
fun Favorite(
    homeOnClick: (() -> Unit),
    myCartOnClick: (() -> Unit),
    backOnClick: () -> Unit,
    ordersOnClick: () -> Unit,
    editProfileClick: () -> Unit,
    showDetailsClick: () -> Unit
) {
    val b = LocalConfiguration.current.screenHeightDp / 8
    BackGround()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp, bottom = b.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            TopBar(
                painter = painterResource(R.drawable.eye),
                text = "Избранное",
                painter2 = painterResource(R.drawable.unselected_heart),
                icon = 1,
                backOnClick = backOnClick,
                favorite = true
            )
            Column(
                Modifier.padding(top = 20.dp)
            ) {
                BootCard(
                    whitePlus = painterResource(R.drawable.white_plus),
                    whiteCart = painterResource(R.drawable.white_cart),
                    showDetailsClick = showDetailsClick
                )
                Spacer(Modifier.padding(bottom = 10.dp))
                BootCard(
                    whiteCart = painterResource(R.drawable.white_cart),
                    showDetailsClick = showDetailsClick
                )
                Spacer(Modifier.padding(bottom = 10.dp))
                BootCard(
                    whiteCart = painterResource(R.drawable.white_cart),
                    showDetailsClick = showDetailsClick
                )
                Spacer(Modifier.padding(bottom = 10.dp))
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        BottomVector(
            false,
            homeOnClick = homeOnClick, myCartOnClick = myCartOnClick,
            ordersOnClick = ordersOnClick, editProfileClick = editProfileClick
        )
    }
}

@Composable
fun CardAction() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Summer Sale"
                )
                Text(
                    text = "15% OFF",
                    style = TextStyle(
                        color = _674DC5,
                        fontFamily = fontFamilyFutura,
                        fontWeight = FontWeight.W900,
                        fontSize = 36.sp
                    )
                )
            }
            Image(
                painter = painterResource(R.drawable.boot_sale),
                contentDescription = "bootSale",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp)
            )
        }
    }
}

@Composable
fun BootCard(
    whitePlus: Painter? = null,
    whiteCart: Painter,
    showDetailsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .clickable {
                    showDetailsClick()
                }
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .fillMaxWidth(0.45f),
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(top = 3.dp, start = 9.dp)
                        .clip(CircleShape)
                        .size(28.dp)
                        .background(_F7F7F9),
                ) {
                    Image(
                        painter = painterResource(R.drawable.red_heart),
                        contentDescription = "redHeart",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                    )
                }
                Image(
                    painter = painterResource(R.drawable.boot_image),
                    contentDescription = "bootImg",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )
                Box(
                    Modifier.padding(start = 9.dp)
                ) {
                    Column {
                        Text(
                            text = "BEST SELLER",
                            style = TextStyle(
                                fontFamily = fontFamilyPoppins,
                                fontWeight = FontWeight.W500,
                                fontSize = 12.sp,
                                color = _48B2E7
                            )
                        )
                        Text(
                            text = "Nike Air Max",
                            style = TextStyle(
                                fontFamily = fontFamilyRaleway,
                                fontWeight = FontWeight.W600,
                                fontSize = 16.sp,
                                color = _6A6A6A
                            )
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "₽752.00",
                                style = TextStyle(
                                    fontFamily = fontFamilyPoppins,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 14.sp,
                                    color = Color.Black
                                )
                            )
                            IconButton(
                                {}, modifier = Modifier
                                    .clip(RoundedCornerShape(topStart = 14.dp))
                                    .background(_48B2E7)
                                    .size(35.dp)
                            ) {
                                Image(
                                    painter = whitePlus ?: whiteCart,
                                    contentDescription = "addImg",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(20.dp, 15.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(Modifier.size(10.dp))
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .clickable {
                    showDetailsClick()
                }
        ) {
            Column(
                Modifier
                    .background(Color.White)
                    .fillMaxWidth(0.9f),
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(top = 3.dp, start = 9.dp)
                        .clip(CircleShape)
                        .size(28.dp)
                        .background(_F7F7F9),
                ) {
                    Image(
                        painter = painterResource(R.drawable.red_heart),
                        contentDescription = "redHeart",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                    )
                }
                Image(
                    painter = painterResource(R.drawable.boot_image),
                    contentDescription = "bootImg",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )
                Box(
                    Modifier.padding(start = 9.dp)
                ) {
                    Column {
                        Text(
                            text = "BEST SELLER",
                            style = TextStyle(
                                fontFamily = fontFamilyPoppins,
                                fontWeight = FontWeight.W500,
                                fontSize = 12.sp,
                                color = _48B2E7
                            )
                        )
                        Text(
                            text = "Nike Air Max",
                            style = TextStyle(
                                fontFamily = fontFamilyRaleway,
                                fontWeight = FontWeight.W600,
                                fontSize = 16.sp,
                                color = _6A6A6A
                            )
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "₽752.00",
                                style = TextStyle(
                                    fontFamily = fontFamilyPoppins,
                                    fontWeight = FontWeight.W500,
                                    fontSize = 14.sp,
                                    color = Color.Black
                                )
                            )
                            IconButton(
                                {}, modifier = Modifier
                                    .clip(RoundedCornerShape(topStart = 14.dp))
                                    .background(_48B2E7)
                                    .size(35.dp)
                            ) {
                                Image(
                                    painter = whiteCart,
                                    contentDescription = "addImg",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(20.dp, 15.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TextAll(text: String, popularClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontFamily = fontFamilyRaleway,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = Color.Black
            )
        )
        Text(
            text = "Всё",
            style = TextStyle(
                fontFamily = fontFamilyPoppins,
                fontWeight = FontWeight.W500,
                fontSize = 12.sp,
                color = _48B2E7
            ),
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                popularClick()
            }
        )
    }
}

@Composable
fun Categories(
    selectedOutdoorIcon: Boolean = false,
    outdoorClick: (() -> Unit)? = null
) {
    val f0 = remember { mutableStateOf(false) }
    val f1 = remember { mutableStateOf(false) }
    val f2 = remember { mutableStateOf(false) }
    val f3 = remember { mutableStateOf(false) }

    if (selectedOutdoorIcon) {
        f0.value = false
        f1.value = true
        f2.value = false
        f3.value = false
    }
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 20.dp)
    ) {
        Text(
            text = "Категории",
            style = TextStyle(
                fontFamily = fontFamilyRaleway,
                fontWeight = FontWeight.W600,
                fontSize = 16.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 15.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(2.dp)
                    .background(
                        if (f0.value) {
                            _48B2E7
                        } else {
                            Color.White
                        }
                    )
                    .size(108.dp, 40.dp)
                    .clickable {
                        f0.value = true
                        f1.value = false
                        f2.value = false
                        f3.value = false
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Все",
                    color = if (f0.value) {
                        Color.White
                    } else {
                        Color.Black
                    }
                )
            }
            Spacer(Modifier.width(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(2.dp)
                    .background(
                        if (f1.value) {
                            _48B2E7
                        } else {
                            Color.White
                        }
                    )
                    .size(108.dp, 40.dp)
                    .clickable {
                        f0.value = false
                        f1.value = true
                        f2.value = false
                        f3.value = false
                        outdoorClick?.invoke()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Outdoor",
                    color = if (f1.value) {
                        Color.White
                    } else {
                        Color.Black
                    }
                )

            }
            Spacer(Modifier.width(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(2.dp)
                    .background(
                        if (f2.value) {
                            _48B2E7
                        } else {
                            Color.White
                        }
                    )
                    .size(108.dp, 40.dp)
                    .clickable {
                        f0.value = false
                        f1.value = false
                        f2.value = true
                        f3.value = false
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tennis",
                    color = if (f2.value) {
                        Color.White
                    } else {
                        Color.Black
                    }
                )

            }
            Spacer(Modifier.width(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(1.0f)
                    .clip(RoundedCornerShape(8.dp))
                    .shadow(2.dp)
                    .background(
                        if (f3.value) {
                            _48B2E7
                        } else {
                            Color.White
                        }
                    )
                    .size(108.dp, 40.dp)
                    .clickable {
                        f0.value = false
                        f1.value = false
                        f2.value = false
                        f3.value = true
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Running",
                    color = if (f3.value) {
                        Color.White
                    } else {
                        Color.Black
                    }
                )
            }
        }
    }
}

@Composable
fun BackGround() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(_F7F7F9)
    )
}

@Composable
fun TopBar(
    painter: Painter, text: String, painter2: Painter? = null,
    icon: Int? = null, firstPage: Boolean = false, cartScreen: Boolean = false,
    secondText: String? = null, backOnClick: (() -> Unit)? = null, context: Context? = null,
    data: Users? = null, sideMenuClick: (() -> Unit)? = null, img: MutableState<ByteArray?>? = null,
    cartClick: (() -> Unit)? = null, favorite: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                end = if (!cartScreen) {
                    20.dp
                } else {
                    0.dp
                }, start = 20.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            backOnClick?.invoke()
        }) {
            if (icon != null) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .size(44.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.AutoMirrored.Default.KeyboardArrowLeft,
                        contentDescription = "iconBack"
                    )
                }

            } else {
                Image(
                    painter = painter, contentDescription = "imgBack",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            if (sideMenuClick != null) {
                                sideMenuClick()
                            }
                        }
                )
            }
        }
        Text(
            text = text,
            style = TextStyle(
                fontFamily = fontFamilyRaleway,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = if (!firstPage) {
                    FontWeight.W600
                } else {
                    FontWeight.W700
                },
                fontSize = if (!firstPage) {
                    16.sp
                } else {
                    32.sp
                }
            )
        )
        val supa = SupaBase()
        val coroutineScope = rememberCoroutineScope()
        val mutableState = MutableStateOf()
        val name = mutableState.getMutableStateOf(name)!!.value
        val email = mutableState.getMutableStateOf(email)!!.value
        val password = mutableState.getMutableStateOf(password)!!.value
        if (secondText != null) {
            Text(
                text = secondText,
                style = Raleway70015_48B2E7,
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        if (img != null) {
                            supa.storage(
                                iconName = "icon",
                                img = img,
                                context = context!!
                            )
                        }
                        supa.updateUserData(
                            name = name,
                            email = email,
                            password = password,
                            context = context!!,
                            data = data!!
                        )
                    }
                }
            )
        }
        if (painter2 != null) {
            val heart = remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = if (!heart.value) {
                        painter2
                    } else {
                        painterResource(R.drawable.red_heart)
                    }, contentDescription = "shoppingBag",
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            if (cartClick != null) {
                                cartClick()
                            } else {
                                heart.value = !heart.value
                            }
                        },
                    contentScale = ContentScale.Crop
                )
            }

        }
    }
}

@Composable
fun getS(text: String): MutableState<Boolean>? {
    val searchPage = remember { mutableStateOf(false) }
    when (text) {
        enabled -> return searchPage
    }
    return null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSearch(
    painter: Painter? = null,
    searchClick: (() -> Unit)? = null
) {
    val searchPage = getS(enabled)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ElevatedCard(
            onClick = if (searchClick != null) {
                {
                    searchPage!!.value = true
                    searchClick()
                }
            } else {
                {
                    searchPage!!.value = true
                }
            },
            enabled = !searchPage!!.value,
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.elevatedCardElevation(4.dp),
            modifier = Modifier
                .fillMaxWidth(
                    if (painter == null) {
                        0.8f
                    } else {
                        1.0f
                    }
                )
                .clip(shape = RoundedCornerShape(14.dp))
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton({}) {
                            Image(
                                painter = painterResource(R.drawable.search_refactor),
                                contentDescription = "searchIcon",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        val search = remember { mutableStateOf("") }
                        TextField(
                            value = search.value,
                            onValueChange = { search.value = it },
                            enabled = searchPage.value,
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledContainerColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                            placeholder = {
                                Text(
                                    text = "Поиск",
                                    style = TextStyle(
                                        fontFamily = fontFamilyPoppins,
                                        fontWeight = FontWeight.W500,
                                        fontSize = 12.sp,
                                        color = _6A6A6A
                                    )
                                )
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                if (painter != null) {
                    Box(
                        modifier = Modifier
                            .size(26.dp)
                    ) {
                        Row {
                            Spacer(
                                Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(_707B81)
                            )
                            Spacer(Modifier.width(10.dp))
                            Image(
                                painter = painter,
                                contentDescription = null,
                                modifier = Modifier.size(26.dp)
                            )
                        }
                    }
                }
            }
        }
        if (painter == null) {
            Box(
                Modifier
                    .clip(CircleShape)
                    .shadow(6.dp)
                    .background(_48B2E7)
                    .size(52.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(R.drawable.filter),
                        contentDescription = "filter",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(24.dp),
                    )
                }
            }
        }

    }
}

var home = true

@Composable
fun BottomVector(
    selectedHomeIcon: Boolean,
    favoriteOnClick: (() -> Unit)? = null,
    homeOnClick: (() -> Unit)? = null,
    ordersOnClick: (() -> Unit)? = null,
    myCartOnClick: (() -> Unit)? = null,
    editProfileClick: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 45.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier
                    .shadow(24.dp)
                    .clip(CircleShape)
            ) {
                IconButton(
                    {
                        myCartOnClick?.invoke()
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(_48B2E7)
                ) {
                    Image(
                        painter = painterResource(R.drawable.shop_bag2),
                        contentDescription = "shopBag",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
        Box(contentAlignment = Alignment.BottomCenter) {
            Image(
                painter = painterResource(R.drawable.bottom_view),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton({
                        if (!home) {
                            homeOnClick?.invoke()
                            home = true
                        }
                    }) {
                        Image(
                            painter = if (selectedHomeIcon) {
                                painterResource(R.drawable.selected_home)
                            } else {
                                painterResource(R.drawable.unselected_home)
                            },
                            contentDescription = "selectedHome",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(25.dp))
                    IconButton({
                        if (home) {
                            favoriteOnClick?.invoke()
                            home = false
                        }
                    }) {
                        Image(
                            painter = if (selectedHomeIcon) {
                                painterResource(R.drawable.unselected_heart)
                            } else {
                                painterResource(R.drawable.selected_heart)
                            },
                            contentDescription = "unselectedHeart",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton({
                        ordersOnClick?.invoke()
                    }) {
                        Image(
                            painter = painterResource(R.drawable.maybe),
                            contentDescription = "notification",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(25.dp))
                    IconButton({
                        editProfileClick?.invoke()
                    }) {
                        Image(
                            painter = painterResource(R.drawable.people_profile),
                            contentDescription = "profile",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MyCart(backOnClick: (() -> Unit)?, nextClick: () -> Unit) {
    val screenWidth = LocalConfiguration.current.screenWidthDp / 1.65
    BackGround()
    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Box(
            modifier = Modifier
                .width(width = screenWidth.dp)
                .padding(top = 35.dp)
        ) {
            TopBar(
                painter = painterResource(R.drawable.eye),
                text = "Корзина",
                icon = 1,
                cartScreen = true,
                backOnClick = backOnClick
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "3 товара",
            style = TextStyle(
                fontFamily = fontFamilyPoppins,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp,
                color = Color.Black
            )
        )
        val xOffset = remember { mutableStateOf(0) }
        val showAddView = remember { mutableStateOf(false) }
        val showSubView = remember { mutableStateOf(false) }

        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Box(
                modifier = getModifierDraggable(xOffset, showSubView, showAddView),
                contentAlignment = Alignment.Center
            ) {
                PreviewBootCard(
                    painterResource(R.drawable.nike_1),
                    "Nike Club Max",
                    "₽584.95",
                    showAddView = showAddView,
                    showSubView = showSubView,
                )
            }
            Box(
                modifier = Modifier.padding(vertical = 15.dp)
            ) {
                PreviewBootCard(
                    painterResource(R.drawable.nike_2),
                    "Nike Air Max 200",
                    "₽94.05", 1
                )
            }

            Box {
                PreviewBootCard(
                    painterResource(R.drawable.nike_3),
                    "Nike Air Max 270 Essential",
                    "₽74.95", 2
                )
            }
        }
        BottomCartCard("Оформить заказ", btnClick = nextClick)
    }
}

@Composable
fun getModifierDraggable(
    xOffset: MutableState<Int>,
    showSubView: MutableState<Boolean>,
    showAddView: MutableState<Boolean>
): Modifier {
    return Modifier
        .offset(
            x = if (xOffset.value in 1..10) {
                xOffset.value.dp
            } else {
                if (xOffset.value <= 0) {
                    showSubView.value = true
                    showAddView.value = false
                    xOffset.value = -10
                    xOffset.value.dp
                } else {
                    showSubView.value = false
                    showAddView.value = true
                    xOffset.value = 10
                    xOffset.value.dp
                }
            }
        )
        .draggable(
            state = rememberDraggableState { distance ->
                xOffset.value += distance.toInt()
            },
            orientation = Orientation.Horizontal
        )
        .fillMaxWidth()
}

@Composable
fun BottomCartCard(buttonText: String, showAD: Boolean = false, btnClick: () -> Unit) {
    val showAlertDialog = remember { mutableStateOf(false) }
    val count = Count()
    val tip = count.count.value * 584.95 + 94.05 + 74.95
    if (showAlertDialog.value) {
        ShowAlertDialog(showAlertDialog)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp)
        ) {
            Box(
                Modifier.padding(start = 20.dp, end = 20.dp, top = 35.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        SetChequeText("Сумма")
                        SetChequeText(text = "Доставка")
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        SetChequePrice("$tip")
                        SetChequePrice("₽60.20")
                    }
                }
            }
            Image(
                painter = painterResource(R.drawable.cheque_under_scope),
                contentDescription = "ChequeUnderScope",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Итого", style = TextStyle(
                        fontFamily = fontFamilyRaleway,
                        color = _2B2B2B,
                        fontSize = 16.sp, fontWeight = FontWeight.W500
                    )
                )
                Text(
                    text = "${tip + 60.2}",
                    style = TextStyle(
                        fontFamily = fontFamilyPoppins,
                        fontWeight = FontWeight.W500,
                        color = _48B2E7,
                        fontSize = 16.sp
                    )
                )
            }
            Button(
                {
                    if (showAD) {
                        showAlertDialog.value = !showAlertDialog.value
                    } else {
                        btnClick()
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 15.dp),
                colors = ButtonColors(
                    contentColor = Color.White,
                    containerColor = _48B2E7,
                    disabledContentColor = Color.White,
                    disabledContainerColor = _48B2E7
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = buttonText,
                    style = TextStyle(
                        fontFamily = fontFamilyRaleway,
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W500
                    )
                )
            }
        }
    }
}

data class Count(val count: MutableState<Int> = mutableStateOf(1))

@Composable
fun ShowAlertDialog(showAlertDialog: MutableState<Boolean>) {
    AlertDialog(
        containerColor = Color.White,
        onDismissRequest = {
            showAlertDialog.value = false
        },
        confirmButton = {
            Button(
                { showAlertDialog.value = false },
                colors = ButtonColors(
                    contentColor = Color.White,
                    containerColor = _48B2E7,
                    disabledContentColor = Color.White,
                    disabledContainerColor = _48B2E7
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Вернуться к покупкам",
                    style = TextStyle(
                        fontFamily = fontFamilyRaleway,
                        fontWeight = FontWeight(500),
                        fontSize = 16.sp,
                        color = Color.White
                    )
                )
            }
        },
        icon = {
            Box(
                modifier = Modifier
                    .size(134.dp)
                    .clip(CircleShape)
                    .background(_DFEFFF),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.successful),
                    contentDescription = "Successful",
                    modifier = Modifier.size(86.dp)
                )
            }
        },
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Вы успешно",
                    style = TextStyle(
                        fontFamily = fontFamilyRaleway,
                        fontWeight = FontWeight(500),
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )
                Text(
                    text = "оформили заказ",
                    style = TextStyle(
                        fontFamily = fontFamilyRaleway,
                        fontWeight = FontWeight(500),
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )
            }
        }
    )
}

@Composable
fun SetChequePrice(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = fontFamilyPoppins,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            color = _2B2B2B,
            textAlign = TextAlign.End
        ),
        modifier = Modifier.padding(top = 5.dp)
    )
}

@Composable
fun SetChequeText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontFamily = fontFamilyRaleway,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            color = _707B81
        ),
        modifier = Modifier.padding(top = 5.dp)
    )
}

@Composable
fun PreviewBootCard(
    painter: Painter, name: String, price: String,
    item: Int = 0, showAddView: MutableState<Boolean> = mutableStateOf(false),
    showSubView: MutableState<Boolean> = mutableStateOf(false)
) {
    val count = remember { mutableStateOf(1) }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (item == 0) {
            if (showAddView.value && !showSubView.value) {
                Box(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(_48B2E7),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.padding(vertical = 5.dp)
                    ) {
                        IconButton(
                            {
                                count.value++
                                Count(count)
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .size(29.dp)
                        ) {
                            Image(
                                painter = painterResource(R.drawable.white_plus),
                                contentDescription = "add",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                        Text(
                            text = count.value.toString(),
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = fontFamilyRaleway,
                                fontWeight = FontWeight.W400,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier.padding(top = 5.dp)
                        )
                        IconButton({
                            if (count.value > 0 || count.value != 0) {
                                count.value--
                            }
                        }) {
                            Box(
                                modifier = Modifier
                                    .size(14.dp, 1.dp)
                                    .background(Color.White)
                            )
                        }
                    }
                }
                BootCard(painter, name, price, Modifier.fillMaxWidth())
            }
            if (showSubView.value && !showAddView.value) {
                BootCard(painter, name, price, Modifier.fillMaxWidth(0.85f))
                Box {
                    IconButton(
                        {}, modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(_F87265)
                            .padding(vertical = 25.dp)
                    ) {
                        Image(
                            painter = painterResource(R.drawable.white_trash),
                            contentDescription = "delete",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }

    if (item == 1) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BootCard(painter, name, price, Modifier.weight(1f))
            Box(Modifier.padding(start = 10.dp)) {
                IconButton(
                    {}, modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(_F87265)
                        .padding(vertical = 25.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.white_trash),
                        contentDescription = "delete",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
    if (item == 2) {
        BootCard(
            painter = painterResource(R.drawable.nike_3),
            name,
            price,
            Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Composable
fun BootCard(
    painter: Painter, name: String, price: String,
    modifierCard: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .then(modifierCard)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(9.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(_F7F7F9),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter,
                    contentDescription = "imgBoot",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(85.dp)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(end = 5.dp)
            ) {
                Text(
                    text = name,
                    style = TextStyle(
                        fontFamily = fontFamilyRaleway,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        color = Color.Black
                    ),
                    maxLines = 1
                )
                Text(
                    text = price,
                    style = TextStyle(
                        fontFamily = fontFamilyPoppins,
                        fontWeight = FontWeight.W500,
                        fontSize = 14.sp,
                        color = Color.Black
                    ),
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun CheckOut(onClick: () -> Unit, homeOnClick: () -> Unit, backOnClick: (() -> Unit)?) {
    val size = LocalConfiguration.current.screenWidthDp / 1.65
    BackGround()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(modifier = Modifier.width(size.dp)) {
            TopBar(
                painter = painterResource(R.drawable.eye),
                text = "My Cart",
                icon = 1,
                cartScreen = true,
                backOnClick = backOnClick
            )
        }
        ContactInformationCart(onClick)
        BottomCartCard("Подтвердить", true, homeOnClick)
    }
}

@Composable
fun ContactInformationCart(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(horizontal = 14.dp)
            .background(Color.White, shape = RoundedCornerShape(16.dp)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ContactInformation(onClick = onClick)
    }
}

@Composable
fun ContactInformation(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Box {
            Column {
                Text(
                    text = "Контактная информация",
                    style = TextStyle(
                        fontFamily = fontFamilyRaleway,
                        fontWeight = FontWeight.W500,
                        fontSize = 14.sp,
                        color = _2B2B2B
                    )
                )
                Spacer(Modifier.height(15.dp))
                EmailOrName(
                    firstText = "emmanueloyiboke@gmail.com",
                    secondText = "Email"
                )
                Spacer(Modifier.height(15.dp))
                EmailOrName(
                    firstIcon = painterResource(R.drawable.empty_phone),
                    firstText = "+234-811-732-5298",
                    secondText = "Телефон"
                )
            }
        }
        Box {
            OrderAddress(onClick = onClick)
        }
        PayMethod()
    }
}

@Composable
fun PayMethod() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Способ оплаты",
            style = TextStyle(
                fontFamily = fontFamilyRaleway,
                fontWeight = FontWeight(500),
                fontSize = 14.sp,
                color = _2B2B2B
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(_F7F7F9),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.pay_method),
                        contentDescription = "payMethod",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(32.dp, 22.dp)
                    )
                }
                Spacer(Modifier.size(12.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "DbL Card",
                        style = TextStyle(
                            fontFamily = fontFamilyWorkSans,
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp,
                            color = _2B2B2B
                        )
                    )
                    Spacer(Modifier.size(6.dp))
                    Row {
                        Text(
                            text = "**** ****",
                            style = TextStyle(
                                fontFamily = fontFamilyPoppins,
                                fontWeight = FontWeight(500),
                                fontSize = 12.sp,
                                color = _6A6A6A
                            )
                        )
                        Text(
                            text = "0696 4629",
                            style = TextStyle(
                                fontFamily = fontFamilyPoppins,
                                fontWeight = FontWeight(500),
                                fontSize = 12.sp,
                                color = _6A6A6A
                            )
                        )
                    }
                }
            }
            Icon(
                Icons.Default.KeyboardArrowDown, contentDescription = "more",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun EmailOrName(
    firstIcon: Painter? = null, firstText: String,
    secondText: String
) {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (firstIcon == null) {
            Row {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(_F7F7F9),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.MailOutline, contentDescription = "emailImg")
                }
                Spacer(Modifier.size(12.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = firstText,
                        style = TextStyle(
                            fontFamily = fontFamilyPoppins,
                            fontWeight = FontWeight.W500,
                            fontSize = 14.sp,
                            color = _2B2B2B
                        )
                    )
                    Text(
                        text = secondText,
                        style = TextStyle(
                            fontFamily = fontFamilyPoppins,
                            fontWeight = FontWeight.W500,
                            fontSize = 12.sp,
                            color = _6A6A6A
                        ),
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }
            }
        } else {
            Row {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(_F7F7F9),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = firstIcon,
                        contentDescription = "emailImg",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(Modifier.size(12.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = firstText,
                        style = TextStyle(
                            fontFamily = fontFamilyPoppins,
                            fontWeight = FontWeight.W500,
                            fontSize = 14.sp,
                            color = _2B2B2B
                        )
                    )
                    Text(
                        text = secondText,
                        style = TextStyle(
                            fontFamily = fontFamilyPoppins,
                            fontWeight = FontWeight.W500,
                            fontSize = 12.sp,
                            color = _6A6A6A
                        ),
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }
            }
        }
        Box(
            modifier = Modifier.size(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.rename_contact_information),
                contentDescription = "rename",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(15.dp)
            )
        }
    }
}

var first = true

@Composable
fun OrderAddress(onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Адрес",
                        style = TextStyle(
                            fontFamily = fontFamilyRaleway,
                            fontWeight = FontWeight(500),
                            color = _2B2B2B
                        )
                    )
                    Spacer(Modifier.size(12.dp))
                    Text(
                        text = "1082 Аэропорт, Нигерии",
                        style = TextStyle(
                            fontFamily = fontFamilyPoppins,
                            fontWeight = FontWeight(500),
                            fontSize = 12.sp,
                            color = _6A6A6A
                        )
                    )
                }
                Icon(
                    Icons.Default.KeyboardArrowDown, contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(vertical = 12.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.map_btn),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .height(100.dp)
                        .fillMaxWidth()
                        .clickable {
                            onClick()
                        },
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(
                        color = _000000_33,
                        blendMode = BlendMode.Darken
                    ),
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Посмотреть на карте",
                        style = Raleway70020White
                    )
                    Box(
                        modifier = Modifier
                            .background(color = _0D6EFD, shape = CircleShape)
                            .size(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

lateinit var ym: com.yandex.mapkit.map.Map
lateinit var map1: Map

@Composable
fun YandexMapKit(
    latitude: MutableState<Double>, longitude: MutableState<Double>,
    cameraPosition: CameraPosition? = null, context: Context
) {
    val mapLongTapPoint = remember { mutableStateOf(Point(0.0, 0.0)) }
    val r = rememberPlacemarkState(mapLongTapPoint.value)
    var pol: PolylineMapObject? = null
    var selectedPlace: ru.sulgik.mapkit.map.PlacemarkMapObject? = null
    var secondSelectedPoint: ru.sulgik.mapkit.map.PlacemarkMapObject? = null
    var myPlace: ru.sulgik.mapkit.map.PlacemarkMapObject? = null
    var bool = remember { mutableStateOf(false) }

    val systemDarkTheme = isSystemInDarkTheme()
    var startPosition = CameraPosition(
        target = Point(latitude.value, longitude.value),
        zoom = 16f,
        azimuth = 0f,
        tilt = 0f
    )
    if (first && cameraPosition != null) {
        startPosition = cameraPosition
        first = false
    }

    rememberAndInitializeMapKit().bindToLifecycleOwner()

    val cameraPositionState = rememberCameraPositionState { position = startPosition }

    val selectedPoint = ImageProvider.fromResource(context = context, R.drawable.point)
    val selectedPointWhite = ImageProvider.fromResource(context, R.drawable.point_white)
    val img = ImageProvider.fromResource(context, R.drawable.my_location)

    val drag = object : MapObjectDragListener() {
        override fun onMapObjectDrag(mapObject: MapObject, point: Point) {
            if (carPolylineMapObject != null) {
                if (carPolylineMapObject!!.isValid) {
                    try {
                        map1.toNative().mapObjects.remove(carPolylineMapObject as com.yandex.mapkit.map.MapObject)
                        mapLongTapPoint.value = point
                        carRoute(
                            com.yandex.mapkit.geometry.Point(latitude.value, longitude.value),
                            com.yandex.mapkit.geometry.Point(
                                mapLongTapPoint.value.latitude.value,
                                mapLongTapPoint.value.longitude.value
                            ),
                            map1.toNative(),
                            context
                        )

                    } catch (_: Exception) {

                    }
                }
            }
            if (walkPolylineMapObject != null) {
                if (walkPolylineMapObject!!.isValid) {
                    try {
                        map1.toNative().mapObjects.remove(walkPolylineMapObject as com.yandex.mapkit.map.MapObject)
                        mapLongTapPoint.value = point
                        walkRoute(
                            com.yandex.mapkit.geometry.Point(latitude.value, longitude.value),
                            com.yandex.mapkit.geometry.Point(
                                mapLongTapPoint.value.latitude.value,
                                mapLongTapPoint.value.longitude.value
                            ),
                            context,
                            map1.toNative(),
                            systemDarkTheme
                        )

                    } catch (_: Exception) {
                    }
                }
            }

        }

        override fun onMapObjectDragEnd(mapObject: MapObject) {

        }

        override fun onMapObjectDragStart(mapObject: MapObject) {

        }

    }

    Column(modifier = Modifier.fillMaxSize()) {
        YandexMap(
            cameraPositionState = cameraPositionState,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) {
            Placemark(
                state = rememberPlacemarkState(Point(latitude.value, longitude.value)),
                icon = img
            )
            val inputListener = object : InputListener() {
                override fun onMapTap(map: Map, point: Point) {

                }

                override fun onMapLongTap(map: Map, point: Point) {
                    bool.value = true
                    if (pol != null) {
                        map.mapObjects.remove(pol as MapObject)
                    }
                    if (selectedPlace != null) {
                        map1.mapObjects.remove(selectedPlace as MapObject)
                        if (carPolylineMapObject != null && carPolylineMapObject!!.isValid) {
                            map1.toNative().mapObjects.remove(carPolylineMapObject as com.yandex.mapkit.map.MapObject)
                            carRoute(
                                com.yandex.mapkit.geometry.Point(latitude.value, longitude.value),
                                point.toNative(),
                                map.toNative(),
                                context
                            )
                        }
                        if (walkPolylineMapObject != null && walkPolylineMapObject!!.isValid) {
                            map1.toNative().mapObjects.remove(walkPolylineMapObject as com.yandex.mapkit.map.MapObject)
                            walkRoute(
                                com.yandex.mapkit.geometry.Point(latitude.value, longitude.value),
                                point.toNative(), context,
                                map.toNative(), systemDarkTheme
                            )
                        }
                    }

                    mapLongTapPoint.value = point
                    r.geometry = point
                    selectedPlace = map1.mapObjects.addPlacemark().apply {
                        geometry = point
                        if (systemDarkTheme) {
                            setIcon(selectedPointWhite)
                        } else {
                            setIcon(selectedPoint)
                        }
                        isDraggable = true
                        setDragListener(drag)
                    }
//                    lol[1] = r.geometry
//                    var pp = Polyline(lol)
//                    pol = map.mapObjects.addPolyline(pp)
                }
            }
            MapEffect { map: Map ->
                map1 = map.toNative().toCommon()
                ym = map.toNative()
                map.addInputListener(inputListener)
                if (selectedPlace != null) {
                    selectedPlace!!.setDragListener(drag)
                }
                myPlace = map.mapObjects.addPlacemark().apply {
                    setIcon(img)
                }
            }
        }
        Row(
            Modifier.padding(bottom = 20.dp)
        ) {
            Button({
                buttonRemoveRoute()
                if (bool.value) {
                    carRoute(
                        com.yandex.mapkit.geometry.Point(latitude.value, longitude.value),
                        com.yandex.mapkit.geometry.Point(
                            mapLongTapPoint.value.latitude.value,
                            mapLongTapPoint.value.longitude.value
                        ),
                        ym,
                        context
                    )
                }
            }) {
                Text("car route")
            }
            Button(
                {
                    if (bool.value) {
                        buttonRemoveRoute()
                        walkRoute(
                            com.yandex.mapkit.geometry.Point(latitude.value, longitude.value),
                            com.yandex.mapkit.geometry.Point(
                                mapLongTapPoint.value.latitude.value,
                                mapLongTapPoint.value.longitude.value
                            ),
                            context,
                            map1.toNative(),
                            systemDarkTheme
                        )
                    }
                }
            ) {
                Text("walk route")
            }
        }
    }
}

fun buttonRemoveRoute() {
    if (carPolylineMapObject != null) {
        if (carPolylineMapObject!!.isValid) {
            map1.toNative().mapObjects.remove(carPolylineMapObject as com.yandex.mapkit.map.MapObject)
        }
    }
    if (walkPolylineMapObject != null) {
        if (walkPolylineMapObject!!.isValid) {
            map1.toNative().mapObjects.remove(walkPolylineMapObject as com.yandex.mapkit.map.MapObject)
        }
    }
}

var carPolylineMapObject: com.yandex.mapkit.map.PolylineMapObject? = null

fun carRoute(
    startLocation: com.yandex.mapkit.geometry.Point,
    endLocation: com.yandex.mapkit.geometry.Point,
    map: com.yandex.mapkit.map.Map,
    context: Context
) {
    val drRouter = DirectionsFactory.getInstance().createDrivingRouter(DrivingRouterType.COMBINED)
    val drOptions = DrivingOptions()
    val vehicleOptions = VehicleOptions()
    val requestPoint: ArrayList<RequestPoint> = ArrayList()
    requestPoint.add(RequestPoint(startLocation, RequestPointType.WAYPOINT, null, null))
    requestPoint.add(RequestPoint(endLocation, RequestPointType.WAYPOINT, null, null))
    val drListener = object : DrivingRouteListener {
        override fun onDrivingRoutes(p0: MutableList<DrivingRoute>) {
            carPolylineMapObject = map.mapObjects.addPolyline(p0[0].geometry)
            carPolylineMapObject!!.apply {
                strokeWidth = 5f
                setStrokeColor(ContextCompat.getColor(context, R.color.purple_500))
                dashLength = 6f
                gapLength = 6f
            }
        }

        override fun onDrivingRoutesError(p0: Error) {
            val error = "unknown error"
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    drRouter.requestRoutes(requestPoint, drOptions, vehicleOptions, drListener)
}

fun walkRoute(
    startLocation: com.yandex.mapkit.geometry.Point,
    endLocation: com.yandex.mapkit.geometry.Point,
    context: Context,
    map: com.yandex.mapkit.map.Map,
    isSystemDarkTheme: Boolean
) {
    val transitOptions = TransitOptions(FilterVehicleTypes.NONE.value, TimeOptions())
    val avoidStep = false
    val routeOptions = RouteOptions(FitnessOptions(avoidStep))
    val points: MutableList<RequestPoint> = ArrayList()
    points.add(
        RequestPoint(
            startLocation,
            RequestPointType.WAYPOINT,
            null,
            null
        )
    )
    points.add(
        RequestPoint(
            endLocation,
            RequestPointType.WAYPOINT,
            null,
            null
        )
    )
    val mtRouter = TransportFactory.getInstance().createMasstransitRouter()

    val masstransitRouter = object : RouteListener {
        override fun onMasstransitRoutes(p0: MutableList<Route>) {
            if (p0.size > 0) {
                for (section in p0[0].sections) {
                    drawSection(
                        section.metadata.data,
                        SubpolylineHelper.subpolyline(
                            p0[0].geometry, section.geometry
                        ),
                        map,
                        context,
                        isSystemDarkTheme
                    )
                }
            }
        }

        override fun onMasstransitRoutesError(error: Error) {
            var errorMessage: String? = "unknown error message"
            if (error is RemoteError) {
                errorMessage = "remote error message"
            } else if (error is NetworkError) {
                errorMessage = "network error message"
            }

            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

    }
    mtRouter.requestRoutes(points, transitOptions, routeOptions, masstransitRouter)
}

var walkPolylineMapObject: com.yandex.mapkit.map.PolylineMapObject? = null

private fun drawSection(
    data: SectionData, geometry: com.yandex.mapkit.geometry.Polyline,
    map: com.yandex.mapkit.map.Map,
    context: Context,
    isSystemDarkTheme: Boolean
) {
    walkPolylineMapObject = map.mapObjects.addPolyline(geometry)
    walkPolylineMapObject!!.apply {
        strokeWidth = 5f
        setStrokeColor(ContextCompat.getColor(context, R.color.purple_500))
        dashLength = 6f
        gapLength = 6f
    }
    if (data.transports != null) {
        for (transport in data.transports!!) {
            if (transport.line.style != null) {
                if (isSystemDarkTheme) {
                    transport.line.style!!.color!!.or(0xFFFFFFFF.toInt())
                } else {
                    transport.line.style!!.color?.or(-0x1000000)
                }
                return
            }
        }
        val knownVehicleTypes = HashSet<String>()
        knownVehicleTypes.add("bus")
        knownVehicleTypes.add("tramway")
        for (transport in data.transports!!) {
            val sectionVehicleType = getVehicleType(transport, knownVehicleTypes)
            if (sectionVehicleType == "bus") {
                if (isSystemDarkTheme) {
                    walkPolylineMapObject!!.setStrokeColor(0xFF00FF00.toInt()) // light Green
                } else {
                    walkPolylineMapObject!!.setStrokeColor(0xFF287A28.toInt()) // dark green
                }
                return
            } else if (sectionVehicleType == "tramway") {
                if (isSystemDarkTheme) {
                    walkPolylineMapObject!!.setStrokeColor(-0x10000) // light Red
                } else {
                    walkPolylineMapObject!!.setStrokeColor(0xFF942323.toInt()) //dark Red
                }
                return
            }
        }
//            polylineMapObject.setStrokeColor(-0xffff01) // Blue
    } else {
        if (isSystemDarkTheme) {
            walkPolylineMapObject!!.setStrokeColor(0xFFFFFFFF.toInt()) // white
        } else {
            walkPolylineMapObject!!.setStrokeColor(-0x1000000) // Black
        }
    }
}

private fun getVehicleType(transport: Transport, knownVehicleTypes: HashSet<String>): String? {
    for (type in transport.line.vehicleTypes) {
        if (knownVehicleTypes.contains(type)) {
            return type
        }
    }
    return null
}

@Composable
fun Orders(backOnClick: () -> Unit) {
    val screen = LocalConfiguration.current.screenWidthDp / 1.65
    val height = LocalConfiguration.current.screenHeightDp / 1.5
    BackGround()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp)
    ) {
        Box(Modifier.width(screen.dp)) {
            TopBar(
                painter = painterResource(R.drawable.eye),
                text = "Заказы",
                icon = 1,
                cartScreen = true,
                backOnClick = backOnClick
            )
        }
        LazyColumn {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height.dp)
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Недавний",
                        style = TextStyle(
                            fontFamily = fontFamilyRaleway,
                            fontWeight = FontWeight(600),
                            fontSize = 18.sp,
                            color = _2B2B2B
                        )
                    )
                    Recent("7 мин назад")
                    Recent("7 мин назад")
                    Text(
                        text = "Вчера",
                        style = TextStyle(
                            fontFamily = fontFamilyRaleway,
                            fontWeight = FontWeight(600),
                            fontSize = 18.sp,
                            color = _2B2B2B
                        )
                    )
                    Recent("10:23")
                    Recent("18:34")
                }
            }
        }
    }
}

@Composable
fun Recent(time: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(86.dp)
                    .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(_F7F7F9)
            ) {
                Image(
                    painter = painterResource(R.drawable.nike_3),
                    contentDescription = "boot",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(60.dp)
                )
            }
            Spacer(Modifier.size(14.dp))
            Column {
                Text(
                    text = "№ 325556516",
                    style = TextStyle(
                        fontFamily = fontFamilyRaleway,
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp,
                        color = _0D6EFD
                    )
                )
                Text(
                    text = "Nike Air Max 270",
                    style = TextStyle(
                        fontFamily = fontFamilyRaleway,
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp,
                        color = _2B2B2B
                    )
                )
                Text(
                    text = "Essential",
                    style = TextStyle(
                        fontFamily = fontFamilyRaleway,
                        fontWeight = FontWeight(500),
                        fontSize = 14.sp,
                        color = _2B2B2B
                    )
                )
                Row {
                    Text(
                        text = "\$364.95",
                        style = TextStyle(
                            fontFamily = fontFamilyPoppins,
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp,
                            color = _2B2B2B
                        )
                    )
                    Spacer(Modifier.size(20.dp))
                    Text(
                        text = "\$260.00",
                        style = TextStyle(
                            fontFamily = fontFamilyPoppins,
                            fontWeight = FontWeight(500),
                            fontSize = 14.sp,
                            color = _6A6A6A
                        )
                    )
                }
            }
        }
        Text(
            text = time,
            style = TextStyle(
                fontFamily = fontFamilyPoppins,
                fontWeight = FontWeight(500),
                fontSize = 14.sp,
                color = _6A6A6A
            )
        )
    }
}

@Composable
fun Popular(backOnClick: () -> Unit, showDetailsClick: () -> Unit) {
    BackGround()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f)
            .padding(top = 35.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(
            painter = painterResource(R.drawable.eye),
            text = "Популярное",
            painter2 = painterResource(R.drawable.unselected_heart),
            icon = 1,
            backOnClick = backOnClick
        )
        Column(
            Modifier.padding(top = 20.dp)
        ) {
            BootCard(
                whitePlus = painterResource(R.drawable.white_plus),
                whiteCart = painterResource(R.drawable.white_cart),
                showDetailsClick = showDetailsClick
            )
            Spacer(Modifier.padding(bottom = 10.dp))
            BootCard(
                whiteCart = painterResource(R.drawable.white_cart),
                showDetailsClick = showDetailsClick
            )
            Spacer(Modifier.padding(bottom = 10.dp))
            BootCard(
                whiteCart = painterResource(R.drawable.white_cart),
                showDetailsClick = showDetailsClick
            )
            Spacer(Modifier.padding(bottom = 10.dp))
        }
    }
}

@Composable
fun ListingOutDoor(backOnClick: () -> Unit, showDetailsClick: () -> Unit) {
    val screen = LocalConfiguration.current.screenWidthDp / 1.65
    BackGround()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(Modifier.width(screen.dp)) {
            TopBar(
                painter = painterResource(R.drawable.profile),
                text = "Outdoor",
                icon = 1,
                cartScreen = true,
                backOnClick = backOnClick
            )
        }
        Spacer(Modifier.fillMaxHeight(0.03f))
        Categories(true)
        Spacer(Modifier.fillMaxHeight(0.05f))
        LazyColumn(Modifier.padding(bottom = 25.dp)) {
            item {
                BootCard(
                    whitePlus = painterResource(R.drawable.white_plus),
                    whiteCart = painterResource(R.drawable.white_cart),
                    showDetailsClick = showDetailsClick
                )
                Spacer(Modifier.padding(bottom = 10.dp))
                BootCard(
                    whiteCart = painterResource(R.drawable.white_cart),
                    showDetailsClick = showDetailsClick
                )
                Spacer(Modifier.padding(bottom = 10.dp))
                BootCard(
                    whiteCart = painterResource(R.drawable.white_cart),
                    showDetailsClick = showDetailsClick
                )
                Spacer(Modifier.padding(bottom = 10.dp))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun p() {
    Favorite({}, {}, {}, {}, {}) { }
}

@Composable
fun Details(backOnClick: () -> Unit, cartClick: () -> Unit) {
    BackGround()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 35.dp),
    ) {
        TopBar(
            painter = painterResource(R.drawable.profile),
            text = "Sneaker Shop",
            painter2 = painterResource(R.drawable.shop_bag),
            icon = 1,
            backOnClick = backOnClick,
            cartClick = cartClick
        )
        Column(
            Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            InfoAboutSelectedBoot()
            val f0 = remember { mutableStateOf(true) }
            val f1 = remember { mutableStateOf(false) }
            val f2 = remember { mutableStateOf(false) }
            val f3 = remember { mutableStateOf(false) }
            val f4 = remember { mutableStateOf(false) }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.45f)
            ) {
                Image(
                    painter = if (f0.value) {
                        painterResource(R.drawable.nike_7)
                    } else if (f1.value) {
                        painterResource(R.drawable.default_choosed_boot)
                    } else if (f2.value) {
                        painterResource(R.drawable.nike_5)
                    } else if (f3.value) {
                        painterResource(R.drawable.boot_image)
                    } else if (f4.value) {
                        painterResource(R.drawable.nike_6)
                    } else {
                        painterResource(R.drawable.default_choosed_boot)
                    },
                    contentDescription = "sliderBoot",
                    contentScale = if (f4.value) {
                        ContentScale.Inside
                    } else if (f0.value) {
                        ContentScale.Crop
                    } else {
                        ContentScale.Crop
                    },
                    modifier = if (f0.value) {
                        Modifier
                            .fillMaxWidth(0.85f)
                            .padding(bottom = 50.dp)
                    } else if (f1.value) {
                        Modifier.fillMaxWidth(0.9f)
                    } else if (f4.value) {
                        Modifier.fillMaxWidth(0.85f)
                    } else if (f3.value) {
                        Modifier.fillMaxWidth(0.85f)
                    } else {
                        Modifier.fillMaxWidth()
                    }
                )
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier.fillMaxSize()
                ) {
                    ExperimentalSlider()
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .clickable {
                            f0.value = true
                            f1.value = false
                            f2.value = false
                            f3.value = false
                            f4.value = false
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.nike_7),
                        contentDescription = "nike",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(52.dp, 27.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .clickable {
                            f0.value = false
                            f1.value = true
                            f2.value = false
                            f3.value = false
                            f4.value = false
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.nike_4),
                        contentDescription = "nike",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(0.75f)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .clickable {
                            f0.value = false
                            f1.value = false
                            f2.value = true
                            f3.value = false
                            f4.value = false
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.nike_5),
                        contentDescription = "nike",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(0.75f)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .clickable {
                            f0.value = false
                            f1.value = false
                            f2.value = false
                            f3.value = true
                            f4.value = false
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.boot_image),
                        contentDescription = "nike",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(45.dp, 21.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .clickable {
                            f0.value = false
                            f1.value = false
                            f2.value = false
                            f3.value = false
                            f4.value = true
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.nike_6),
                        contentDescription = "nike",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(50.dp, 35.dp)
                    )
                }
            }
            Box {
                Column {
                    Text(
                        text = "Вставка Max Air 270 обеспечивает\n" +
                                " непревзойденный комфорт в течение всего \n" +
                                "дня. Изящный дизайн ........",
                        style = TextStyle(
                            fontFamily = fontFamilyPoppins,
                            fontWeight = FontWeight(400),
                            fontSize = 14.sp,
                            color = _6A6A6A
                        ),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Подробнее",
                        style = TextStyle(
                            fontFamily = fontFamilyPoppins,
                            fontWeight = FontWeight(400),
                            fontSize = 14.sp,
                            color = _0D6EFD,
                            textAlign = TextAlign.End
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            BottomLiked()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperimentalSlider() {
    val sliderState = remember { mutableStateOf(0.7f) }
    val gradient = Brush.verticalGradient(
        listOf(
            _000000,
            _2363F6_100
        )
    )
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = Modifier
            .border(width = 2.dp, brush = gradient, shape = CircleShape)
            .fillMaxWidth()
            .fillMaxHeight(0.45f),
        contentAlignment = Alignment.BottomCenter
    ) {
        Slider(
            value = sliderState.value,
            onValueChange = { sliderState.value = it },
            thumb = {
                SliderDefaults.Thumb(
                    interactionSource = interactionSource,
                    thumbSize = DpSize(39.dp, 18.dp),
                    colors = SliderDefaults.colors(
                        activeTrackColor = Color.Transparent,
                        inactiveTickColor = Color.Transparent,
                        activeTickColor = Color.Transparent,
                        inactiveTrackColor = Color.Transparent,
                        thumbColor = _6A6A6A
                    )
                )
            },
            modifier = Modifier.offset(y = 23.dp),
            colors = SliderDefaults.colors(
                activeTrackColor = Color.Transparent,
                inactiveTickColor = Color.Transparent,
                activeTickColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent,
                thumbColor = _6A6A6A
            ),
        )
    }
}

@Composable
fun InfoAboutSelectedBoot() {
    Column {
        Text(
            text = "Nike Air Max 270\nEssential",
            style = TextStyle(
                fontFamily = fontFamilyRaleway,
                fontWeight = FontWeight(700),
                fontSize = 26.sp,
                color = _2B2B2B
            )
        )
        Text(
            text = "Men’s Shoes",
            style = TextStyle(
                fontFamily = fontFamilyRaleway,
                fontWeight = FontWeight(500),
                fontSize = 16.sp,
                color = _6A6A6A
            ),
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Text(
            text = "₽179.39",
            style = TextStyle(
                fontFamily = fontFamilyPoppins,
                fontWeight = FontWeight(600),
                fontSize = 24.sp,
                color = _2B2B2B
            )
        )
    }
}

@Composable
fun BottomLiked() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(
                    bottom = 40.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(_D9D9D966_40),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = "liked"
                )
            }
            Box(Modifier.fillMaxWidth(0.7f)) {
                Button(
                    {},
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonColors(
                        contentColor = Color.White,
                        containerColor = _48B2E7,
                        disabledContentColor = Color.White,
                        disabledContainerColor = _48B2E7
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.shop_bag2),
                            contentDescription = "addToCart",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = "В корзину",
                            style = TextStyle(
                                fontFamily = fontFamilyRaleway,
                                fontWeight = FontWeight(600),
                                fontSize = 14.sp,
                                color = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}

