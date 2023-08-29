package ci.orange.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ci.orange.core_ui.ui.theme.LocalTextStyle
import ci.orange.core_ui.ui.theme.SICAutomationTheme
import ci.orange.core_ui.ui.theme.gray
import ci.orange.core_ui.ui.theme.green
import ci.orange.core_ui.ui.theme.lightBlue
import ci.orange.core_ui.ui.theme.orange
import ci.orange.core_ui.ui.theme.purple
import ci.orange.presentation.R
import ci.orange.presentation.components.HSmallSpacer
import com.plcoding.core_ui.LocalSpacing

@Composable
fun ScenarioSection(modifier: Modifier) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(text = "Mes scénarios ", style = LocalTextStyle.current.titleMedium,modifier = Modifier.padding(
            LocalSpacing.current.spaceSmall))
        Surface(
            color = gray.copy(alpha = 0.7F),
            modifier = modifier
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            shadowElevation = 2.dp
        ) {
            Row(
                modifier = Modifier.padding(LocalSpacing.current.spaceMedium),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ScenarioItem(
                    isSelected = false,
                    label = "Ajouter",
                    icon = R.drawable.ic_add,
                    color = orange
                )
                ScenarioItem(
                    isSelected = false,
                    label = "Sortie",
                    icon = R.drawable.ic_sleep,
                    color = purple
                )
                ScenarioItem(
                    isSelected = false,
                    label = "Film",
                    icon = R.drawable.ic_movie,
                    color = green
                )
                ScenarioItem(
                    isSelected = true,
                    label = "Domir",
                    icon = R.drawable.ic_sleep,
                    color = lightBlue,
                )
                ScenarioItem(
                    isSelected = false,
                    label = "Nuit",
                    icon = R.drawable.ic_movie,
                    color = gray,
                )
            }
            LazyRow(
                horizontalArrangement = Arrangement.SpaceAround,
                contentPadding = PaddingValues(LocalSpacing.current.spaceMedium),
                content = {
                    item {
                       
                    }
                }
            )
        }
    }
}


@Preview
@Composable
fun ScenarioSectionPreview() {
    SICAutomationTheme {
        Surface() {
            ScenarioSection(modifier = Modifier.fillMaxWidth().padding(6.dp))
        }

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
            modifier = if (isSelected) Modifier

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
                        .padding(LocalSpacing.current.spaceSmall),
                    contentAlignment = Alignment.Center
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