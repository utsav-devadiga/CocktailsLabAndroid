package com.applabs.cockatilslab.cocktailsList.presentation

sealed interface CocktailListUIEvent {

    data class Paginate(val category: String) : CocktailListUIEvent
    object Navigate : CocktailListUIEvent
}