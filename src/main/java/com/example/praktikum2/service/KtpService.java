package com.example.praktikum2.service;

import com.example.praktikum2.dto.KtpDTO;
import java.util.List;

public interface KtpService {
    KtpDTO createKtp(KtpDTO dto);
    List<KtpDTO> getAllKtp();
    KtpDTO getKtpById(Integer id);
    KtpDTO updateKtp(Integer id, KtpDTO dto);
    void deleteKtp(Integer id);
}
