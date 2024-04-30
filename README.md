**LP7DPBO2024C2" - 2202680 **

### Judul: Flappy Bird Java Swing

### Deskripsi Singkat:
Program ini adalah klon dari permainan Flappy Bird yang populer, dibuat menggunakan Java Swing. Pemain mengontrol burung untuk menghindari rintangan dan mencetak skor sebanyak mungkin sebelum kecelakaan.

### Alur Kode:

1. **Kelas `App`:**
   - Memulai aplikasi dengan membuat instance dari `StartForm`.
   - Menggunakan `SwingUtilities.invokeLater()` untuk memastikan bahwa pembuatan GUI terjadi di EDT (Event Dispatch Thread).

2. **Kelas `StartForm`:**
   - Merupakan jendela awal yang menampilkan tombol "Start Game".
   - Ketika tombol ditekan, jendela ini ditutup dan permainan dimulai dengan memanggil `startGame()`.

3. **Kelas `FlappyBird`:**
   - Merupakan panel tempat permainan sebenarnya terjadi.
   - Menggunakan `Timer` untuk mengatur loop permainan dan penambahan rintangan.
   - Menggunakan `KeyListener` untuk mengontrol gerakan burung (space untuk melompat, 'P' untuk berhenti sementara, 'R' untuk restart setelah kalah).
   - Melacak kollision antara burung dan rintangan, serta mencetak skor.
   - Memberikan fitur pause saat permainan dijeda.

### Alur Utama Program:

1. Aplikasi dimulai dengan `App`, yang membuat instance dari `StartForm`.
2. Pemain memulai permainan dengan menekan tombol "Start Game" di `StartForm`.
3. Permainan dimulai, di mana `FlappyBird` ditampilkan.
4. Pemain mengontrol burung dengan menggunakan tombol keyboard.
5. Skor dicetak dan permainan berlanjut hingga pemain kalah.
6. Jika pemain kalah, pesan game over ditampilkan dengan opsi untuk restart atau keluar dari permainan.

### Assets:
Program menggunakan beberapa gambar sebagai aset:
- `background.png`: Latar belakang permainan.
- `bird.png`: Gambar burung.
- `lowerPipe.png` dan `upperPipe.png`: Gambar rintangan bawah dan atas.

