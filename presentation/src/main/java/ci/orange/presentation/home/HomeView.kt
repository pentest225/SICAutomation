package ci.orange.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString

import androidx.hilt.navigation.compose.hiltViewModel
import ci.orange.presentation.R

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import ci.orange.core_ui.ui.theme.LocalTextStyle
import ci.orange.core_ui.ui.theme.SICAutomationTheme
import ci.orange.core_ui.ui.theme.green
import ci.orange.core_ui.ui.theme.lightBlue
import ci.orange.core_ui.ui.theme.orange
import ci.orange.core_ui.ui.theme.purple
import ci.orange.presentation.components.HMediumSpacer
import ci.orange.presentation.components.HSmallSpacer
import ci.orange.presentation.components.WMediumSpacer
import ci.orange.presentation.components.WSmallSpacer
import com.plcoding.core_ui.LocalSpacing
import com.thingclips.smart.home.sdk.bean.DashBoardBean
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            HeaderCard(modifier = Modifier.padding(LocalSpacing.current.spaceSmall))
        }
    ) { padding ->
        val modifier = Modifier.padding(padding)
        val weatherList = listOf(
            DashBoardBean().apply {
                name = "Temperature"
                value = "34"
                unit = "°C"
            },
            DashBoardBean().apply {
                name = "Consommation"
                value = "34"
                unit = "°C"
            },
            DashBoardBean().apply {
                name = "Humidité"
                value = "34"
                unit = "°C"
            },
        )
        Surface(modifier = modifier.padding(LocalSpacing.current.spaceSmall)) {
            Column() {
                WeatherCard(weatherList = weatherList)
                ScenarioSection()
            }
        }
    }
}

@Composable
fun WeatherItem(
    dashBoardBean: DashBoardBean,
    isLastItem: Boolean = false
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(LocalSpacing.current.spaceSmall),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_thermostat),
                    contentDescription = null,
                    tint = Color.Gray
                )
                Text(
                    text = dashBoardBean.value,
                    style = LocalTextStyle.current.titleLarge,
                    color = Color.White
                )
                Text(
                    text = dashBoardBean.unit,
                    style = LocalTextStyle.current.titleLarge,
                    color = Color.White
                )
            }
            HSmallSpacer()
            Text(
                text = dashBoardBean.name,
                style = LocalTextStyle.current.bodySmall,
                color = Color.Gray
            )
        }
        if (!isLastItem) {
            WMediumSpacer()
            Divider(
                Modifier
                    .height(60.dp)
                    .width(1.dp), color = Color.Gray
            )
            WMediumSpacer()
        }
    }
}

@Composable
fun HeaderCard(
    modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.carre_orange),
                contentDescription = "Default Icon",
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .clip(
                        CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
            WSmallSpacer()
            Column(horizontalAlignment = Alignment.Start) {
                Text(text = "PatrickDagouaga", style = LocalTextStyle.current.titleLarge)
                Text(text = "28/07/2023", style = LocalTextStyle.current.bodySmall)
            }
        }
//        Icon(painter = painterResource(id = R.drawable.ic_notification), contentDescription = "Notification icon")
    }
}

@Composable
fun ScenarioSection() {
    Surface(color = MaterialTheme.colorScheme.surface, modifier = Modifier
        .fillMaxWidth()
        .padding(
            LocalSpacing.current.spaceMedium
        )) {
        LazyRow(content = {
            item {
                ScenarioItem(label = "Ajouter", icon = R.drawable.ic_add, color = orange)
            }
            item {
                ScenarioItem(label = "Sortie", icon = R.drawable.ic_neght, color = purple, isSelected = true)
            }
            item {
                ScenarioItem(label = "Film", icon = R.drawable.ic_movie, color = green)
            }
            item {
                ScenarioItem(label = "Dormir", icon = R.drawable.ic_sleep, color = lightBlue)
            }
        })
    }
}



@Preview
@Composable
fun ScenarioSectionPreview() {
    SICAutomationTheme {
        ScenarioSection()
    }
}
@Composable
fun ScenarioItem(
    isSelected: Boolean = false,
    label: String,
    icon: Int,
    color: Color,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            shadowElevation = if (isSelected) 4.dp else 0.dp,
            color = Color.White,
            shape = MaterialTheme.shapes.extraLarge,
            modifier = if(isSelected) Modifier

                .border(width = 1.dp, color, MaterialTheme.shapes.extraLarge) else Modifier

        ) {
            Column(
                modifier = Modifier.padding(LocalSpacing.current.spaceSmall),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color = color.copy(alpha = 0.4F))
                        .padding(LocalSpacing.current.spaceSmall), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier.clip(
                            CircleShape
                        ),
                        tint = color
                    )
                }
                if (isSelected) {
                    HSmallSpacer()
                    Text(text = label, style = LocalTextStyle.current.bodyMedium)
                }

            }
        }

        if (!isSelected) {
            Text(text = label, style = LocalTextStyle.current.bodySmall)
        }
    }
}

@Preview
@Composable
fun ScenarioItemPreview() {
    SICAutomationTheme {
        Surface(color = Color.Gray, modifier = Modifier.padding(LocalSpacing.current.spaceSmall)) {
            ScenarioItem(
                isSelected = false,
                label = "Sortie",
                icon = R.drawable.ic_thermostat,
                color = purple
            )
        }
    }
}

@Preview
@Composable
fun HeaderCardPreview() {
    SICAutomationTheme {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalSpacing.current.spaceSmall)
        ) {
            HeaderCard()
        }

    }
}

@Preview
@Composable
fun WeatherItemPreview() {
    SICAutomationTheme {
        WeatherItem(
            DashBoardBean().apply {
                name = "Temperature"
                value = "34"
                unit = "°C"
            },
        )
    }
}

@Composable
fun WeatherCard(
    weatherList: List<DashBoardBean>
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = Color.Black,
        modifier = Modifier
            .padding(LocalSpacing.current.spaceSmall)
            .fillMaxWidth(),

        ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(LocalSpacing.current.spaceSmall)
        ) {
            weatherList.forEach {
                WeatherItem(it)
            }
        }
    }
}


@Preview
@Composable
fun WeatherCardPreview() {
    val weatherList = listOf(
        DashBoardBean().apply {
            name = "Temperature"
            value = "34"
            unit = "°C"
        },
        DashBoardBean().apply {
            name = "Consommation"
            value = "34"
            unit = "°C"
        },
        DashBoardBean().apply {
            name = "Humidité"
            value = "34"
            unit = "°C"
        },
    )
    SICAutomationTheme {
        WeatherCard(weatherList)
    }
}