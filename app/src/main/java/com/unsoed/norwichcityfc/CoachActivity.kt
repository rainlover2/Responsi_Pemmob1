
package com.unsoed.norwichcityfc

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unsoed.norwichcityfc.data.Coach
import com.unsoed.norwichcityfc.databinding.ActivityCoachBinding
import java.util.Locale
import java.text.SimpleDateFormat

class CoachActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoachBinding

    companion object {
        const val EXTRA_COACH = "extra_coach"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Head Coach"


        val coach = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_COACH, Coach::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_COACH)
        }

        // Tampilkan data pelatih
        coach?.let {
            Glide.with(this)
                .load(R.drawable.liam_manning)
                .error(R.drawable.ic_coach)
                .into(binding.imgCoach)

            binding.tvCoachName.text = it.name ?: "Nama Tidak Diketahui"
            binding.tvCoachNationality.text = it.nationality ?: "N/A"
            binding.tvCoachDob.text = formatSimpleDate(it.dateOfBirth)
        }
    }
    private fun formatSimpleDate(dateString: String?): String {
        if (dateString == null) return "N/A"

        // Menggunakan SimpleDateFormat (yang aman untuk API 24)
        return try {
            // Format input dari API
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

            // Format output yang Anda inginkan (sesuai contoh)
            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

            // 1. Ubah String menjadi objek Date
            val date = inputFormat.parse(dateString)

            // 2. Ubah objek Date kembali menjadi String dengan format baru
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
