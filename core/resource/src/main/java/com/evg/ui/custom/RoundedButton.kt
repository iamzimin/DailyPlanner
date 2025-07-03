package com.evg.ui.custom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.R
import com.evg.ui.theme.AppTheme
import com.evg.ui.theme.DailyPlannerTheme

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    icon: Painter,
    iconColor: Color,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        modifier = modifier.size(50.dp),
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        containerColor = backgroundColor,
    ) {
        Icon(
            modifier = Modifier
                .size(25.dp),
            painter = icon,
            contentDescription = null,
            tint = iconColor,
        )
    }
}

@Preview
@Composable
private fun RoundedButtonPreview() {
    DailyPlannerTheme {
        RoundedButton(
            backgroundColor = AppTheme.colors.secondary,
            icon = painterResource(R.drawable.plus),
            iconColor = AppTheme.colors.text,
            onClick = {},
        )
    }
}