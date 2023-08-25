package ci.orange.presentation.login


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import ci.orange.core_ui.ui.theme.LocalTextStyle
import ci.orange.core_ui.ui.theme.SICAutomationTheme
import ci.orange.presentation.R
import ci.orange.presentation.components.HMediumSpacer
import ci.orange.presentation.components.HSmallSpacer
import ci.orange.presentation.components.OCIEditText
import ci.orange.presentation.components.OCIFUllButton
import ci.orange.presentation.components.OCIOutlineButton
import com.plcoding.core_ui.LocalSpacing


@Composable
fun VerifyOTPDialog(
    login: String,
    code: String,
    onEditCode: (String) -> Unit,
    onValidateCode: () -> Unit,
    onResendCode: () -> Unit,
) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        VerifyOTPDialogContent(
            login = login,
            code = code,
            onEditCode = onEditCode,
            onValidateCode = onValidateCode,
            onResendCode = onResendCode
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VerifyOTPDialogContent(
    login: String,
    code: String,
    onEditCode: (String) -> Unit,
    onValidateCode: () -> Unit,
    onResendCode: () -> Unit,
) {
    Surface(shape = MaterialTheme.shapes.medium) {
        Column(modifier = Modifier.padding(LocalSpacing.current.spaceSmall)) {
            Text(text = "Veuillez entrez le code", style = LocalTextStyle.current.titleLarge)
            HMediumSpacer()
            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily(Font(R.font.roboto_light)),
                        fontWeight = FontWeight.W700,
                        fontSize = 18.sp,
                        letterSpacing = 0.5.sp
                    )
                ) {
                    append("Un code OTP vien d'être envoyer la l'addresse ")
                }


                withStyle(
                    style = SpanStyle(
                        fontFamily = FontFamily(Font(R.font.roboto_bold)),
                        fontWeight = FontWeight.W700,
                        fontSize = 18.sp,
                        letterSpacing = 0.5.sp
                    )
                ) {
                    append(login)
                }

            })
            HMediumSpacer()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                OCIEditText(onChange = onEditCode, value = code)
                HMediumSpacer()
                OCIFUllButton(
                    onClick = onValidateCode,
                    title = "Vérifier",
                    modifier = Modifier.width(200.dp)
                )
                HSmallSpacer()
                Text(
                    text = "Vous n'avez pas réçus de code ?",
                    style = MaterialTheme.typography.caption
                )
                HSmallSpacer()
                OCIOutlineButton(
                    onClick = onResendCode,
                    title = "Renvoyer",
                    modifier = Modifier.width(200.dp)
                )
            }


        }
    }
}

@Preview
@Composable
fun VerifyOTPPreview() {
    SICAutomationTheme {
        VerifyOTPDialogContent(
            login = "patrickdagouaga@gmail.com",
            code = "",
            onEditCode = {},
            onResendCode = {},
            onValidateCode = {}
        )
    }
}