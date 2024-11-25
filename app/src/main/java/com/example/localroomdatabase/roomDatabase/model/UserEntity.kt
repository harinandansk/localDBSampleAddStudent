package com.example.localroomdatabase.roomDatabase.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.localroomdatabase.roomDatabase.fragments.list.recycleView.model.UserModel
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user_table")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val firstName:String,
    val lastName:String,
    val age:Int
){
    fun entityToModel():UserModel{
        return UserModel(
            id = id,
            firstName = firstName,
            lastName = lastName,
            age = age
        )
    }
}

