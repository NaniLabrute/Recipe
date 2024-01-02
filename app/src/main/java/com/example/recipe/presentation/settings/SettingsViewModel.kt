package com.example.recipe.presentation.settings

import com.example.recipe.data.repository.DatabaseRepositoryImpl

class SettingsViewModel {
    private val databaseRepositoryImpl = DatabaseRepositoryImpl()

    fun deleteSavedRecipes(){
        databaseRepositoryImpl.deleteSavedRecipes()
    }
}