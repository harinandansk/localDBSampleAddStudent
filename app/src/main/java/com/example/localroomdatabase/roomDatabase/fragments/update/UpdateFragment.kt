package com.example.localroomdatabase.roomDatabase.fragments.update

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.localroomdatabase.R
import com.example.localroomdatabase.databinding.FragmentAddBinding
import com.example.localroomdatabase.databinding.FragmentUpdateBinding
import com.example.localroomdatabase.roomDatabase.fragments.list.recycleView.model.UserModel
import com.example.localroomdatabase.roomDatabase.model.viewModel.UserViewModel

class UpdateFragment : Fragment(R.layout.fragment_update) {

    private var _binding: FragmentUpdateBinding?=null
    private val binding get() = _binding!!

    private val mUserViewModel : UserViewModel by viewModels()
    private val args: UpdateFragmentArgs by navArgs<UpdateFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUpdateBinding.bind(view)

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
        val argsFirstName = args.currentUser.firstName
        val argsLastName = args.currentUser.lastName
        val argsAge = args.currentUser.age

        binding.apply {
            updateFirstName.setText(argsFirstName)
            updateLastName.setText(argsLastName)
            updateAge.setText(argsAge.toString())
        }

        binding.updateButton.setOnClickListener {
            val updateId = args.currentUser.id
            val updateFirstName = binding.updateFirstName.text
            val updateLastName = binding.updateLastName.text
            val updateAge = binding.updateAge.text

            if(inputCheck(updateFirstName,updateLastName,updateAge)) {
                // create user object
                val updateUser = UserModel(updateId , updateFirstName.toString(), updateLastName.toString(), Integer.parseInt(updateAge.toString()))
                // add data to database
                mUserViewModel.updateUser(updateUser)
                Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()
                // navigate back
                findNavController().navigateUp()
            } else{
                Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun inputCheck(updateFirstName: Editable, updateLastName: Editable, updateAge: Editable):Boolean{
        return !(updateFirstName.isEmpty() || updateLastName.isEmpty() || updateAge.isEmpty())
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
