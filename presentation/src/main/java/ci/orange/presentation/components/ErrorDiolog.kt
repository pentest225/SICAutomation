package ci.orange.presentation.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import ci.orange.presentation.R


@Composable
fun ErrorDialog(
    @DrawableRes errorIcon:Int = R.drawable.ic_error,
    @StringRes title:Int,
    description:String,
    @StringRes actionButtonText:Int,
    showDialog: Boolean = true,
    onDismiss: () -> Unit,
    onDone:()-> Unit,
) {
    if(showDialog){
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = {
                Text(text = stringResource(id = title), style = MaterialTheme.typography.titleMedium)
            },
            text = {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            },
            confirmButton = {
                TextButton(onClick = { onDone() }) {
                    Text(text = stringResource(id = actionButtonText))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(id = R.string.ok))
                }
            },
            icon = {
                Image(
                    painter = painterResource(id = errorIcon),
                    contentDescription = "dialog icon" ,
                    modifier = Modifier.size(55.dp)
                )
            },
            shape = MaterialTheme.shapes.small,
            properties = DialogProperties(dismissOnBackPress = false,dismissOnClickOutside = false)
        )

    }
}