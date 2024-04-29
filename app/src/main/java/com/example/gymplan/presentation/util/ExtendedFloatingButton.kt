package com.example.gymplan.presentation.util

import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ExtendedButton(text: String, image: ImageVector, buttonColor: Color = Color.Blue, onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = { onClick() },
        icon = { Icon(image, "Extended floating action button.") },
        text = { Text(text = text) },
        elevation = FloatingActionButtonDefaults.elevation(8.dp, 16.dp, 8.dp, 8.dp),
        containerColor = buttonColor,
        contentColor = Color.White,
        modifier = Modifier.width(150.dp)
    )
}