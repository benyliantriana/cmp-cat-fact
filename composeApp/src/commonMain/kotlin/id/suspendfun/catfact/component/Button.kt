package id.suspendfun.catfact.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import id.suspendfun.catfact.theme.AppColors

@Composable
fun ButtonText(
    textButton: String,
    isEnabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        colors = ButtonDefaults.buttonColors(contentColor = AppColors.BlueSky),
        enabled = isEnabled,
        onClick = onClick,
        modifier = modifier,
    ) {
        TextBody(
            color = AppColors.White,
            text = textButton
        )
    }
}

@Composable
fun IconButtonWithLabel(
    icon: ImageVector,
    label: String,
    contentDescription: String? = null,
    modifier: Modifier = Modifier,
    tintColor: Color = LocalContentColor.current,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick,
        ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp),
            tint = tintColor
        )
        Spacer(Modifier.width(4.dp))
        TextBody(label)
    }
}