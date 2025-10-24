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

    // --- Ini untuk listener klik ---
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

            // --- INI ADALAH LOGIKA PEWARNAAN YANG BENAR ---

            var cardColor = Color.WHITE
            var textColor = Color.BLACK // Default warna teks

            // Cek posisi
            when (player.position) {
                "Goalkeeper" -> {
                    cardColor = Color.parseColor("#FFFF00") // Kuning
                    textColor = Color.BLACK // Teks hitam di atas kuning
                }
                "Defender", "Centre-Back", "Defence", "Left-Back", "Right-Back" -> {
                    cardColor = Color.parseColor("#0000FF") // Biru
                    textColor = Color.WHITE // Teks putih di atas biru
                }
                "Midfield", "Central Midfield", "Defensive Midfield", "Left Midfield", "Left Winger", "Right Winger" -> {
                    cardColor = Color.parseColor("#008000") // Hijau
                    textColor = Color.WHITE // Teks putih di atas hijau
                }
                "Forward", "Attacker", "Centre-Forward", "Offence" -> { // Menambahkan "Attacker" untuk jaga-jaga
                    cardColor = Color.parseColor("#FF0000") // Merah
                    textColor = Color.WHITE // Teks putih di atas merah
                }
                else -> {
                    cardColor = Color.WHITE // Default jika posisi tidak dikenali
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

