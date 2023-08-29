package ci.orange.presentation.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ci.orange.core_ui.ui.theme.LocalTextStyle
import ci.orange.core_ui.ui.theme.SICAutomationTheme
import ci.orange.core_ui.ui.theme.lightGray
import ci.orange.core_ui.ui.theme.orange
import com.plcoding.core_ui.LocalSpacing


@Composable
fun FastActionSection(
    roomsList: List<String>
) {
    Column {
        Text(
            text = "Action rapide",
            style = LocalTextStyle.current.titleMedium,
            modifier = Modifier.padding(
                LocalSpacing.current.spaceSmall
            )
        )

        LazyRow(

            content = {
                items(roomsList) {
                    RoomCardItems(isSelected = it == "Tous", label = it)
                }
            })

    }
}

@Preview
@Composable
fun FastActionPreview() {
    SICAutomationTheme {
        Surface {
            FastActionSection(
                roomsList = listOf(
                    "Tous",
                    "Chambre 1",
                    "Chambre 2",
                    "Bureau",
                    "Cuisine"
                )
            )
        }
    }
}

@Composable
fun RoomCardItems(
    isSelected: Boolean,
    label: String
) {
    Surface(
        color = if (isSelected) orange else lightGray,
        modifier = Modifier.padding(
            LocalSpacing.current.spaceSmall
        ),
        shape = MaterialTheme.shapes.large
    ) {
        Text(
            text = label, style = LocalTextStyle.current.bodySmall, modifier = Modifier.padding(
                LocalSpacing.current.spaceSmall
            )
        )
    }
}

@Preview
@Composable
fun RoomItems() {
    SICAutomationTheme {
        RoomCardItems(isSelected = false, "Chambre 1")
    }
}