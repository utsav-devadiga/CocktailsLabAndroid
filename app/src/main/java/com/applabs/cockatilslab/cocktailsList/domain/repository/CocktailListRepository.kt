package com.applabs.cockatilslab.cocktailsList.domain.repository

import com.applabs.cockatilslab.cocktailsList.domain.model.Cocktail
import com.applabs.cockatilslab.cocktailsList.util.Resource
import kotlinx.coroutines.flow.Flow

interface CocktailListRepository {

    suspend fun getCocktailList(
        forceFetchFromRemote: Boolean,
        category: String
    ): Flow<Resource<List<Cocktail>>>


    suspend fun getCocktail(id: Int): Flow<Resource<Cocktail>>
}