package com.only.ekacareassignment.di

import android.app.Application
import androidx.room.Room
import com.only.ekacareassignment.data.datasource.MyDatabase
import com.only.ekacareassignment.data.repository.Repository
import com.only.ekacareassignment.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): MyDatabase {
        return Room.databaseBuilder(
            app,
            MyDatabase::class.java,
            "MyDataBase"
        )
            .addMigrations()
            .build()
    }

    @Provides
    @Singleton
    fun provideMyDao(myDatabase: MyDatabase): Repository{
        return RepositoryImpl(myDatabase.dao())
    }
}