package com.example.recipe

import org.junit.Assert.*

import org.junit.Test

class DetailViewModelTest {
    private var favorite = false

    private fun setFavorite() {
        favorite = !favorite
    }

    @Test
    fun testSetFavorite() {
        setFavorite()
        assertEquals(true, favorite)
        setFavorite()
        assertEquals(false, favorite)
    }
}