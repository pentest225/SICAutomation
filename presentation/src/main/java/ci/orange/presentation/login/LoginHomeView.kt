package ci.orange.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ci.orange.core_ui.ui.theme.SICAutomationTheme
import ci.orange.presentation.R
import ci.orange.presentation.components.ErrorDialog
import ci.orange.presentation.components.LoaderView
import ci.orange.presentation.components.OCIEditText
import ci.orange.presentation.components.OCIFUllButton
import ci.orange.presentation.navigation.RouteList
import com.plcoding.core_ui.LocalSpacing


@Composable
fun LoginHomeView(
    viewModel: LoginRegisterViewModel = hiltViewModel(),
    onNavigate: (route: String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            OCISimpleAppBar()
        }
    ) { paddingValues ->
        if (viewModel.loader) {
            LoaderView()
        }
        if (viewModel.navigateToHomeScreen) {
            onNavigate(RouteList.HOME_SCREEN)
        }
        if (viewModel.errorMessage != null) {
            ErrorDialog(
                title = R.string.error_loading,
                description = viewModel.errorMessage!!,
                actionButtonText = R.string.retry,
                showDialog = viewModel.errorMessage != null,
                onDismiss = viewModel::onShowError,
            ) {}
        }
        if (viewModel.showValidationCode) {
            VerifyOTPDialog(
                login = viewModel.login,
                code = viewModel.validationCode,
                onEditCode = viewModel::onVerifyCodeEdit,
                onValidateCode = viewModel::verificationCode,
                onResendCode = viewModel::sendVerificationCode
            )
        }
        LoginHomeContent(
            login = viewModel.login,
            onLogin = viewModel::onLoginEdit,
            onAction = viewModel::sendVerificationCode,
            isLoginMode = viewModel.isLoginMode,
            onToggleLoginMode = viewModel::onToggleLoginMode,
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            password = viewModel.password,
            onPassword = viewModel::onPasswordEdit
        )
    }
}

@Composable
fun OCISimpleAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(LocalSpacing.current.spaceSmall)
    ) {
        Image(
            painter = painterResource(id = R.drawable.carre_orange),
            contentDescription = "Orange Icon",
            modifier = Modifier.align(
                Alignment.CenterEnd
            )
        )
    }
}

@Preview
@Composable
fun OCISimpleAppBarPreview() {
    SICAutomationTheme {
        OCISimpleAppBar()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginHomeContent(
    modifier: Modifier = Modifier,
    login: String,
    onLogin: (value: String) -> Unit,
    password: String,
    onPassword: (value: String) -> Unit,
    onAction: () -> Unit,
    isLoginMode: Boolean = false,
    onToggleLoginMode: () -> Unit
) {
    val label = if (isLoginMode) "Connexion" else "Inscription"
    val actionLabel = if (isLoginMode) "Se connecter" else "Créer un compte"
    val buttonLabel = "Verifier"
    val spacing = LocalSpacing.current

    Column(modifier = modifier) {
        Text(text = label, style = MaterialTheme.typography.h2)
        Spacer(modifier = Modifier.height(126.dp))
        OCIEditText(
            onChange = onLogin,
            value = login,
            stringPlaceholder = "xxxx@ofirbre.ci",
            modifier = Modifier.fillMaxWidth()
        )
        OCIEditText(
            onChange = onPassword,
            value = password,
            stringPlaceholder = "....",
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password
        )

        Spacer(modifier = Modifier.height(16.dp))
        OCIFUllButton(
            onClick = onAction,
            title = buttonLabel,
        )
        Box(
            modifier = Modifier
                .padding(spacing.spaceSmall)
                .fillMaxWidth()
        ) {
            TextButton(
                onClick = onToggleLoginMode,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Text(text = actionLabel, color = MaterialTheme.colors.primary)
            }
        }
        Spacer(modifier = Modifier.weight(1F))
    }
}

@Preview
@Composable
fun LoginHomeViewPreview() {
    SICAutomationTheme {
        Surface {
            LoginHomeContent(
                login = "patrick@gmail.com",
                onLogin = {},
                onAction = {},
                password = "...",
                onPassword = {},
                onToggleLoginMode = {},
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
        }
    }
}