package com.example.recipe.data.repository

import android.util.Log
import com.example.recipe.data.RetrofitInstance
import com.example.recipe.domain.model.RecipeDetail
import com.example.recipe.domain.model.SearchRecipe
import com.example.recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl: RecipeRepository {
    // Use a coroutine scope to launch the API call on a background thread
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val searchRecipes = MutableStateFlow<List<SearchRecipe>>(emptyList())

    override fun getSearchRecipes(query: String){
        // Use the coroutineScope to launch a new coroutine
        coroutineScope.launch {
            try {
                val response = RetrofitInstance.recipeApi.getSearchRecipeData(query)
                if (response.isSuccessful) {
                    val responseSearchRecipes = response.body()
                    withContext(Dispatchers.Main) {
                        searchRecipes.value = responseSearchRecipes?.results!!
                    }
                } else {
                    Log.e("RecipeRepository - getSearchRecipes", "API call failed: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("RecipeRepository - getSearchRecipes", "API call failed: ${e.message}")
            }
        }
    }

    val recipeDetail = MutableStateFlow<RecipeDetail?>(null)

    override fun getRecipeDetail(id: String) {
        coroutineScope.launch {
            try {
                val response = RetrofitInstance.recipeApi.getRecipeDetails(id)
                if (response.isSuccessful) {
                    val responseRecipeDetail = response.body()
                    withContext(Dispatchers.Main) {
                        recipeDetail.value = responseRecipeDetail
                    }
                } else {
                    Log.e("RecipeRepository - getRecipeDetail", "API call failed: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("RecipeRepository - getRecipeDetail", "API call failed: ${e.message}")
            }
        }
    }
}