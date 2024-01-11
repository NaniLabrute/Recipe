package com.example.recipe.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.recipe.R
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationDrawer(navController: NavHostController, drawerState: DrawerState){
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.home)) },
                    selected = 0 == selectedItemIndex,
                    onClick = {
                        navController.navigate(route = Screen.Home.route,)
                        selectedItemIndex = 0
                        scope.launch {drawerState.close()}
                    },
                    icon = {
                        Icon(
                            imageVector = if(0 == selectedItemIndex){
                                Icons.Filled.Home
                            } else Icons.Outlined.Home,
                            contentDescription = "Home"
                        )
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
                NavigationDrawerItem(
                    label = { Text(text = stringResource(R.string.settings_2)) },
                    selected = 1 == selectedItemIndex,
                    onClick = {
                        navController.navigate(route = Screen.Settings.route)
                        selectedItemIndex = 1
                        scope.launch {drawerState.close()}
                    },
                    icon = {
                        Icon(
                            imageVector = if(1 == selectedItemIndex){
                                Icons.Filled.Settings
                            } else Icons.Outlined.Settings,
                            contentDescription = "Settings"
                        )
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        },
        drawerState = drawerState
    ) {
        SetupNavGraph(navController = navController, drawerState = drawerState)
    }
}