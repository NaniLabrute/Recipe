package com.example.recipe.presentation.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.recipe.R
import com.example.recipe.presentation.components.Error
import com.example.recipe.presentation.home.HomeViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchRecipeBar(navController: NavController, homeViewModel: HomeViewModel, drawerState: DrawerState){
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        query = query,
        onQueryChange = {query = it},
        onSearch = {active = false},
        active = active,
        onActiveChange ={active = it},
        placeholder = { Text(text = stringResource(R.string.search_a_recipe)) },
        leadingIcon = {
            IconButton(
                onClick = {scope.launch {drawerState.open()}}
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        trailingIcon = {
            if(active) {
                Icon(
                    modifier = Modifier.clickable {
                        if(query.isNotEmpty()){
                            query = ""
                        } else {
                            active = false
                        }
                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon"
                )
            } else {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            }
        }
    ){
        val searchRecipes = homeViewModel.getSearchRecipes(query).collectAsState()
        if(searchRecipes.value.isEmpty()){
            Error(stringResource(R.string.no_recipe_found_search_recipe_bar))
        } else {
            RecipeSearchList(navController, searchRecipes.value)
        }
    }
}