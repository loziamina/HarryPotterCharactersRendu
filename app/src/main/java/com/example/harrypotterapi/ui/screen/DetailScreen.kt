package com.example.harrypotterapi.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.amonteiro.a01_supvincia.R
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.harrypotterapi.Theme.HarryPotterAPITheme
import com.example.harrypotterapi.model.MainViewModel


//Code affiché dans la Preview
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DetailScreenPreview() {
    HarryPotterAPITheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {


            DetailScreen(0)
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DetailScreenPreviewDark() {
    HarryPotterAPITheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            DetailScreen(0)
        }
    }
}

//Composable représentant l'ensemble de l'écran
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(position: Int, navController: NavHostController? = null, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val pictureData = viewModel.getFilterListBySearchText().getOrNull(position)

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        Text(
            text = pictureData?.text ?: "Pas de donnée",
            fontSize = 36.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.fillMaxWidth()
        )

        if (pictureData != null) {
            GlideImage(
                model = pictureData.url,
                contentDescription = "une photo de chat",
                loading = placeholder(R.mipmap.ic_launcher_round),
                failure = placeholder(R.mipmap.ic_launcher),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }

        Text(
            text = pictureData?.longText ?: "Pas de donnée",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(16.dp))

        Button(
            onClick = {

                navController?.popBackStack()
            },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Retour")
        }

        Spacer(Modifier.size(8.dp))
    }
}