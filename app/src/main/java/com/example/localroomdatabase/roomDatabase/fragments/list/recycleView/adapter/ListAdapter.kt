package com.example.localroomdatabase.roomDatabase.fragments.list.recycleView.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.localroomdatabase.databinding.CellListBinding
import com.example.localroomdatabase.roomDatabase.fragments.list.recycleView.model.UserModel
import com.example.localroomdatabase.roomDatabase.fragments.list.recycleView.viewHolder.ListUserViewHolder


class ListAdapter(
    val onItemClickLambda: (item: UserModel) -> Unit,
    val deleteItemLambda: (item: UserModel) -> Unit
) : Adapter<ListUserViewHolder>() {
    private var list = emptyList<UserModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUserViewHolder {
        return ListUserViewHolder(
            CellListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        val data = list[position]
        holder.binding.apply {
            id.text = "${data.id} -"
            firstName.text = data.firstName
            lastName.text = data.lastName
            age.text = "( ${data.age} )"

            listLayout.setOnClickListener {
                onItemClickLambda.invoke(data)
            }

            deleteButton.setOnClickListener {
                notifyItemRemoved(position)
                deleteItemLambda.invoke(data)
            }
        }
    }

    fun setData(user: List<UserModel>) {
        this.list = user
        notifyDataSetChanged()
    }
}