package com.example.userssp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userssp.databinding.ItemUserBinding

//
class UserAdapter(private val users: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

    }

    override fun getItemCount(): Int {

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemUserBinding.bind(view)
    }
}