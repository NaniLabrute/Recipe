package com.example.recipe.data.repository

import android.util.Log
//import com.example.recipe.data.FirestoreInstance
import com.example.recipe.data.RetrofitInstance
import com.example.recipe.domain.model.RecipeDetail
import com.example.recipe.domain.model.SearchRecipe
import com.example.recipe.domain.repository.DatabaseRepository
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firestore.v1.Document
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

fun toObject(document: DocumentSnapshot): RecipeDetail{
    val id = document.id
    val data = document.data
    return RecipeDetail(
        id = id,
        title = data?.get("title") as String,
        image = data["image"] as String,
        imageType = data["imageType"] as String,
        servings = (data["servings"] as Long).toInt(),
        readyInMinutes = (data["readyInMinutes"] as Long).toInt()
    )
}

class DatabaseRepositoryImpl: DatabaseRepository {
    private val recipesCollection = Firebase.firestore.collection("recipes")
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val savedRecipes = MutableStateFlow<List<RecipeDetail>>(emptyList())

    override fun getRecipes(){
        coroutineScope.launch {
            try {
                val querySnapshot: QuerySnapshot = recipesCollection.get().await()

                // Convert each document in the snapshot to a RecipeDetail object
                val responseSavedRecipes = querySnapshot.documents.mapNotNull { document ->
                    toObject(document)
                }

                withContext(Dispatchers.Main) {
                    savedRecipes.value = responseSavedRecipes
                }
            } catch (e: Exception) {
                Log.e("DatabaseRepository - getRecipes", "Failed to get all recipes: ${e.message}")
            }
        }
    }

    override fun saveRecipeDetail(recipeDetail: RecipeDetail) {
        coroutineScope.launch {
            try {
                recipesCollection.document(recipeDetail.id).set(recipeDetail).await()
            } catch (e: Exception) {
                Log.e("DatabaseRepository - saveRecipeDetail", "Failed to save recipe details: ${e.message}")
            }
        }
    }

    val savedRecipeDetail = MutableStateFlow<RecipeDetail?>(null)

    override fun getRecipeDetail(id: String) {
        coroutineScope.launch {
            try {
                val document = recipesCollection.document(id).get().await()
                val responseSavedRecipe = if (document.exists()) {
                    toObject(document)
                } else {
                    null
                }
                withContext(Dispatchers.Main) {
                    savedRecipeDetail.value = responseSavedRecipe!!
                }

            } catch (e: Exception) {
                Log.e("DatabaseRepository - getRecipeDetail", "Failed to get recipe details: ${e.message}")
            }
        }
    }

    override fun deleteRecipeDetail(id: String) {
        coroutineScope.launch {
            try {
                recipesCollection.document(id).delete().await()
            } catch (e: Exception) {
                Log.e("DatabaseRepository - deleteRecipeDetail", "Failed to delete recipe details: ${e.message}")
            }
        }
    }

    override fun deleteSavedRecipes() {
        coroutineScope.launch {
            try {
                val querySnapshot: QuerySnapshot = recipesCollection.get().await()

                // Use a batched write to delete each document
                val batch = Firebase.firestore.batch()

                for (document in querySnapshot.documents) {
                    batch.delete(document.reference)
                }
                batch.commit().await()
            } catch (e: Exception) {
                Log.e("DatabaseRepository - deleteRecipeDetail", "Failed to delete recipe details: ${e.message}")
            }
        }
    }
}