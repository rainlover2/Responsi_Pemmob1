package com.unsoed.norwichcityfc.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.norwichcityfc.data.Player
import com.unsoed.norwichcityfc.databinding.ListItemPlayerBinding

class PlayerAdapter : ListAdapter<Player, PlayerAdapter.PlayerViewHolder>(PlayerDiffCallback()) {


    private var onItemClickListener: ((Player) -> Unit)? = null

    fun setOnItemClickListener(listener: (Player) -> Unit) {
        onItemClickListener = listener
    }
    // -----------------------------

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ListItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = getItem(position)
        holder.bind(player)
    }

    inner class PlayerViewHolder(private val binding: ListItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            // Set data teks
            binding.tvPlayerName.text = player.name ?: "N/A"
            binding.tvPlayerNationality.text = player.nationality ?: "N/A"

            var cardColor = Color.WHITE
            var textColor = Color.BLACK

            // Cek posisi
            when (player.position) {
                "Goalkeeper" -> {
                    cardColor = Color.parseColor("#FFFF00")
                    textColor = Color.BLACK
                }
                "Defender", "Centre-Back", "Defence", "Left-Back", "Right-Back" -> {
                    cardColor = Color.parseColor("#0000FF")
                    textColor = Color.WHITE
                }
                "Midfield", "Central Midfield", "Defensive Midfield", "Left Midfield", "Left Winger", "Right Winger" -> {
                    cardColor = Color.parseColor("#008000")
                    textColor = Color.WHITE
                }
                "Forward", "Attacker", "Centre-Forward", "Offence" -> {
                    cardColor = Color.parseColor("#FF0000")
                    textColor = Color.WHITE
                }
                else -> {
                    cardColor = Color.WHITE
                    textColor = Color.BLACK
                }
            }

            // Terapkan warna
            binding.cardPlayer.setCardBackgroundColor(cardColor)
            binding.tvPlayerName.setTextColor(textColor)
            binding.tvPlayerNationality.setTextColor(textColor)
            // ------------------------------------------------

            // Set listener klik untuk item ini
            binding.root.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(player)
                }
            }
        }
    }

    // DiffUtil untuk efisiensi RecyclerView
    class PlayerDiffCallback : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem == newItem
        }
    }
}

