package com.applabs.cockatilslab.cocktailsList.data.local.cocktail

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CocktailEntity::class], version = 1)
abstract class CocktailDatabase : RoomDatabase() {
    abstract val cocktailDao: CocktailDao

}