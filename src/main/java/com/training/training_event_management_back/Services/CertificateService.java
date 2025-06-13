package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.Entities.Certificate;
import com.training.training_event_management_back.Repositories.CertificateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateService {

    private CertificateRepository repository;
    public CertificateService(CertificateRepository repository) {
        this.repository = repository;
    }
    public List<Certificate> getAllCertificates(){
        return repository.findAll();
    }
    public Certificate getCertificateById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found with id: "+id));
    }
    public Certificate createCertificate(Certificate certificate){
        return repository.save(certificate);
    }
    public Certificate updateCertificate(Long id, Certificate updatedCertificate){
        Certificate existing = getCertificateById(id);
        existing.setEvent(updatedCertificate.getEvent());
        existing.setCourseId(updatedCertificate.getCourseId());
        return repository.save(existing);
    }
    public void deleteCertificate(Long id){
        repository.deleteById(id);
    }
}