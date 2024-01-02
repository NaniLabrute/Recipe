package com.example.recipe.presentation.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.recipe.R
import com.example.recipe.domain.model.RecipeDetail
import com.example.recipe.presentation.detail.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailCard(navController: NavController, detailViewModel: DetailViewModel, recipeDetail: RecipeDetail) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        //elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
        ) {
            Image(
                painter = rememberAsyncImagePainter(recipeDetail.image),
                contentDescription = recipeDetail.title,
            )
            Text(
                text = recipeDetail.id,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = recipeDetail.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = stringResource(
                    R.string.servings_ready_in_minutes,
                    recipeDetail.servings,
                    recipeDetail.readyInMinutes
                ),
            )
        }
        IconButton(
            onClick = { detailViewModel.switchFavorite(recipeDetail.id);navController.popBackStack()},
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = if (detailViewModel.favorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Default.FavoriteBorder
                },
                contentDescription = "Favorite",
            )
        }
    }
}