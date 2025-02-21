# ESHOP Advance Programming

Nama: Wisnu Nugroho

NPM: 2306275084

Kelas: Prolan-A

Deployment: [Eshop on Koyeb](https://evil-virgie-wsnugroho-c810dfaf.koyeb.app)

<hr>

# Modul 2 - CI/CD & DevOps

## Refleksi 2

> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

Berikut adalah beberapa isu kualitas kode yang saya perbaiki beserta strategi perbaikannya:

1. **Unnecessary Public Modifier**: Saya mengubah visibilitas kelas dan metode test JUnit5 dari public menjadi default
   package visibility agar sesuai rekomendasi best practice.
2. **Dependency Grouping**: Saya mengelompokkan dependensi dalam file build berdasarkan fungsionalitas, sehingga
   meningkatkan keterbacaan, memudahkan pemeliharaan, dan membuat struktur kode lebih baik
3. **Unused Import**: Saya menghapus import yang tidak digunakan untuk menjaga kebersihan kode, mengurangi clutter, dan
   membuat kode lebih mudah dipahami.
4. **Duplikasi String Literal**: Saya mengekstrak string literal "redirect:/product/list" ke dalam sebuah konstanta
   untuk menghindari duplikasi, mendukung prinsip DRY (Don't Repeat Yourself) dan memudahkan pemeliharaan.

> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the
> definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Implementasi CI/CD yang saya terapkan seharusnya sudah sesuai dengan definisi Continuous Integration dan Continuous
Deployment. Berikut beberapa alasannya:

1. **Continuous Integration**: Setiap kali ada perubahan di repository, saya memanfaatkan GitHub Actions dengan file
   `ci.yml` untuk menjalankan build dan unit test secara otomatis. Hal ini memastikan bahwa setiap commit terintegrasi
   dengan baik dan potensi error dapat cepat terdeteksi. Saya juga membuat Github Action workflow melalui file
   `scorecard.yml`
   untuk analisis OSSF dan `sonarqube.yml` untuk mengevaluasi kualitas kode, sehingga kualitas kode sesuai dengan
   standar yang telah ada.
2. **Continuous Deployment**: Setelah build dan test berhasil, saya melakukan deployment aplikasi ke Koyeb menggunakan
   Dockerfile. Repository yang telah di-deploy di Koyeb akan melakukan auto rebuild setiap kali ada push atau pull
   request ke branch main, sehingga setiap fitur terbaru langsung tersedia di
   production tanpa perlu intervensi manual. Hal ini dapat meningkatkan kecepatan, konsistensi, dan keandalan rilisnya
   aplikasi tersebut.

<hr>

# Modul 1 - Coding Standards

## Refleksi 1

> You already implemented two new features using Spring Boot. Check again your source code and evaluate the coding
> standards that you have learned in this module. Write clean code principles and secure coding practices that have been
> applied to your code. If you find any mistake in your source code, please explain how to improve your code

### Coding Standards yang sudah diterapkan

- **Meaningful Names**

  Menggunakan nama variabel, metode, dan kelas yang deskriptif agar kode lebih mudah dipahami. Contohnya, kelas
  ProductController menangani kontrol produk, sementara metode createProductPage dan editProductPage yang akan digunakan
  untuk merender page create dan edit produk.

- **Object and Data Structure**

  Memisahkan data dan perilaku dengan desain yang jelas untuk menjaga modularitas sistem. Penggunaan Data Transfer
  Object (DTO) membantu memisahkan data yang dikirim dari entitas utama, sehingga menghindari manipulasi langsung pada
  objek domain.

- **Separation of Concerns (SoC)**

  Memisahkan tanggung jawab dalam kode berdasarkan lapisan (layer) seperti controller, service, dan repository. Dalam
  project kali ini, ProductController hanya akan menangani request HTTP, ProductService menangani logika bisnis, dan
  ProductRepository menangani akses data.

- **Consistent Formatting**

  Menerapkan format kode yang konsisten, termasuk indentasi dan penulisan kode yang rapi untuk meningkatkan keterbacaan.

### Secure Code Principles yang sudah diterapkan

- Menggunakan UUID sebagai produk demi memastikan setiap produk memiliki identifikasi yang unik dan sulit diprediksi.

- Menerapkan prinsip enkapsulasi pada class Product dengan menggunakan access modifier private untuk
  variabel-variabelnya.

### Improvisasi yang dapat dilakukan

- Kode saat ini belum menerapkan validasi dan sanitasi input, sehingga masih rentan terhadap SQL Injection dan XSS.
  Untuk mitigasi hal tersebut, saya perlu menerapkan parameterized queries dan escaping input agar data yang diterima
  dari client dipastikan aman.

- Error handling dalam kode belum saya terapkan dengan baik, apabila aplikasi mengalami crash maka pesan yang
  ditampilkan kurang informatif. Saya perlu mengimplementasikan mekanisme exception handling yang jelas, seperti
  try-catch dan logging untuk mempermudah debugging.

## Refleksi 2

> After writing the unit test, how do you feel? How many unit tests should be made in a class? How to make sure that our
> unit tests are enough to verify our program? It would be good if you learned about code coverage. Code coverage is a
> metric that can help you understand how much of your source is tested. If you have 100% code coverage, does that mean
> your code has no bugs or errors?

Setelah membuat unit test, saya merasa lebih percaya diri karena dapat memastikan bahwa kode berjalan sesuai harapan dan
mempermudah dalam mengidentifikasi bug. Meski begitu, tantangan muncul saat menulis test yang efektif tanpa menimbulkan
redundansi, terutama ketika harus menguji fungsi kompleks dengan banyak edge cases.

1. **Berapa banyak unit test yang diperlukan dalam suatu class?**

   Unit test harus mencakup seluruh bagian penting dari class, terutama logika bisnis dan method yang bersifat public.
   Selain itu, perlu dibuat unit test untuk skenario positif dan negatif, serta berbagai kemungkinan input parameter
   untuk menguji ketahanan kode.

2. **Bagaimana memastikan kalau unit testnya sudah cukup?**

   Salah satu cara adalah dengan menggunakan code coverage untuk melihat seberapa banyak kode yang dieksekusi dalam
   pengujian. Namun, selain angka coverage yang tinggi, perlu dilakukan review dari tim atau tester yang berpengalaman
   untuk memastikan pengujian mencakup semua skenario penting.

3. **Jika code coverage mencapai 100%, apakah program bebas bug?**

   Tidak selalu. Code coverage hanya menunjukkan bahwa setiap jalur kode telah diuji, tetapi tidak menjamin bahwa semua
   bug telah ditemukan. Masih ada kemungkinan bug yang muncul akibat interaksi antar-komponen atau skenario yang belum
   terpikirkan dalam unit test.

> What do you think about the cleanliness of the code of the new functional test suite? Will the new code reduce the
> code quality? Identify the potential clean code issues, explain the reasons, and suggest possible improvements to make
> the code cleaner! Please write your reflection inside the repository's README.md file.

Penambahan functional test baru yang hampir identik dengan CreateProductFunctionalTest.java dapat berdampak negatif
terhadap kualitas kode. Berikut adalah beberapa permasalahan yang muncul serta solusi yang dapat diterapkan:

**Masalah**

- **Pengulangan Kode**: Penulisan ulang kode yang identik di banyak kelas menyebabkan redundansi, sehingga jika terjadi
  perubahan, pembaruan harus dilakukan di beberapa tempat.

- **Penurunan Efisiensi**: Duplikasi kode mengurangi modularitas program, sehingga membuat refactoring dan perbaikan di
  masa depan menjadi lebih sulit.

**Solusi**

- **Pembuatan Base Class**: Melakukan refactoring dengan membuat kelas dasar (misalnya, BaseFunctionalTest) yang
  menyatukan semua setup procedure dan variabel umum. Kelas-kelas test lainnya dapat mewarisi kelas ini untuk
  menghindari duplikasi.

- **Implementasi Helper Method**: Gunakan helper methods untuk mengabstraksi fungsi-fungsi yang sering digunakan,
  sehingga kode yang sama tidak perlu ditulis berulang-ulang.

Dengan solusi tersebut, meskipun ada setup procedure yang sama, kualitas kode tetap terjaga karena pemeliharaan menjadi
lebih mudah dan perubahan dapat dilakukan secara terpusat.