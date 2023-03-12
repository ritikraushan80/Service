package com.example.innobuzzapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.innobuzzapplication.R
import com.example.innobuzzapplication.model.ApiData
import com.example.innobuzzapplication.utils.Constant
import com.example.innobuzzapplication.utils.OnClickListener
import com.example.innobuzzapplication.utils.SharedPref
import com.google.android.material.card.MaterialCardView

class DataListAdapter(
    private val list: ArrayList<ApiData>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<DataListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = list[position].id.toString()
        holder.title.text = list[position].title

        holder.card.setOnClickListener {
            SharedPref.write(Constant.ID, list[position].id.toString())
            SharedPref.write(Constant.USER_ID, list[position].userId.toString())
            SharedPref.write(Constant.TITLE, list[position].title)
            SharedPref.write(Constant.DESCRIPTION, list[position].body)
            onClickListener.callBack(list[position].id)

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.id)
        val title: TextView = itemView.findViewById(R.id.title)
        val card: ConstraintLayout = itemView.findViewById(R.id.data_item)

    }
}