package ci.orange.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.plcoding.core_ui.LocalSpacing

@Composable
fun HSmallSpacer() {
    Spacer(modifier = Modifier.height(LocalSpacing.current.spaceSmall))
}

@Composable
fun HMediumSpacer() {
    Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
}

@Composable
fun HLargeSpacer() {
    Spacer(modifier = Modifier.height(LocalSpacing.current.spaceLarge))
}

@Composable
fun WSmallSpacer() {
    Spacer(modifier = Modifier.width(LocalSpacing.current.spaceSmall))
}

@Composable
fun WMediumSpacer() {
    Spacer(modifier = Modifier.width(LocalSpacing.current.spaceMedium))
}

@Composable
fun WLargeSpacer() {
    Spacer(modifier = Modifier.width(LocalSpacing.current.spaceLarge))
}