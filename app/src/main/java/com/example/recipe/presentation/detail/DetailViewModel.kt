package com.example.recipe.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.recipe.data.repository.DatabaseRepositoryImpl
import com.example.recipe.data.repository.RecipeRepositoryImpl
import com.example.recipe.domain.model.RecipeDetail
import kotlinx.coroutines.flow.MutableStateFlow

class DetailViewModel: ViewModel(){
    private val searchRecipeRepositoryImpl = RecipeRepositoryImpl()
    private val databaseRepositoryImpl = DatabaseRepositoryImpl()

    private val recipeDetail = searchRecipeRepositoryImpl.recipeDetail
    private val savedRecipeDetail = databaseRepositoryImpl.savedRecipeDetail

    var favorite by mutableStateOf(false)

    fun getRecipeDetail(id: String, saved: Boolean): MutableStateFlow<RecipeDetail?> {
        if(saved){
            databaseRepositoryImpl.getRecipeDetail(id)
            favorite = true
            return savedRecipeDetail
        } else {
            searchRecipeRepositoryImpl.getRecipeDetail(id)
            favorite = false
            return recipeDetail
        }
    }

    fun switchFavorite(id: String){
        favorite = !favorite
        if(favorite){
            databaseRepositoryImpl.saveRecipeDetail(recipeDetail.value!!)
        } else {
            databaseRepositoryImpl.deleteRecipeDetail(id)
        }
    }
}