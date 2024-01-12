package com.example.recipe.data.repository

import com.example.recipe.domain.model.RecipeDetail
import com.example.recipe.domain.repository.DatabaseRepository
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

fun toObject(document: DocumentSnapshot): RecipeDetail?{
    val data = document.data
    if (data != null){
        return RecipeDetail(
            id = data["id"] as String,
            title = data["title"] as String,
            image = data["image"] as String,
            imageType = data["imageType"] as String,
            servings = (data["servings"] as Long).toInt(),
            readyInMinutes = (data["readyInMinutes"] as Long).toInt()
        )
    } else {
        return null
    }
}

class DatabaseRepositoryImpl: DatabaseRepository {
    private val recipesCollection = Firebase.firestore.collection("recipes")

    override suspend fun getRecipes(): List<RecipeDetail> {
        val querySnapshot: QuerySnapshot = recipesCollection.get().await()

        val responseSavedRecipes = querySnapshot.documents.mapNotNull {
            document -> toObject(document)
        }
        return responseSavedRecipes
    }

    override suspend fun saveRecipeDetail(recipeDetail: RecipeDetail) {
        recipesCollection.document(recipeDetail.id).set(recipeDetail).await()
    }

    override suspend fun getRecipeDetail(id: String): RecipeDetail? {
        val document = recipesCollection.document(id).get().await()
        return toObject(document)
    }

    override suspend fun deleteRecipeDetail(id: String) {
        recipesCollection.document(id).delete().await()
    }

    override suspend fun deleteSavedRecipes() {
        val querySnapshot: QuerySnapshot = recipesCollection.get().await()
        val batch = Firebase.firestore.batch()
        for (document in querySnapshot.documents) {
            batch.delete(document.reference)
        }
        batch.commit().await()
    }
}