package com.example.harrypotterapi.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    var searchText = mutableStateOf("")
    val myList = mutableStateListOf<PictureData>()
    var errorMessage = mutableStateOf("")
    var runInProgress = mutableStateOf(false)

    fun uploadSearchText(newText: String) {
        searchText.value = newText
    }

    fun getFilterListBySearchText() = myList.filter { it.text.contains(searchText.value) }

    fun loadData() {
        myList.clear()
        errorMessage.value =""
        runInProgress.value = true

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val listCharacters: List<WeatherBean> = listOf(WeatherAPI.loadWeather(searchText.value))

                if(listCharacters.isEmpty()){
                    throw Exception("Pas de résultat")
                }

                myList.clear()

                myList.addAll(listCharacters.map {

                    var text = "C'est : ${it.name}"
                    var urlImage = "https://ik.imagekit.io/hpapi/harry.jpg"
                    PictureData(urlImage, it.name, text)
                })
            }catch(e:Exception) {
                e.printStackTrace()
                errorMessage.value = "Une erreur : ${e.message}"
            }

            runInProgress.value = false
        }
    }


    fun loadCharactersData() {
        myList.clear()

        errorMessage.value =""
        runInProgress.value = true

        viewModelScope.launch(Dispatchers.Default) {
            try {
                val listCharacters: List<HarryPotterCharactersBeanItem> = HarryPotterAPI.loadCharacters()

                if(listCharacters.isEmpty()){
                    throw Exception("Pas de résultat")
                }

                myList.clear()

                myList.addAll(listCharacters.map {
                    val text = "name : ${it.name}"
                    PictureData(it.image, it.actor, text)
                })
            }catch(e:Exception) {
                e.printStackTrace()
                errorMessage.value = "Une erreur : ${e.message}"
            }

            runInProgress.value = false
        }
    }
}