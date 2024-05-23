package com.example.userssp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.userssp.databinding.ItemUserAlterBinding

//
class UserAdapter(private val users: MutableList<User>, private val listener: OnClickListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>()
{

    //VARIABLE PARA INFLAR LA VISTA
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_user_alter, parent, false)

        return ViewHolder(view)
        //inflamos la vista de item pero necesita de onBindViewHolder para rellenar la informacion
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users.get(position) //tomamos el obj user
        val humanPosition = position + 1
        with(holder){
            setListener(user, humanPosition) //vincular cada elemento con el listener, adapter configurado
            binding.tvOrder.text = (humanPosition).toString()
            binding.tvName.text = user.getFullName()
            Glide.with(context)
                .load(user.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop() //completa el espacio con la img
                .circleCrop() //imagen redonda
                .into(binding.imgPhoto)
        }
    }

    override fun getItemCount(): Int = users.size //indica cuantos elementos hay en el Adapter
    fun remove(position: Int) {
        users.removeAt(position)//eso funciona solo con MutableList
        notifyItemRemoved(position)//notifica solo remove
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemUserAlterBinding.bind(view)

        fun setListener(user: User, position: Int){
            binding.root.setOnClickListener {
                listener.onClick(user, position)
            }
        }
    }
}