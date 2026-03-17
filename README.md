# Dokumentasi API Praktikum 2 - Sistem Manajemen KTP
Dokumentasi ini berisi daftar endpoint untuk manajemen data KTP (Kependudukan) pada proyek Praktikum 2.

## Base URL
`http://localhost:8080`

---

## 1. Tambah Data KTP (Add KTP)
Digunakan untuk menambahkan data KTP baru ke dalam sistem.

*   **URL**: `/ktp`
*   **Method**: `POST`
*   **Headers**: `Content-Type: application/json`

**Request Body**
```json
{
  "nomorKtp": "1234567890123456",
  "namaLengkap": "Hikmatyar Alghifary",
  "alamat": "Jl. Kaliurang KM 5, Yogyakarta",
  "tanggalLahir": "1998-12-25",
  "jenisKelamin": "Laki-laki"
}
```

**Response Body (200 OK)**
```json
{
  "message": "Data KTP berhasil ditambahkan",
  "data": {
    "id": 5,
    "nomorKtp": "1234567890123456",
    "namaLengkap": "Hikmatyar Alghifary",
    "alamat": "Jl. Kaliurang KM 5, Yogyakarta",
    "tanggalLahir": "1998-12-25",
    "jenisKelamin": "Laki-laki"
  }
}
```

---

## 2. Ambil Semua Data KTP (Get All KTP)
Digunakan untuk mendapatkan daftar seluruh KTP yang terdaftar.

*   **URL**: `/ktp`
*   **Method**: `GET`

**Response Body (200 OK)**
```json
{
  "message": "Berhasil mengambil semua data KTP",
  "data": [
    {
      "id": 2,
      "nomorKtp": "1111111111111111",
      "namaLengkap": "Ahmad Syihab",
      "alamat": "Yogyakarta",
      "tanggalLahir": "1990-01-01",
      "jenisKelamin": "Laki-laki"
    },
    {
      "id": 5,
      "nomorKtp": "1234567890123456",
      "namaLengkap": "Hikmatyar Alghifary",
      "alamat": "Jl. Kaliurang KM 5, Yogyakarta",
      "tanggalLahir": "1998-12-25",
      "jenisKelamin": "Laki-laki"
    }
  ]
}
```

---

## 3. Ambil KTP Berdasarkan ID (Get KTP By ID)
Digunakan untuk mendapatkan detail data KTP tertentu menggunakan ID.

*   **URL**: `/ktp/{id}`
*   **Method**: `GET`

**Contoh URL**: `/ktp/5`

**Response Body (200 OK)**
```json
{
  "message": "Berhasil mengambil data KTP",
  "data": {
    "id": 5,
    "nomorKtp": "1234567890123456",
    "namaLengkap": "Hikmatyar Alghifary",
    "alamat": "Jl. Kaliurang KM 5, Yogyakarta",
    "tanggalLahir": "1998-12-25",
    "jenisKelamin": "Laki-laki"
  }
}
```

---

## 4. Update Data KTP
Digunakan untuk memperbarui data KTP yang sudah ada berdasarkan ID.

*   **URL**: `/ktp/{id}`
*   **Method**: `PUT`
*   **Headers**: `Content-Type: application/json`

**Contoh URL**: `/ktp/5`

**Request Body**
```json
{
  "nomorKtp": "1234567890123456",
  "namaLengkap": "Hikmatyar Alghifary (Updated)",
  "alamat": "Bantul, Yogyakarta",
  "tanggalLahir": "1998-12-25",
  "jenisKelamin": "Laki-laki"
}
```

**Response Body (200 OK)**
```json
{
  "message": "Data KTP berhasil diperbarui",
  "data": {
    "id": 5,
    "nomorKtp": "1234567890123456",
    "namaLengkap": "Hikmatyar Alghifary (Updated)",
    "alamat": "Bantul, Yogyakarta",
    "tanggalLahir": "1998-12-25",
    "jenisKelamin": "Laki-laki"
  }
}
```

---

## 5. Hapus Data KTP (Delete KTP)
Digunakan untuk menghapus data KTP berdasarkan ID.

*   **URL**: `/ktp/{id}`
*   **Method**: `DELETE`

**Contoh URL**: `/ktp/4`

**Response Body (200 OK)**
```json
{
  "message": "Data KTP berhasil dihapus",
  "data": null
}
```

---

## 6. Penanganan Request Tidak Valid (Invalid Request Handling)
Jika terjadi duplikasi nomor KTP atau data tidak sesuai, sistem akan mengembalikan response error 400 (Bad Request).

**Skenario**: Menambah KTP dengan nomor yang sudah terdaftar.
**Response Body (400 Bad Request)**
```json
{
  "error": "Nomor KTP sudah terdaftar!"
}
```

---

## 7. Penanganan Data Tidak Ditemukan (KTP Not Found)
Jika ID yang dicari tidak ada dalam sistem, server akan mengembalikan pesan error.

**Method**: `GET / PUT / DELETE`
**URL**: `/ktp/999`
**Response Body (400 Bad Request)**
```json
{
  "error": "Data KTP tidak ditemukan dengan id: 999"
}
```

---

## 8. Screenshot Tampilan Antarmuka Web
![Antarmuka Web CRUD KTP](<img width="1919" height="867" alt="image" src="https://github.com/user-attachments/assets/49f269ea-8b3f-4bd1-8b79-5639a7e7729d" />)
![Antarmuka Web CRUD KTP](<img width="1919" height="553" alt="image" src="https://github.com/user-attachments/assets/da7b96eb-71d6-4fea-a1d0-66237eaf6551" />)
---
*Dikerjakan oleh: Hikmatyar Alghifary - NIM: 20230140193*
