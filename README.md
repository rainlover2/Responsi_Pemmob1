# Responsi 1 Pemrograman Mobile
Nama : Ratu Naurah Calista
Nim : H1D023004
Shift Baru : B
Shift Asal : C

#Video Demo
https://github.com/user-attachments/assets/48c4296a-64df-49e1-9959-92b118a58f39

#Penjelasan Alur Data
1.  Aplikasi dimulai di `MainActivity`. `ClubViewModel` dipanggil untuk mengambil data.
2.  `ClubViewModel` menggunakan Coroutine (`viewModelScope`) untuk memanggil `RetrofitClient.instance.getTeamDetails(351)`.
3.  `RetrofitClient` menggunakan `AuthInterceptor` untuk menyisipkan Token API (yang sudah dihapus) ke dalam *header* request.
4.  Request `GET` dikirim ke API `https://api.football-data.org/v4/teams/68`.
5.  API mengembalikan data JSON. Retrofit (dengan Gson) mem-parsing JSON ini ke data class `TeamResponse`.
6.  `ClubViewModel` memperbarui `LiveData` dengan data `TeamResponse` tersebut.
7.  `MainActivity` meng-observe `LiveData` dan menampilkan data (nama, logo, profil) ke UI.
8.  Saat tombol "Head Coach" diklik, objek `team.coach` dikirim ke `CoachActivity` via Intent.
9.  Saat tombol "Team Squad" diklik, `ArrayList<Player>` dari `team.squad` dikirim ke `SquadActivity` via Intent.
10. `SquadActivity` memberikan list pemain ke `PlayerAdapter`. `PlayerAdapter` mewarnai kartu berdasarkan `player.position` (Kuning, Biru, Hijau, Merah) dan mengatur *click listener*.
