package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.DataTransferObjects.CertificateDto;
import com.training.training_event_management_back.Entities.Certificate;
import com.training.training_event_management_back.Repositories.CertificateRepository;
import com.training.training_event_management_back.Repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificateService {

    private final CertificateRepository repository;
    private final EventRepository eventRepository;

    public CertificateService(CertificateRepository repository, EventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public List<CertificateDto> getAllCertificates() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CertificateDto getCertificateById(Long id) {
        Certificate certificate = getEntityById(id);
        return toDto(certificate);
    }

    public CertificateDto createCertificate(CertificateDto dto) {
        Certificate certificate = new Certificate();
        copyFromDto(dto, certificate);
        return toDto(repository.save(certificate));
    }

    public CertificateDto updateCertificate(Long id, CertificateDto dto) {
        Certificate certificate = getEntityById(id);
        copyFromDto(dto, certificate);
        return toDto(repository.save(certificate));
    }

    public void deleteCertificate(Long id) {
        repository.deleteById(id);
    }

    // Helper: fetch entity or throw
    private Certificate getEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found with id: " + id));
    }

    // Convert Entity -> DTO
    private CertificateDto toDto(Certificate certificate) {
        CertificateDto dto = new CertificateDto();
        dto.setCourseId(certificate.getCourseId());

        if (certificate.getEvent() != null) {
            dto.setEventId(certificate.getEvent().getId());
            dto.setEventTitle(certificate.getEvent().getTitle());
        }

        return dto;
    }


    private void copyFromDto(CertificateDto dto, Certificate certificate) {
        certificate.setCourseId(dto.getCourseId());

        if (dto.getEventId() != null) {
            certificate.setEvent(
                    eventRepository.findById(dto.getEventId())
                            .orElseThrow(() -> new RuntimeException("Event not found with id: " + dto.getEventId()))
            );
        } else {
            certificate.setEvent(null);
        }
    }
}
