package ci.orange.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ci.orange.core_ui.ui.theme.LocalTextStyle
import ci.orange.core_ui.ui.theme.SICAutomationTheme
import ci.orange.presentation.R
import ci.orange.presentation.components.WSmallSpacer
import com.plcoding.core_ui.LocalSpacing


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
                    .height(40.dp)
                    .width(40.dp)
                    .clip(
                        CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
            WSmallSpacer()
            Column(horizontalAlignment = Alignment.Start) {
                Text(text = "Patrick Dagouaga", style = LocalTextStyle.current.titleMedium)
                Text(text = "28/07/2023", style = LocalTextStyle.current.bodySmall)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(painter = painterResource(id = R.drawable.ic_notification), contentDescription = "Notification icon")
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