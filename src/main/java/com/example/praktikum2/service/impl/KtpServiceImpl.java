package com.example.praktikum2.service.impl;

import com.example.praktikum2.entity.Ktp;
import com.example.praktikum2.repository.KtpRepository;
import com.example.praktikum2.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository ktpRepository;

    @Autowired
    private com.example.praktikum2.mapper.KtpMapper ktpMapper;

    @Override
    public com.example.praktikum2.dto.KtpDTO createKtp(com.example.praktikum2.dto.KtpDTO dto) {
        if (ktpRepository.existsByNomorKtp(dto.getNomorKtp())) {
            throw new RuntimeException("Nomor KTP sudah terdaftar!");
        }
        Ktp ktp = ktpMapper.toEntity(dto);
        Ktp saved = ktpRepository.save(ktp);
        return ktpMapper.toDTO(saved);
    }

    @Override
    public List<com.example.praktikum2.dto.KtpDTO> getAllKtp() {
        return ktpRepository.findAll().stream()
                .map(ktpMapper::toDTO)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public com.example.praktikum2.dto.KtpDTO getKtpById(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan dengan id: " + id));
        return ktpMapper.toDTO(ktp);
    }

    @Override
    public com.example.praktikum2.dto.KtpDTO updateKtp(Integer id, com.example.praktikum2.dto.KtpDTO dto) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan dengan id: " + id));
        
        if (!ktp.getNomorKtp().equals(dto.getNomorKtp()) && 
            ktpRepository.existsByNomorKtp(dto.getNomorKtp())) {
            throw new RuntimeException("Nomor KTP baru sudah digunakan!");
        }

        ktp.setNomorKtp(dto.getNomorKtp());
        ktp.setNamaLengkap(dto.getNamaLengkap());
        ktp.setAlamat(dto.getAlamat());
        ktp.setTanggalLahir(dto.getTanggalLahir());
        ktp.setJenisKelamin(dto.getJenisKelamin());

        return ktpMapper.toDTO(ktpRepository.save(ktp));
    }

    @Override
    public void deleteKtp(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data KTP tidak ditemukan dengan id: " + id));
        ktpRepository.delete(ktp);
    }
}
