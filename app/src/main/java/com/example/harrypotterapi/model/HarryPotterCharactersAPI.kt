package com.example.harrypotterapi.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request


fun main() {
    println(HarryPotterAPI.loadCharacters())
}

object HarryPotterAPI {
    val client = OkHttpClient()
    val gson = Gson()

    const val URL_API = "https://hp-api.onrender.com/api/characters"

    fun loadCharacters(id: Int): HarryPotterCharactersBeanItem {
        val json = sendGet(URL_API + "/4")
        return gson.fromJson(json, HarryPotterCharactersBeanItem::class.java)
    }


    fun loadCharacters(): List<HarryPotterCharactersBeanItem> {
        val json = sendGet(URL_API )
        println(json)
        return gson.fromJson(json, Array<HarryPotterCharactersBeanItem>::class.java).toList()
    }

    fun sendGet(url: String): String {
        println("url : $url")
        val request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute().use { //it:Response

            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }

            it.body.string()
        }
    }
}


data class HarryPotterCharactersBeanItem(
    val actor: String,
    val id: String,
    val image: String,
    val name: String,
    val dateOfBirth: String,
    val yearOfBirth: Int
)
