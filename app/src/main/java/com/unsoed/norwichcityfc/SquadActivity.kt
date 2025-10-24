package com.unsoed.norwichcityfc

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast // <-- Impor Toast jika Anda pakai debug
import androidx.appcompat.app.AppCompatActivity
import com.unsoed.norwichcityfc.adapter.PlayerAdapter
import com.unsoed.norwichcityfc.data.Player
import com.unsoed.norwichcityfc.databinding.ActivitySquadBinding
import java.text.SimpleDateFormat
import java.util.Locale

class SquadActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySquadBinding
    private lateinit var playerAdapter: PlayerAdapter

    companion object {
        const val EXTRA_SQUAD = "extra_squad"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySquadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Team Squad"

        // Ambil data skuad yang dikirim dari MainActivity
        val squadList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayListExtra(EXTRA_SQUAD, Player::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableArrayListExtra(EXTRA_SQUAD)
        }

        setupRecyclerView()

        squadList?.let {
            if (it.isNotEmpty()) {
                playerAdapter.submitList(it)
            } else {
                Toast.makeText(this, "Data skuad kosong.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupRecyclerView() {
        playerAdapter = PlayerAdapter()
        binding.rvSquad.adapter = playerAdapter
        playerAdapter.setOnItemClickListener { player ->
            showPlayerDetail(player)
        }
    }

    private fun showPlayerDetail(player: Player) {
        binding.tvDetailName.text = player.name ?: "N/A"
        binding.tvDetailDob.text = formatSimpleDate(player.dateOfBirth)
        binding.tvDetailNationality.text = player.nationality ?: "N/A"
        binding.tvDetailPosition.text = player.position ?: "N/A"
        binding.cardPlayerDetail.visibility = View.VISIBLE
    }

    private fun formatSimpleDate(dateString: String?): String {
        if (dateString == null) return "N/A"

        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

            val date = inputFormat.parse(dateString)
            if (date != null) {
                outputFormat.format(date)
            } else {
                dateString
            }
        } catch (e: Exception) {
            dateString
        }
    }
}

