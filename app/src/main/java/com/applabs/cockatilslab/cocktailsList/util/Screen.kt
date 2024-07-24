package com.applabs.cockatilslab.cocktailsList.util

sealed class Screen(val route: String) {
    object Home : Screen("main")
    object PopularCocktailList : Screen("popularCocktail")
    object FavouriteCocktailList : Screen("FavouriteCocktail")
    object Details : Screen("details")
}