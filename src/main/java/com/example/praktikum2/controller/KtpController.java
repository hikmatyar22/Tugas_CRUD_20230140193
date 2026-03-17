package com.example.praktikum2.controller;

import com.example.praktikum2.dto.KtpDTO;
import com.example.praktikum2.model.WebResponse;
import com.example.praktikum2.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ktp")
@CrossOrigin(origins = "*")
public class KtpController {

    @Autowired
    private KtpService ktpService;

    @PostMapping
    public WebResponse<KtpDTO> createKtp(@RequestBody KtpDTO dto) {
        KtpDTO result = ktpService.createKtp(dto);
        return new WebResponse<>("Data KTP berhasil ditambahkan", result);
    }

    @GetMapping
    public WebResponse<List<KtpDTO>> getAllKtp() {
        List<KtpDTO> result = ktpService.getAllKtp();
        return new WebResponse<>("Berhasil mengambil semua data KTP", result);
    }

    @GetMapping("/{id}")
    public WebResponse<KtpDTO> getKtpById(@PathVariable Integer id) {
        KtpDTO result = ktpService.getKtpById(id);
        return new WebResponse<>("Berhasil mengambil data KTP", result);
    }

    @PutMapping("/{id}")
    public WebResponse<KtpDTO> updateKtp(@PathVariable Integer id, @RequestBody KtpDTO dto) {
        KtpDTO result = ktpService.updateKtp(id, dto);
        return new WebResponse<>("Data KTP berhasil diperbarui", result);
    }

    @DeleteMapping("/{id}")
    public WebResponse<String> deleteKtp(@PathVariable Integer id) {
        ktpService.deleteKtp(id);
        return new WebResponse<>("Data KTP berhasil dihapus", null);
    }
}
