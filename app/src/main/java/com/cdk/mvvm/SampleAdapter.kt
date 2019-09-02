package com.cdk.mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.sample_item_view.view.*

class SampleAdapter(
    private var data: List<String>,
    private val callback: (String) -> Unit
) :
    RecyclerView.Adapter<SampleViewHolder>() {

    // can also use DiffUtil here if you want to get fancy
    fun updateItems(list: List<String>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val viewHolder = SampleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.sample_item_view,
                parent, false
            )
        )

        viewHolder.itemView.setOnClickListener { _ ->
            viewHolder.adapterPosition.takeIf { it != RecyclerView.NO_POSITION }?.let {
                callback.invoke(data[it])
            }
        }

        return viewHolder
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.subText.text = data[position]
        holder.body.text = data[position]
    }
}

class SampleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val body: TextView = view.body
    val subText: TextView = view.subtext
}