package com.svalero.wheatherapp.ui.components

import android.widget.ProgressBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ProgressBar() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit){}
            .zIndex(2f)
            .background(Color.Gray)
    ) {
        val progressBarComponent = createRef()
        CircularProgressIndicator(
            modifier = Modifier.constrainAs(progressBarComponent) {
              top.linkTo(parent.top)
              bottom.linkTo(parent.bottom)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
            }
        )
    }
}