package com.only.ekacareassignment.data.entity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
@Entity(tableName = tableName)

@RequiresApi(Build.VERSION_CODES.O)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @SerialName("id")
    val id: Int = 0,

    @SerialName("name")
    val name: String = "",

    @SerialName("age")
    val age: String = "",

    @SerialName("dob")
    val dob: String = LocalDate.now().toString(),

    @SerialName("address")
    val address: String = "",

    )

const val tableName = "UserEntity"