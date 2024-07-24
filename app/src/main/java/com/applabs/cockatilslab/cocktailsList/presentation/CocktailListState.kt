package com.applabs.cockatilslab.cocktailsList.presentation

import com.applabs.cockatilslab.cocktailsList.domain.model.Cocktail

data class CocktailListState(
    val isLoading: Boolean = false,
    val isCurrentPopularScreen: Boolean = true,
    val popularCocktailList: List<Cocktail> = emptyList()
) {
}