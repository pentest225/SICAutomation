package ci.orange.presentation.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ci.orange.core_ui.ui.theme.SICAutomationTheme
import ci.orange.presentation.home.components.EquipmentGridList
import ci.orange.presentation.home.components.FastActionSection
import ci.orange.presentation.home.components.HeaderCard
import ci.orange.presentation.home.components.ScenarioSection
import ci.orange.presentation.home.components.WeatherCard
import com.plcoding.core_ui.LocalSpacing
import com.thingclips.smart.home.sdk.bean.DashBoardBean

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
        HomeViewContent(modifier = modifier
            .padding(LocalSpacing.current.spaceSmall)
            .fillMaxSize())
    }
}

@Composable
fun HomeViewContent(
    modifier: Modifier,
) {
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
    Surface {
        Column(modifier = modifier.padding(LocalSpacing.current.spaceSmall)) {
            WeatherCard(weatherList = weatherList)
            ScenarioSection(modifier = Modifier.fillMaxWidth())
            FastActionSection(
                roomsList = listOf(
                    "Tous",
                    "Chambre 1",
                    "Chambre 2",
                    "Bureau",
                    "Cuisine"
                )
            )
            EquipmentGridList()
        }
    }
}

@Preview
@Composable
fun HomeVewPreview() {
    SICAutomationTheme {
        HomeViewContent(modifier = Modifier.padding(LocalSpacing.current.spaceSmall))
    }
}



