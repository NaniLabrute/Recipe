package com.example.recipe.presentation.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe.data.repository.DatabaseRepositoryImpl
import com.example.recipe.data.repository.RecipeRepositoryImpl
import com.example.recipe.domain.model.RecipeDetail
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel(){
    private val searchRecipeRepositoryImpl = RecipeRepositoryImpl()
    private val databaseRepositoryImpl = DatabaseRepositoryImpl()

    private var recipeDetail by mutableStateOf<RecipeDetail?>(null)
    var favorite by mutableStateOf(false)

    fun getRecipeDetail(id: String, saved: Boolean): RecipeDetail? {
        viewModelScope.launch {
            if(saved){
                favorite = true
                recipeDetail = databaseRepositoryImpl.getRecipeDetail(id)
            } else {
                favorite = false
                recipeDetail = searchRecipeRepositoryImpl.getRecipeDetail(id)
            }
        }
        return recipeDetail
    }

    fun switchFavorite(id: String){
        favorite = !favorite
        viewModelScope.launch {
            if(favorite){
                databaseRepositoryImpl.saveRecipeDetail(recipeDetail!!)
            } else {
                databaseRepositoryImpl.deleteRecipeDetail(id)
            }
        }
    }
}