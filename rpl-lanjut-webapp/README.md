# Tugas RPL Lanjut — Kalkulator Bangun Datar & Persamaan Kuadrat

Project ini dibungkus sebagai **Maven web app** (bukan project manual),
karena penyebab paling umum "gabisa jalan di VSCode" untuk Servlet/JSP itu
karena classpath dan struktur WAR-nya di-setup manual dan salah.
Dengan Maven, semua itu di-handle otomatis.

## Struktur

```
rpl-lanjut-webapp/
├── pom.xml
└── src/main/
    ├── java/com/app/servlet/
    │   ├── PersegiServlet.java
    │   └── PersamaanKuadratServlet.java
    └── webapp/
        ├── index.jsp
        ├── persegi.jsp
        ├── kuadrat.jsp
        └── WEB-INF/web.xml
```

## Yang perlu di-install dulu

1. **JDK 17** (atau minimal JDK 11) — cek dengan `java -version`
2. **Apache Tomcat 10.x** — download dari https://tomcat.apache.org/download-10.cgi
   (harus Tomcat **10 ke atas** karena project ini pakai `jakarta.servlet.*`,
   bukan `javax.servlet.*`. Kalau kampus/dosen mewajibkan Tomcat 9 ke bawah,
   lihat catatan di bagian bawah).
3. **Maven** — cek dengan `mvn -version`
4. Ekstensi VSCode:
   - **Extension Pack for Java** (Microsoft)
   - **Community Server Connectors** (biar VSCode bisa deploy ke Tomcat langsung)

## Cara menjalankan (paling gampang: build WAR lalu deploy manual)

1. Buka folder `rpl-lanjut-webapp` ini di VSCode.
2. Buka terminal di VSCode, jalankan:
   ```
   mvn clean package
   ```
   Ini akan menghasilkan file `target/rpl-lanjut-webapp.war`.
3. Copy file `.war` tersebut ke folder `webapps/` di instalasi Tomcat kamu.
4. Jalankan Tomcat:
   - Windows: jalankan `bin\startup.bat`
   - Mac/Linux: jalankan `bin/startup.sh`
5. Buka browser ke:
   ```
   http://localhost:8080/rpl-lanjut-webapp/
   ```
   Nanti muncul menu untuk pilih Soal 1&2 atau Soal 3.

## Cara alternatif (langsung dari VSCode pakai Community Server Connectors)

1. Install extension **Community Server Connectors**.
2. Buka tab **Servers** di sidebar VSCode → klik **+** → pilih **Tomcat Server** →
   arahkan ke folder instalasi Tomcat kamu.
3. Klik kanan project ini di Explorer → **Add Deployment...** → pilih Tomcat server tadi.
4. Klik kanan server → **Start Server**.
5. Akses `http://localhost:8080/rpl-lanjut-webapp/`.

## Kalau server kampus pakai Tomcat 9 ke bawah

Berarti servlet API-nya masih `javax.servlet.*`, bukan `jakarta.servlet.*`. Yang perlu diubah:

1. Di `pom.xml`, ganti dependency:
   ```xml
   <dependency>
       <groupId>javax.servlet</groupId>
       <artifactId>javax.servlet-api</artifactId>
       <version>4.0.1</version>
       <scope>provided</scope>
   </dependency>
   ```
2. Di kedua file `.java`, ganti semua `import jakarta.servlet.*` menjadi `import javax.servlet.*`.
3. Di `WEB-INF/web.xml`, ganti `version="5.0"` dan namespace `jakarta.ee` menjadi
   versi Servlet 4.0 (`http://xmlns.jcp.org/xml/ns/javaee`).

## Troubleshooting umum

- **404 Not Found saat akses**: pastikan context path sesuai nama file WAR
  (misalnya `rpl-lanjut-webapp.war` → `http://localhost:8080/rpl-lanjut-webapp/`).
- **HTTP 500 / ClassNotFoundException**: biasanya karena versi Servlet API di
  `pom.xml` gak cocok sama versi Tomcat yang dipakai (lihat bagian Tomcat 9 di atas).
- **Port 8080 sudah dipakai**: matikan aplikasi lain yang pakai port itu, atau ubah
  port Tomcat di `conf/server.xml`.
