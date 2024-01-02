package com.example.recipe

import com.example.recipe.presentation.home.HomeViewModel
import org.junit.Test

import org.junit.Assert.*

class ViewModelTests {
    @Test
    fun searchRecipeTest() {
        val query = "$50,000 Burger"
        val expectedContent = query

        var homeViewModel= HomeViewModel()
        val actualContent = homeViewModel.getSearchRecipes(query)
        assertEquals(expectedContent, actualContent)
    }

    @Test
    fun savedRecipesTest() {
        val query = "$50,000 Burger"
        val expectedContent = query

        var homeViewModel= HomeViewModel()
        val actualContent = homeViewModel.getRecipes()
        assertEquals(expectedContent, actualContent)
    }
}