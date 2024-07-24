package com.applabs.cockatilslab.cocktailsList.data.local.cocktail

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CocktailDao {
    @Upsert
    suspend fun upsertCocktailList(cocktailList: List<CocktailEntity>)

    @Query("SELECT * FROM CocktailEntity WHERE idDrink =:id")
    suspend fun getCocktailById(id: Int): CocktailEntity


    @Query("SELECT * FROM CocktailEntity WHERE category =:category")
    suspend fun getCocktailListByCategory(category: String): List<CocktailEntity>
}