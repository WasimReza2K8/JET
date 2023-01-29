package com.jet.feature.search.presentation.view

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun SelectionDialog(
    title: String,
    text: String,
    yesButtonText: String,
    noButtonText: String,
    onConfirm: () -> Unit,
    onDecline: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onDismissRequest.
            // sendEvent(Event.OnDismissButtonClicked)
        },
        title = {
            Text(text = title)
        },
        text = {
            Text(text = text)
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() },
            ) { Text(yesButtonText) }
        },
        dismissButton = {
            TextButton(
                onClick = { onDecline() },
            ) {
                Text(noButtonText, color = Color.Red)
            }
        },
    )
}