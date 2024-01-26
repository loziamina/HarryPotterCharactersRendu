package com.example.harrypotterapi.ui.screen

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import com.example.harrypotterapi.model.PictureData
import com.example.harrypotterapi.ui.Routes
import com.example.harrypotterapi.ui.components.ErrorView

//Code affiché dans la Preview
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SearchScreenPreview() {
    HarryPotterAPITheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            SearchScreen()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SearchScreenPreviewDark() {
    HarryPotterAPITheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            SearchScreen()
        }
    }
}

//Composable représentant l'ensemble de l'écran
@Composable
fun SearchScreen(navController: NavHostController? = null, viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {


    val filterList = viewModel.myList.filter {
        it.text.contains(viewModel.searchText.value)
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {

        SearchBar(searchText = viewModel.searchText)

        if(viewModel.runInProgress.value) {
            CircularProgressIndicator()
        }


        if(viewModel.errorMessage.value.isNotBlank()) {
            ErrorView(message = viewModel.errorMessage.value)
        }

        Spacer(Modifier.size(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filterList.size) {
                PictureRowItem(data = filterList[it], onPictureClick = {
                    navController?.navigate(Routes.DetailScreen.addParam(it))
                } )
            }
        }

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Button(
                onClick = { viewModel.uploadSearchText("")},
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Clear filter")
            }

            Spacer(Modifier.size(8.dp))

            Button(
                onClick = {
                    viewModel.loadData()
                },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Load data")
            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchText: MutableState<String> = remember { mutableStateOf("") }
) {

    TextField(
        value = searchText.value,
        onValueChange = { newValue -> searchText.value = newValue },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        label = { Text("Enter text") },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )

}

//Composable affichant 1 PictureData
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(modifier: Modifier = Modifier, data: PictureData, onPictureClick : () -> Unit = {}) {



    var isExpanded by remember { mutableStateOf(false) }

    var text = if (isExpanded) data.longText else (data.longText.take(20) + "...")

    Row(modifier = modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.surface)
        .heightIn(max = 100.dp)
    ) {

        GlideImage(
            model = data.url,
            contentDescription = "une photo de chat",
            loading = placeholder(R.mipmap.ic_launcher_round),
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .heightIn(max = 100.dp)
                .widthIn(max = 100.dp)
                .clickable(onClick = onPictureClick)
        )

        Spacer(modifier = Modifier.size(8.dp))

        Column(modifier = Modifier.clickable {
            isExpanded = !isExpanded
        }) {
            Text(
                text = data.text,
                fontSize = 20.sp
            )
            Text(
                text = text,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.animateContentSize()
            )
        }
    }


}