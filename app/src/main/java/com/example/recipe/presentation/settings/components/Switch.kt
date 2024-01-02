package com.example.recipe.presentation.settings.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.recipe.presentation.settings.SettingsViewModel



data class ToggleableInfo(
    val isChecked :Boolean,
    val text: String
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Switch(title: String, viewModel: SettingsViewModel){
    /*var switch by remember {
        mutableStateOf(
            ToggleableInfo(
                isChecked = false,
                text = "Dark mode"
            )
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = switch.text)
        Switch(title = switch.isChecked, viewModel = )
    }*/
}