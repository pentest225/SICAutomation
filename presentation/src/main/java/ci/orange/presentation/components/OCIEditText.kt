package ci.orange.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.plcoding.core_ui.LocalSpacing
@ExperimentalMaterial3Api
@Composable
fun OCIEditText(
    modifier: Modifier = Modifier,
    @StringRes label:Int? = null,
    stringLabel:String? = null,
    @StringRes placeholder:Int? = null,
    stringPlaceholder:String? = null,
    @StringRes helperText:Int? = null,
    action: ImeAction = ImeAction.Default,
    keyboardType : KeyboardType = KeyboardType.Text,
    leadingIcon:@Composable() (()->Unit)? = null,
    onChange:(String)->Unit,
    value:String,
) {
    val spacer = LocalSpacing.current
    Column(modifier = Modifier.padding(spacer.spaceSmall)) {
        label?.let {
            Text(text = stringLabel?: stringResource(id = label), style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(spacer.spaceSmall))
        }
//        if(helperText != null){
//            Spacer(modifier = Modifier.height(spacer.spaceSmall))
//            PCMInputHelperUI(helperText)
//            Spacer(modifier = Modifier.height(spacer.spaceSmall))
//        }

        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            modifier = modifier,
            textStyle = MaterialTheme.typography.bodyMedium,
            isError = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = action,
                keyboardType = keyboardType
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.55f),
                unfocusedBorderColor = Color.Gray),

            placeholder = {
                Text(
                    text = stringPlaceholder?: stringResource(id = placeholder!!),
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
            leadingIcon = leadingIcon,
            shape = MaterialTheme.shapes.medium
        )
    }
}