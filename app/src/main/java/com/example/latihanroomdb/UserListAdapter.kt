package com.example.latihanroomdb

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.latihanroomdb.data.User

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.MyViewHolder>() {
    private var userList = emptyList<User>()
    class  MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.tvId).text = curentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.tvFirstName).text = curentItem.firstName
        holder.itemView.findViewById<TextView>(R.id.tvLastName).text = curentItem.lastName
        holder.itemView.findViewById<TextView>(R.id.tvAge).text = curentItem.age.toString()

        holder.itemView.findViewById<LinearLayout>(R.id.rowLayout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(curentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(user:List<User>){
        this.userList = user
        Log.d("UserList", user.toString())
        notifyDataSetChanged()
    }
}