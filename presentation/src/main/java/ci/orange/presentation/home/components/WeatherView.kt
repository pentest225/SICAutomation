package ci.orange.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ci.orange.core_ui.ui.theme.LocalTextStyle
import ci.orange.core_ui.ui.theme.SICAutomationTheme
import ci.orange.presentation.R
import ci.orange.presentation.components.HSmallSpacer
import ci.orange.presentation.components.WMediumSpacer
import com.plcoding.core_ui.LocalSpacing
import com.thingclips.smart.home.sdk.bean.DashBoardBean

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
                    style = LocalTextStyle.current.titleMedium,
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
    weatherList: List<DashBoardBean>,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = Color.Black,
        modifier = modifier
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