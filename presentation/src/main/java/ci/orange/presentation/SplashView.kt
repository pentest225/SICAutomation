package ci.orange.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ci.orange.presentation.navigation.RouteList
import com.google.accompanist.themeadapter.material.MdcTheme

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thingclips.smart.api.router.UrlRouter
import com.thingclips.smart.family.main.view.activity.FamilyManageActivity


@Composable
fun SplashScreenView(
    onNavigate: (route: String) -> Unit,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {
    if (viewModel.navigateToHomeScreen) {
        onNavigate(RouteList.HOME_SCREEN)
        viewModel.onNavigate()
    }
    if(viewModel.navigateToLoginScreen){
        onNavigate(RouteList.LOGIN_SCREEN)
        viewModel.onNavigate()
    }
    SplashScreenContent()
}

@Composable
private fun SplashScreenContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}



@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreenContent()
}