package com.example.bajhdo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter (val context:Context, val messageList:ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        val ITEM_RECIVE = 1
        val ITEM_SENT = 2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if(viewType==1){
            val view:View = LayoutInflater.from(context).inflate(R.layout.recive, parent,false)
            return ReciveViewHolder(view)
        }else{
            val view:View = LayoutInflater.from(context).inflate(R.layout.send, parent,false)
            return SentViewHolder(view)
        }
    }


    override fun getItemCount(): Int {
        return messageList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val courrentMessage = messageList[position]
        if(holder.javaClass==SentViewHolder::class.java){

            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = courrentMessage.message

        }else{
            val viewHolder = holder as ReciveViewHolder
            holder.reciveMessage.text = courrentMessage.message

        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
        return ITEM_SENT
        }else{
            return ITEM_RECIVE
        }
    }
    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val sentMessage = itemView.findViewById<TextView>(R.id.txtSentMessage)
    }
    class ReciveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val reciveMessage = itemView.findViewById<TextView>(R.id.txtReciveMessage)

    }
}