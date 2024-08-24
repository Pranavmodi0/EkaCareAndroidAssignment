package com.only.ekacareassignment.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.only.ekacareassignment.data.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class MyDatabase: RoomDatabase(){
    abstract fun dao(): MyDao
}