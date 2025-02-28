# ESHOP Advance Programming

Nama: Wisnu Nugroho

NPM: 2306275084

Kelas: Prolan-A

Deployment: [Eshop on Koyeb](https://evil-virgie-wsnugroho-c810dfaf.koyeb.app)

<hr>

# Modul 3 - Maintainability & OO Principles

## Refleksi 3

> Explain what principles you apply to your project!

1. **Single Responsibility Principle (SRP)**: Saya memisahkan class `CarController` ke file tersendiri terpisah dari
   `ProductController`. Hal ini saya lakukan karena setiap file per controller seharusnya menghandle masing-masing
   tasknya
   tersendiri, dalam hal ini `ProductController` menghandle routing `/product/*` sedangkan `CarController` menghandle
   routing `/car/*`.
2. **Open/Closed Principle (OCP)**: Saya menggunakan interface agar kode saya dapat diperluas tanpa harus diubah. Dengan
   penggunaan interface `CarService` sebagai kontrak antara controller dan implementasinya, saya bisa menambah
   implementasi baru di masa depan tanpa mengubah kode controllernya.
3. **Liskov Substitution Principle (LSP)**: Saya mengubah tipe dependency di `CarController` dari implementasi konkret
   `CarServiceImpl` menjadi interface `CarService`, sehingga implementasi apapun dari `CarService` dapat digunakan tanpa
   merusak fungsionalitas aplikasi.
4. **Interface Segregation Principle (ISP)**: Saya terapkan dengan memastikan interface yang dibuat hanya berisi metode
   yang memang dibutuhkan. Interface seperti `CarService` dan `ProductService` hanya berisi operasi-operasi penting
   seperti create, find, update, dan delete, tanpa memaksa implementasi metode yang tidak dibutuhkan.
5. **Dependency Inversion Principle (DIP)**: Saya terapkan dengan cara membuat class-class high-level (seperti
   controller) tidak bergantung langsung pada implementasi konkret, melainkan pada abstraksi berupa interface. Spring
   Boot membantu implementasi DIP melalui fitur dependency injection dengan annotation `@Autowired`, dimana object yang
   dibutuhkan tidak perlu dibuat secara manual dengan operator new, tetapi cukup dideklarasikan sebagai dependency yang
   kemudian akan disediakan oleh framework.

> Explain the advantages of applying SOLID principles to your project with examples.

Menerapkan prinsip SOLID dapat memberikan banyak keuntungan. Pertama, maintenance kode menjadi lebih mudah
karena setiap kelas memiliki tanggung jawab yang terdefinisi dengan jelas. Misalnya, perubahan pada logika bisnis mobil
hanya mempengaruhi `CarServiceImpl` tanpa perlu mengubah controller atau repository. Kemudahan pengujian juga meningkat
signifikan. Dengan ketergantungan pada interface seperti `CarService`, saya dapat dengan mudah membuat mock untuk
pengujian. Sebagai contoh, untuk menguji `CarController`, saya bisa membuat mock dari `CarService` tanpa perlu
implementasi aktual, sehingga pengujian unit dapat dilakukan secara terisolasi. Fleksibilitas dan skalabilitas kode juga
meningkat. Contohnya, jika suatu saat saya ingin menambahkan implementasi alternatif seperti `TruckServiceImpl` yang
mengimplementasi `CarService`, saya dapat melakukannya tanpa mengubah kode di `CarController`. Controller tidak perlu
tahu implementasi mana yang digunakan, karena bergantung pada interface.

> Explain the disadvantages of not applying SOLID principles to your project with examples.

Tidak menerapkan prinsip SOLID dapat menimbulkan berbagai masalah. Pertama, refactoring dan pemeliharaan kode menjadi
sulit.
Misalnya, jika `CarController` dan `ProductController` berada dalam satu file, perubahan pada logika mobil bisa
berdampak
tidak sengaja pada fitur produk, menciptakan bug yang susah dilacak. Ketergantungan kuat antar kelas (tight coupling)
juga akan membuat kode menjadi kaku dan sulit diubah. Contohnya, jika `CarController` langsung bergantung pada
`CarServiceImpl`, mengganti implementasi service akan memerlukan perubahan kode di controller tersebut. Selain itu,
proses unit testing menjadi lebih menantang. Tanpa penggunaan interface dan dependency injection, membuat mock untuk
komponen akan lebih rumit.

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