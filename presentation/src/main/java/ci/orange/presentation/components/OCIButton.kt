package ci.orange.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun OCIFUllButton(
    onClick:()->Unit,
    isEnable:Boolean = true,
    title:String,
    contentColor:Color = MaterialTheme.colorScheme.onPrimary,
    containerColor:Color = MaterialTheme.colorScheme.primary,
    modifier: Modifier = Modifier,

    ){
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        enabled = isEnable,
        colors =ButtonDefaults.buttonColors(containerColor = containerColor, contentColor = contentColor),
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun OCIDefaultButton(
    onClick:()->Unit,
    title:String,
    isEnable:Boolean = true
){
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        enabled = isEnable
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun OCIOutlineButton(
    onClick:()->Unit,
    title:String,
    modifier: Modifier = Modifier,
    borderColor : Color =  MaterialTheme.colorScheme.onSurface
){
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp,borderColor),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
    ) {
        Text(text = title, style = MaterialTheme.typography.bodyMedium, color = borderColor)
    }
}
