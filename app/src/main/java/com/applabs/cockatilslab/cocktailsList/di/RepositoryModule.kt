package com.applabs.cockatilslab.cocktailsList.di

import com.applabs.cockatilslab.cocktailsList.data.repository.CocktailListRepositoryImpl
import com.applabs.cockatilslab.cocktailsList.domain.repository.CocktailListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCocktailListRepository(
        cocktailListRepositoryImpl: CocktailListRepositoryImpl
    ): CocktailListRepository

}