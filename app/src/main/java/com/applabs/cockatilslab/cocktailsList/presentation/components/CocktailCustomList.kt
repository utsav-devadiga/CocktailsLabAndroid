package com.applabs.cockatilslab.cocktailsList.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.applabs.cockatilslab.cocktailsList.presentation.CocktailListState

@Composable
fun CocktailCustomGrid(
    cocktailListState: CocktailListState,
    navHostController: NavHostController
) {
    val itemsInRow = 2
    val chunkedCocktailList = cocktailListState.popularCocktailList.chunked(itemsInRow)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(chunkedCocktailList.size) { rowIndex ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                chunkedCocktailList[rowIndex].forEach { cocktail ->
                    CocktailItem(
                        cocktail = cocktail,
                        navHostController = navHostController,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    )
                }

                // If the row has only one item, add a spacer to balance the grid
                if (chunkedCocktailList[rowIndex].size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        item {
            if (!cocktailListState.isLoading) {
                // Your pagination logic here
            }
        }
    }
}

