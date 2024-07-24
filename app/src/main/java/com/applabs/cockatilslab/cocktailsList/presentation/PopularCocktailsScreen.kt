package com.applabs.cockatilslab.cocktailsList.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.applabs.cockatilslab.cocktailsList.presentation.components.CocktailItem

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

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(cocktailListState.popularCocktailList.size) { index ->

                CocktailItem(
                    cocktail = cocktailListState.popularCocktailList[index],
                    navHostController = navHostController
                )
                Spacer(modifier = Modifier.height(16.dp))

                if (index >= cocktailListState.popularCocktailList.size - 1 && !cocktailListState.isLoading) {
                    //paginate.
                }

            }
        }

    }

}