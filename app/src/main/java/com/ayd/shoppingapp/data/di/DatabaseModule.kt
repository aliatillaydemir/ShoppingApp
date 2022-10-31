package com.ayd.shoppingapp.data.di

import android.content.Context
import androidx.room.Room
import com.ayd.shoppingapp.data.database.ProductDatabase
import com.ayd.shoppingapp.utils.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,
        ProductDatabase::class.java,
        DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideDao(database: ProductDatabase) = database.productDao()

}