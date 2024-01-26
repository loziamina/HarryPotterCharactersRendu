package com.example.harrypotterapi.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.harrypotterapi.Theme.HarryPotterAPITheme


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ComponentsPreview() {
    HarryPotterAPITheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column {
                ErrorView(message = "Il y a une erreur")
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ComponentsPreviewDark() {
    HarryPotterAPITheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column {
                ErrorView(message = "Il y a une erreur")
            }
        }
    }
}

@Composable
fun ErrorView(modifier: Modifier = Modifier, message:String){
    Text(
        text = message,
        color= MaterialTheme.colorScheme.error,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(8.dp)
    )
}