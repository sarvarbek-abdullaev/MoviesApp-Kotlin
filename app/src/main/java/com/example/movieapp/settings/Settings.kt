package com.example.movieapp.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Settings() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    )
    {
        AddNewButton {  }
    }
}

@Composable
private fun AddNewButton(onClick: () -> Unit) {

    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .height(75.dp)
            .padding(vertical = 16.dp)
    ) {
        Text(
//            text = stringResource(id = R.string.list_delete_all)
            text = ""
        )
    }
}

@Preview
@Composable
fun ComposablePreview() {
    Settings()
}