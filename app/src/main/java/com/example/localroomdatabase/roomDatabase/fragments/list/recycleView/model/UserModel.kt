package com.example.localroomdatabase.roomDatabase.fragments.list.recycleView.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel (
    val id:Int,
    val firstName:String,
    val lastName:String,
    val age:Int
) : Parcelable