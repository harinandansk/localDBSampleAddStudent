package com.example.localroomdatabase.roomDatabase.model.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.localroomdatabase.roomDatabase.data.UserDatabase
import com.example.localroomdatabase.roomDatabase.fragments.list.recycleView.model.UserModel
import com.example.localroomdatabase.roomDatabase.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application):AndroidViewModel(application) {

    val readAllData:LiveData<List<UserModel>>
    private val repository: UserRepository

    init {
        val userDao=UserDatabase.getDatabase(application).userDao()
        repository= UserRepository(userDao)
        readAllData=repository.readAllData
    }

    fun addUser(user: UserModel){
        viewModelScope.launch (Dispatchers.IO){
            repository.addUser(user)
        }
    }

    fun updateUser(user: UserModel){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateUser(user)
        }
    }

    fun deleteUser(user : UserModel){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteUser(user)
        }
    }
}