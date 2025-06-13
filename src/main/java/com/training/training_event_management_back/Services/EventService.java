package com.training.training_event_management_back.Services;

import com.training.training_event_management_back.Entities.Event;
import com.training.training_event_management_back.Repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> getAllEvents(){
        return repository.findAll();
    }

    public Event getEventById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event with id: " +id+ "not found"));
    }

    public Event createEvent(Event event) {
        return repository.save(event);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        Event existing = getEventById(id);
        existing.setTitle(updatedEvent.getTitle());
        existing.setDescription(updatedEvent.getDescription());
        existing.setStartDate(updatedEvent.getStartDate());
        existing.setEndDate(updatedEvent.getEndDate());
        existing.setMaxStudent(updatedEvent.getMaxStudent());
        existing.setCourse(updatedEvent.getCourse());
        existing.setTeacher(updatedEvent.getTeacher());
        existing.setStudents(updatedEvent.getStudents());
        existing.setAttendanceList(updatedEvent.getAttendanceList());
        existing.setCertificateList(updatedEvent.getCertificateList());
        return repository.save(existing);
    }

    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }
}