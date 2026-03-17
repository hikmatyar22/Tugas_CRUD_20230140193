package com.example.praktikum2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "KTP")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ktp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String nomorKtp;

    @Column(nullable = false)
    private String namaLengkap;

    private String alamat;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalLahir;

    private String jenisKelamin;
}
