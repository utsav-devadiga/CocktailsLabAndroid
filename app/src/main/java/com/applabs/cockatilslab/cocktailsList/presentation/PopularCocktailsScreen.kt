package com.applabs.cockatilslab.cocktailsList.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.applabs.cockatilslab.cocktailsList.presentation.components.CocktailCustomGrid

@Composable
fun PopularCocktailsScreen(
    cocktailListState: CocktailListState,
    navHostController: NavHostController,
    onEvent: (CocktailListUIEvent) -> Unit
) {

    if (cocktailListState.popularCocktailList.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        CocktailCustomGrid(cocktailListState,navHostController)
    }

}