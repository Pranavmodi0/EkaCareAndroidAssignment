package com.only.ekacareassignment.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = tableName)

data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id: Int = 0,

    @SerialName("name")
    val name: String = "",

    @SerialName("age")
    val age: String = "",

    @SerialName("dob")
    val dob: String = "",

    @SerialName("address")
    val address: String = "",

)

const val tableName = "UserEntity"