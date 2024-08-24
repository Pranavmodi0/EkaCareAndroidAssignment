package com.only.ekacareassignment.data.repository

import com.only.ekacareassignment.data.datasource.MyDao
import com.only.ekacareassignment.data.entity.UserEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface Repository {

    suspend fun insert(userEntity: UserEntity)

    suspend fun getUsers(): Flow<List<UserEntity>>
}

class RepositoryImpl @Inject constructor(
    private val dao: MyDao,
) : Repository {
    override suspend fun insert(userEntity: UserEntity) {
        withContext(IO) {
            dao.insert(userEntity)
        }
    }

    override suspend fun getUsers(): Flow<List<UserEntity>> {
        return withContext(IO){
            dao.getUsers()
        }
    }
}