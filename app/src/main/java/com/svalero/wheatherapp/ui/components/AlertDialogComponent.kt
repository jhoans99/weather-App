package com.svalero.wheatherapp.ui.components

import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.svalero.wheatherapp.R

@Composable
fun AlertDialogComponent(
    message: String,
    onDismiss:() -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            dismissOnClickOutside = false
        )
    ) {
        Column(
            modifier = Modifier.background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = message)
            Button(onClick = {
               onDismiss()
            }) {
                Text(text = stringResource(id = R.string.text_button_confirm))
            }
        }
    }
}