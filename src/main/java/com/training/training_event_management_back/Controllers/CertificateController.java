package com.training.training_event_management_back.Controllers;


import com.training.training_event_management_back.DataTransferObjects.CertificateDto;
import com.training.training_event_management_back.Entities.Certificate;
import com.training.training_event_management_back.Services.CertificateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/certificates")
@RestController

public class CertificateController {
    private final CertificateService certificateService;
    public CertificateController (CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public ResponseEntity<List<CertificateDto>> getAllCertificates() {
        List<CertificateDto> output = certificateService.getAllCertificates();
        return ResponseEntity.ok(output);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateDto> getCertificateById(@PathVariable Long id) {
        try{
            CertificateDto certificate = certificateService.getCertificateById(id);
            return ResponseEntity.ok(certificate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CertificateDto> createCertificate(@RequestBody CertificateDto certificate) {
        CertificateDto created = certificateService.createCertificate(certificate);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificateDto> updateCertificate(@PathVariable Long id, @RequestBody CertificateDto certificate) {
        try{
            CertificateDto updatedCertificate = certificateService.updateCertificate(id, certificate);
            return ResponseEntity.ok(updatedCertificate);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificate(@PathVariable Long id) {
        certificateService.deleteCertificate(id);
        return ResponseEntity.noContent().build();
    }
}