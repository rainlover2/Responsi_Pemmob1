// Pastikan package name Anda benar
package com.unsoed.norwichcityfc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
// Impor ViewBinding yang sesuai dengan nama layout Anda
import com.unsoed.norwichcityfc.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    // Deklarasikan variabel binding
    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout menggunakan ViewBinding
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set judul di ActionBar
        supportActionBar?.title = "Club History"

        // --- Mengisi Konten ---

        // 1. Set Gambar Header
        // (Gunakan gambar stadion yang sama dari MainActivity)
        Glide.with(this)
            .load(R.drawable.norwich_stadium) // Pastikan nama file gambar benar
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(binding.imgHistoryHeader)

        // 2. Set Judul
        binding.tvHistoryTitle.text = "Norwich City's History"

        // 3. Set Konten Teks Sejarah
        // (Anda harus mencari teks ini di internet dan menempelkannya di sini)
        binding.tvHistoryContent.text = """
          Norwich City Football Club didirikan pada 17 Juni 1902 di kota Norwich, Norfolk, Inggris, oleh sekelompok pecinta sepak bola. Awalnya klub ini bermain di level amatir dengan seragam berwarna biru dan putih, sebelum berganti menjadi kuning dan hijau yang terinspirasi dari burung kenari, ikon kota Norwich, sehingga mereka dijuluki “The Canaries.” Pada tahun 1905, Norwich bergabung dengan Norfolk & Suffolk League dan East Anglian League, lalu masuk ke kompetisi Southern League. Setelah Perang Dunia I, pada tahun 1919, Norwich resmi diterima menjadi anggota Football League dan mulai berkompetisi di tingkat nasional. Klub ini meraih promosi pertama ke Divisi Dua pada musim 1933–34 setelah menjadi juara Divisi Tiga Selatan, namun sempat terdegradasi kembali menjelang Perang Dunia II. Pasca perang, mereka kembali bangkit dan pada tahun 1959 membuat kejutan besar dengan mencapai semifinal FA Cup meskipun berasal dari Divisi Tiga. Kesuksesan besar pertama datang pada tahun 1962 ketika Norwich menjuarai Football League Cup (EFL Cup), kemudian kembali mengangkat trofi yang sama pada tahun 1985. Memasuki era 1970-an hingga 1990-an, Norwich menjadi tim yang konsisten bersaing di level tertinggi sepak bola Inggris, bahkan mencapai puncak kejayaan pada musim 1992/93 di bawah pelatih Mike Walker dengan finis di posisi ketiga Premier League — pencapaian terbaik dalam sejarah klub. Musim berikutnya mereka tampil di Piala UEFA dan menorehkan sejarah dengan mengalahkan Bayern Munich di Jerman, sebuah kemenangan yang masih dikenang hingga kini. Memasuki abad ke-21, Norwich dikenal sebagai “yo-yo club” karena kerap naik turun antara Premier League dan Championship (promosi pada 2004, 2011, 2015, 2019, dan 2021). Meskipun sering mengalami degradasi, klub ini tetap stabil secara finansial di bawah kepemilikan Delia Smith dan Michael Wynn-Jones, dengan fokus pada pengembangan pemain muda serta keterlibatan komunitas lokal. Hingga kini Norwich City masih berkompetisi di EFL Championship dan terus berjuang untuk kembali ke Premier League. Dengan sejarah panjang, loyalitas suporter, dan identitas khas sebagai “The Canaries,” Norwich City FC menjadi simbol kebanggaan masyarakat Norwich dan Norfolk, serta contoh klub yang bertahan berkat semangat komunitasnya.
        """.trimIndent() // trimIndent() untuk merapikan teks
    }
}
