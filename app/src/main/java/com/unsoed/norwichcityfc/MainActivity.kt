package com.unsoed.norwichcityfc

// (Pastikan semua import ini ada)
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unsoed.norwichcityfc.data.TeamResponse
import com.unsoed.norwichcityfc.databinding.ActivityMainBinding
import com.unsoed.norwichcityfc.viewmodel.ClubViewModel

class MainActivity : AppCompatActivity() {

    // Menggunakan ViewBinding untuk mengakses layout
    private lateinit var binding: ActivityMainBinding

    // Inisialisasi ViewModel
    private val clubViewModel: ClubViewModel by viewModels()

    // Variabel untuk menyimpan data tim saat ini
    private var currentTeamData: TeamResponse? = null

    // ID Klub Norwich City FC
    private val NORWICH_CITY_ID = 68 // <-- ID SUDAH BENAR

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Sembunyikan ActionBar agar gambar header penuh
        supportActionBar?.hide()

        // 1. Ambil data dari ViewModel
        clubViewModel.fetchTeamData(NORWICH_CITY_ID)

        // 2. Amati perubahan data
        observeViewModel()

        // 3. Siapkan listener untuk tombol
        setupButtonListeners()
    }

    private fun observeViewModel() {
        // Amati data tim
        clubViewModel.teamData.observe(this) { team ->
            team?.let {
                currentTeamData = it // Simpan data untuk dikirim ke activity lain
                setupUI(it)
            }
        }

        // Amati status loading
        clubViewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Amati jika ada error
        clubViewModel.error.observe(this) { errorMsg ->
            if (!errorMsg.isNullOrEmpty()) {
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
                binding.tvClubProfile.text = "Gagal memuat data."
            }
        }
    }

    // Fungsi untuk mengisi data ke UI
    private fun setupUI(team: TeamResponse) {
        binding.tvClubName.text = team.name ?: "Norwich City FC"

        // Gabungkan data dari API dengan teks manual
        val profileText = """
            ${team.name} (${team.shortName}) adalah klub sepakbola profesional Inggris yang berbasis di Norwich, Norfolk.
            Klub ini didirikan pada tahun 1902 dan saat ini bermain di stadion ${team.venue}.
        """.trimIndent()
        binding.tvClubProfile.text = profileText

        // Muat logo klub menggunakan Glide
        Glide.with(this)
            .load(team.crest) // URL logo dari API
            .error(R.drawable.ic_launcher_background) // Gambar jika error
            .into(binding.imgClubLogo)

        // Muat gambar header stadion (dari drawable)
        Glide.with(this)
            .load(R.drawable.norwich_stadium)
            .into(binding.imgClubHeader)
    }

    // Fungsi untuk mengatur klik tombol
    private fun setupButtonListeners() {
        // Tombol Sejarah Klub (ID BARU)
        binding.cardClubHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }

        // Tombol Pelatih
        binding.cardHeadCoach.setOnClickListener {
            currentTeamData?.coach?.let { coach ->
                val intent = Intent(this, CoachActivity::class.java)
                intent.putExtra(CoachActivity.EXTRA_COACH, coach)
                startActivity(intent)
            } ?: Toast.makeText(this, "Data pelatih tidak tersedia", Toast.LENGTH_SHORT).show()
        }

        // Tombol Skuad Tim
        binding.cardTeamSquad.setOnClickListener {
            currentTeamData?.squad?.let { squad ->
                if (squad.isNotEmpty()) {
                    val intent = Intent(this, SquadActivity::class.java)
                    intent.putParcelableArrayListExtra(SquadActivity.EXTRA_SQUAD, ArrayList(squad))
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Data skuad tidak tersedia", Toast.LENGTH_SHORT).show()
                }
            } ?: Toast.makeText(this, "Data skuad tidak tersedia", Toast.LENGTH_SHORT).show()
        }
    }
}

