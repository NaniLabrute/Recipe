package com.example.recipe.presentation.settings.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.recipe.presentation.settings.SettingsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingTileDeleteSavedRecipes(title: String, settingsViewModel: SettingsViewModel){
    val openAlertDialog = remember { mutableStateOf(false) }
    Column {
        ListItem(
            modifier = Modifier.clickable {
                openAlertDialog.value = true
            },
            headlineContent = { Text(title) },
        )
        Divider()
    }
    when{
        openAlertDialog.value -> {
            AlertDialog(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    settingsViewModel.deleteSavedRecipes()
                    openAlertDialog.value = false
                },
                dialogTitle = "Delete all the saved recipes",
                dialogText = "Are you sure to delete all the saved recipes?",
                icon = Icons.Default.Warning
            )
        }
    }

}