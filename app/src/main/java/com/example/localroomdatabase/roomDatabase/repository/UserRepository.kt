package com.example.localroomdatabase.roomDatabase.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.localroomdatabase.roomDatabase.model.UserEntity
import com.example.localroomdatabase.roomDatabase.data.UserDao
import com.example.localroomdatabase.roomDatabase.fragments.list.recycleView.model.UserModel

class UserRepository(private val userDao: UserDao) {

    val readAllData:LiveData<List<UserModel>> =  userDao.readAllData().map { list ->
        list.map { it.entityToModel() }
    }

    suspend fun addUser(user: UserModel){
        userDao.addUser(
            UserEntity(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                age = user.age
            )
        )
    }

    suspend fun updateUser(user: UserModel){
        userDao.updateUser(
            UserEntity(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                age = user.age
            )
        )
    }

    suspend fun deleteUser(user: UserModel){
        userDao.deleteUser(
            UserEntity(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                age = user.age
            )
        )
    } 
}