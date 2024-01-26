package com.example.harrypotterapi.model

fun main() {

    val list = arrayListOf("Bob", "Tobby", "John")
    println(list.joinToString { "${it} " })
}

const val LONG_TEXT = """hello"""
data class PictureData(val url: String, val text: String, val longText: String)


val pictureList = arrayListOf(PictureData("https://picsum.photos/200", "ABCD", LONG_TEXT),
    PictureData("https://picsum.photos/201", "BCDE", LONG_TEXT),
    PictureData("https://picsum.photos/202", "CDEF", LONG_TEXT),
    PictureData("https://picsum.photos/203", "EFGH", LONG_TEXT)
)