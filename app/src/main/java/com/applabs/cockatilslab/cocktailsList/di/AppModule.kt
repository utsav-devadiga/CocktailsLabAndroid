package com.applabs.cockatilslab.cocktailsList.di

import android.app.Application
import androidx.room.Room
import com.applabs.cockatilslab.cocktailsList.data.local.cocktail.CocktailDatabase
import com.applabs.cockatilslab.cocktailsList.data.remote.CocktailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesCocktailApi(): CocktailsApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(CocktailsApi.BASE_URL)
            .client(client)
            .build()
            .create(CocktailsApi::class.java)

    }

    @Provides
    @Singleton
    fun providesCocktailDatabase(app: Application): CocktailDatabase {
        return Room.databaseBuilder(
            app,
            CocktailDatabase::class.java,
            "cocktailsdb.db"
        ).build()
    }


}


