// Pastikan package name Anda benar
package com.unsoed.norwichcityfc

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unsoed.norwichcityfc.data.Coach
import com.unsoed.norwichcityfc.databinding.ActivityCoachBinding
// Import java.time sudah dihapus karena tidak dipakai
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

        // Ambil data Coach yang dikirim dari MainActivity
        val coach = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_COACH, Coach::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_COACH)
        }

        // Tampilkan data pelatih
        coach?.let {
            // --- Mengisi Data Sesuai Layout Baru ---

            // 1. Set Gambar Pelatih (WAJIB Anda cari manual)
            Glide.with(this)
                .load(R.drawable.liam_manning) // Anda sudah mengganti ini, bagus!
                .placeholder(R.drawable.ic_coach) // Placeholder
                .error(R.drawable.ic_coach) // Gambar jika error
                .into(binding.imgCoach)

            // 2. Set Info di dalam Card
            binding.tvCoachName.text = it.name ?: "Nama Tidak Diketahui"
            binding.tvCoachNationality.text = it.nationality ?: "N/A"

            // Format tanggal agar sesuai contoh (YYYY-MM-DD)
            binding.tvCoachDob.text = formatSimpleDate(it.dateOfBirth)
        }
    } // <-- Penutup fungsi onCreate

    // --- FUNGSI formatSimpleDate SEKARANG ADA DI TEMPAT YANG BENAR ---
    // (di dalam kelas CoachActivity, tapi di luar onCreate)
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
                dateString // Kembalikan string asli jika parse gagal
            }
        } catch (e: Exception) {
            dateString // Kembalikan string asli jika ada error
        }
    }

} // <-- Penutup kelas CoachActivity (HANYA ADA SATU)
