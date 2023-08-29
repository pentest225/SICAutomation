package ci.orange.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ci.orange.core_ui.ui.theme.LocalTextStyle
import ci.orange.core_ui.ui.theme.SICAutomationTheme
import ci.orange.presentation.R
import com.plcoding.core_ui.LocalSpacing


@Composable
fun EquipmentGridList(modifier: Modifier = Modifier) {
    Surface(modifier) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 170.dp),
        ) {
            items(10) {
                EquipmentCard(modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Preview
@Composable
fun EquipmentGridPreview() {
    SICAutomationTheme {
        EquipmentGridList()
    }
}

@Composable
fun EquipmentCard(
    modifier: Modifier = Modifier
) {
    Surface(shape = MaterialTheme.shapes.medium, elevation = 2.dp, modifier = modifier) {
        Column(modifier = Modifier.padding(LocalSpacing.current.spaceSmall)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Image(painter = painterResource(id = R.drawable.ic_clim), contentDescription = null)
                Spacer(modifier = Modifier.weight(1F))
                Switch(checked = true, onCheckedChange = {})
            }
            Spacer(modifier = Modifier.weight(1F))
            Text(text = "Télévision", style = LocalTextStyle.current.titleMedium)
        }
    }
}

@Preview
@Composable
fun EquipmentPreview() {
    SICAutomationTheme {
        EquipmentCard(
            modifier = Modifier
                .width(170.dp)
                .height(130.dp)
        )
    }
}