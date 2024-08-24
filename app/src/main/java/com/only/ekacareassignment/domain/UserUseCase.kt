package com.only.ekacareassignment.domain

import com.only.ekacareassignment.data.entity.UserEntity
import com.only.ekacareassignment.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(userEntity: UserEntity) {
        repository.insert(userEntity)
    }

    suspend operator fun invoke(): Flow<List<UserEntity>> {
        return repository.getUsers()
    }
}