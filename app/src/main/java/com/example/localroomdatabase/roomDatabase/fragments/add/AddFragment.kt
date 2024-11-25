package com.example.localroomdatabase.roomDatabase.fragments.add

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.localroomdatabase.R
import com.example.localroomdatabase.databinding.FragmentAddBinding
import com.example.localroomdatabase.roomDatabase.fragments.list.recycleView.model.UserModel
import com.example.localroomdatabase.roomDatabase.model.UserEntity
import com.example.localroomdatabase.roomDatabase.model.viewModel.UserViewModel


class AddFragment : Fragment(R.layout.fragment_add) {

    private var _binding:FragmentAddBinding?=null
    private val binding get() = _binding!!

    private lateinit var mUserViewModel : UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding=FragmentAddBinding.bind(view)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.addButton.setOnClickListener{
            onAddClick()
        }
    }

    private fun onAddClick() {
        val firstName = binding.editFirstName.text
        val secondName = binding.editSecondName.text
        val age = binding.editAge.text

        if(inputCheck(firstName,secondName,age)) {
            // create user object
            val userModel = UserModel(0,firstName.toString(),secondName.toString(),Integer.parseInt(age.toString()))
            // add data to database
            mUserViewModel.addUser(userModel)
            Toast.makeText(requireContext(), "Added Successfully", Toast.LENGTH_SHORT).show()
            // navigate back
            findNavController().navigateUp()
        } else{
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName:Editable,secondName:Editable,age:Editable):Boolean{
        return !(firstName.isEmpty() || secondName.isEmpty() || age.isEmpty())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}