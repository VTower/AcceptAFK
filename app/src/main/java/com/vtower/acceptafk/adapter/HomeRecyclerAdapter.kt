package com.vtower.acceptafk.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.vtower.acceptafk.R
import com.vtower.acceptafk.model.Game
import androidx.core.graphics.toColorInt

class HomeRecyclerAdapter(private val games: List<Game>) : RecyclerView.Adapter<HomeRecyclerAdapter.GamesViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class GamesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Game) {
            itemView.findViewById<ImageView>(R.id.gameLogoImage).setImageResource(item.gameImage)
            itemView.findViewById<TextView>(R.id.nameGame).text = item.gameName

            if (layoutPosition == selectedPosition) {
                itemView.setBackgroundColor("#525355".toColorInt())
            } else {
            itemView.setBackgroundColor(Color.TRANSPARENT)
            }

            itemView.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = position

                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.adapter_game_card, parent, false)

        return GamesViewHolder(view)
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int {
        return games.size
    }

}
