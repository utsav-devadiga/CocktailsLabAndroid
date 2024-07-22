package com.applabs.cockatilslab.cocktailsList.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Utsav Devadiga
 */
interface CocktailsApi {

    @GET("{api-key}/{category}")
    suspend fun getCocktailList(
        @Path("api-key") apiKey: String,
        @Path("category") category: String
    )


    companion object {
        //popular.php
        const val BASE_URL = "https://www.thecocktaildb.com/api/json/v2/"
        const val INGREDIENTS_BASE_URL = "https://www.thecocktaildb.com/images/ingredients/"
        const val API_KEY = "XXXXXX"
    }
}