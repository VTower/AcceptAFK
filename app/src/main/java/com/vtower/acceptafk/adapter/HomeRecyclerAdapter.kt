package com.vtower.acceptafk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vtower.acceptafk.R

class HomeRecyclerAdapter : RecyclerView.Adapter<HomeRecyclerAdapter.GamesViewHolder>() {

    inner class GamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var gameImage: ImageView

        fun bind(item: String) {
            // Assuming the layout has a TextView with id text1
            // (itemView.findViewById<TextView>(android.R.id.text1)).text = item
        }
    }

    // Sample data
    private val items = listOf("Item 1", "Item 2", "Item 3", "Item 3", "Item 3", "Item 3", "Item 3", "Item 3", "Item 3", "Item 3", "Item 3", "Item 3")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.adapter_game_card, parent, false)

        return GamesViewHolder(view)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}