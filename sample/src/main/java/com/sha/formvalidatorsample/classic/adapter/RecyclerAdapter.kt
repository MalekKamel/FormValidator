package com.sha.formvalidatorsample.classic.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.sha.formvalidatorsample.R

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Vh>() {

    inner class Vh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

        init {
            itemView.setOnClickListener { items[adapterPosition].showDemo(itemView.context) }
        }

        fun onBind(item: FieldItem) {
            tvTitle.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_example, parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) = holder.onBind(items[position])

    override fun getItemCount() = items.size

    companion object {
        private val items = FieldInfo.items
    }

}
