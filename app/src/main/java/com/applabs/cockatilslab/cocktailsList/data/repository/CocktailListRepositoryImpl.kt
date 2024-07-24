package com.applabs.cockatilslab.cocktailsList.data.repository

import com.applabs.cockatilslab.cocktailsList.data.local.cocktail.CocktailDatabase
import com.applabs.cockatilslab.cocktailsList.data.mappers.toCocktail
import com.applabs.cockatilslab.cocktailsList.data.mappers.toCocktailEntity
import com.applabs.cockatilslab.cocktailsList.data.remote.CocktailsApi
import com.applabs.cockatilslab.cocktailsList.domain.model.Cocktail
import com.applabs.cockatilslab.cocktailsList.domain.repository.CocktailListRepository
import com.applabs.cockatilslab.cocktailsList.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class CocktailListRepositoryImpl @Inject constructor(
    private val cocktailsApi: CocktailsApi,
    private val cocktailDatabase: CocktailDatabase
) : CocktailListRepository {

    override suspend fun getCocktailList(
        forceFetchFromRemote: Boolean,
        category: String
    ): Flow<Resource<List<Cocktail>>> {
        return flow {
            emit(Resource.Loading(true))
            val localCockTailList = cocktailDatabase.cocktailDao.getCocktailListByCategory(category)

            val shouldLoadLocalCocktail = false //localCockTailList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalCocktail) {
                emit(Resource.Success(
                    data = localCockTailList.map { cocktailEntity ->
                        cocktailEntity.toCocktail(category = category)
                    }
                ))
                emit(Resource.Loading(false))
                return@flow
            }

            val cocktailListFromApi = try {
                cocktailsApi.getCocktailList(category = category)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading cocktails"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Error loading cocktails"))
                return@flow
            }


            val cocktailEntities = cocktailListFromApi.drinks.let {
                it.map { cocktailDto ->
                    cocktailDto.toCocktailEntity(category)
                }
            }
            cocktailDatabase.cocktailDao.upsertCocktailList(cocktailList = cocktailEntities)
            emit(Resource.Success(cocktailEntities.map {
                it.toCocktail(category = category)
            }))

            emit(Resource.Loading(false))
        }
    }

    override suspend fun getCocktail(id: Int): Flow<Resource<Cocktail>> {
        return flow {
            emit(Resource.Loading(true))

            val cocktailEntity = cocktailDatabase.cocktailDao.getCocktailById(id = id)
            if (cocktailEntity != null) {
                emit(
                    Resource.Success(cocktailEntity.toCocktail(cocktailEntity.category))
                )
                emit(Resource.Loading(false))
            }

            emit(Resource.Error("Error no such cocktail"))
        }
    }
}