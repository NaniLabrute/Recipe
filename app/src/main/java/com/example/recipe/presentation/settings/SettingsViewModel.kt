package com.example.recipe.presentation.settings

import androidx.lifecycle.viewModelScope
import com.example.recipe.data.repository.DatabaseRepositoryImpl
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel

class SettingsViewModel: ViewModel() {
    private val databaseRepositoryImpl = DatabaseRepositoryImpl()
    fun deleteSavedRecipes(){
        viewModelScope.launch {
            databaseRepositoryImpl.deleteSavedRecipes()
        }
    }
}