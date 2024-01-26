package com.example.harrypotterapi.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.harrypotterapi.Theme.HarryPotterAPITheme
import com.example.harrypotterapi.model.MainViewModel
import com.example.harrypotterapi.ui.Routes
import com.example.harrypotterapi.ui.components.ErrorView


//Code affiché dans la Preview
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HarryPotterCharactersPreview() {
    HarryPotterAPITheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            HarryPotterCharactersScreen()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HarryPotterCharactersPreviewDark() {
    HarryPotterAPITheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            HarryPotterCharactersScreen()
        }
    }
}

//Composable représentant l'ensemble de l'écran
@Composable
fun HarryPotterCharactersScreen(navController: NavHostController? = null, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    LaunchedEffect("") {
        viewModel.loadCharactersData()
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {


        if (viewModel.runInProgress.value) {
            CircularProgressIndicator()
        }


        if (viewModel.errorMessage.value.isNotBlank()) {
            ErrorView(message = viewModel.errorMessage.value)
        }

        Spacer(Modifier.size(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewModel.myList.size) {

                PictureRowItem(data = viewModel.myList[it], onPictureClick = {
                    navController?.navigate(Routes.HarryPotterScreenDetail.addParam(it))
                })
            }
        }
    }
}