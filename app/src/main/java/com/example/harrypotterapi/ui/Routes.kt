package com.example.harrypotterapi.ui

sealed class Routes(val route : String) {
    object SearchScreen : Routes("searchScreen")
    object DetailScreen : Routes("detailScreen/{data}") {
        fun addParam(position: Int) = "detailScreen/$position"
    }


    object HarryPotterCharactersScreen : Routes("HarryPotterCharactersScreen")

    object HarryPotterScreenDetail : Routes("HarryPotterScreenDetail/{data}") {
        fun addParam(id: Int) = "HarryPotterScreenDetail/$id"
    }


}