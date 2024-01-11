package com.example.recipe.presentation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home_screen")
    object Detail: Screen(route = "detail_screen?id={id}&saved={saved}"){
        fun passData(
            id: String,
            saved: Boolean
        ): String {
            return "detail_screen?id=$id&saved=$saved"
        }
    }
    object Settings: Screen(route = "settings_screen")
}