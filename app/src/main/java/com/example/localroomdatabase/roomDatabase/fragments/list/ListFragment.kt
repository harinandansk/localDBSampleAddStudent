package com.example.localroomdatabase.roomDatabase.fragments.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.localroomdatabase.R
import com.example.localroomdatabase.databinding.FragmentListBinding
import com.example.localroomdatabase.roomDatabase.fragments.list.recycleView.adapter.ListAdapter
import com.example.localroomdatabase.roomDatabase.fragments.update.UpdateFragmentArgs
import com.example.localroomdatabase.roomDatabase.model.viewModel.UserViewModel


class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding:FragmentListBinding?=null
    private val binding get()=_binding!!
    private val args: UpdateFragmentArgs by navArgs<UpdateFragmentArgs>()


    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ListAdapter

    private val viewModel: UserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding=FragmentListBinding.bind(view)

        binding.addFloatingActionButton.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToAddFragment())
        }

        initViews()


    }

    private fun initViews() {

        //RecyclerView
        adapter = ListAdapter(onItemClickLambda = {  user ->
            val direction = ListFragmentDirections.actionListFragmentToUpdateFragment(
                currentUser = user
            )
           findNavController().navigate(direction)
        }, deleteItemLambda = { deleteUser ->
            viewModel.deleteUser(deleteUser)
        })

        binding.rvList.adapter = adapter
        // UserViewModel
        viewModel.readAllData.observe(viewLifecycleOwner) { users ->
            adapter.setData(users)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }



}