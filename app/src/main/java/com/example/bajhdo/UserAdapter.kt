package com.example.bajhdo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class UserAdapter(val context: Context, val userList: ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view:View = LayoutInflater.from(context).inflate(R.layout.user_resourse_file, parent,false)
        return UserViewHolder(view)
    }


    override fun getItemCount(): Int {
        return userList.count()
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
    val currentUser = userList[position]
        holder.txtName.text = currentUser.name


        holder.itemView.setOnClickListener {
            val i = Intent(context,Chat_Activity::class.java)
            i.putExtra("name",currentUser.name)
            i.putExtra("uid",currentUser.uid)
            context.startActivity(i)
        }
    }
    
    class UserViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val txtName = itemView.findViewById<TextView>(R.id.txt_Name)
    }

}
