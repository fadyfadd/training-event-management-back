package com.training.training_event_management_back.ServicesTest;

import com.training.training_event_management_back.DataTransferObjects.EventDto;
import com.training.training_event_management_back.Entities.Event;
import com.training.training_event_management_back.Repositories.CourseRepository;
import com.training.training_event_management_back.Repositories.EventRepository;
import com.training.training_event_management_back.Repositories.TeacherRepository;
import com.training.training_event_management_back.Services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@ActiveProfiles("test")
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEvents_returnsDtoList() {
        Event event = new Event();
        event.setId(1L);
        event.setTitle("Test Event");
        event.setDescription("Desc");
        event.setStartDate(LocalDate.now());
        event.setEndDate(LocalDate.now().plusDays(1));
        event.setMaxStudent(30L);

        when(eventRepository.findAll()).thenReturn(Collections.singletonList(event));

        List<EventDto> dtos = eventService.getAllEvents();

        assertEquals(1, dtos.size());
        assertEquals("Test Event", dtos.get(0).getTitle());
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    void testGetEventById_found() {
        Event event = new Event();
        event.setId(1L);
        event.setTitle("Sample");

        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        EventDto dto = eventService.getEventById(1L);

        assertEquals("Sample", dto.getTitle());
    }

    @Test
    void testGetEventById_notFound_throwsException() {
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            eventService.getEventById(1L);
        });

        assertTrue(ex.getMessage().contains("not found"));
    }

    @Test
    void testCreateEvent_success() {
        EventDto dto = new EventDto();
        dto.setTitle("New Event");
        dto.setDescription("Desc");
        dto.setStartDate(LocalDate.now());
        dto.setEndDate(LocalDate.now().plusDays(1));
        dto.setMaxStudent(20L);

        Event savedEvent = new Event();
        savedEvent.setId(1L);
        savedEvent.setTitle(dto.getTitle());
        savedEvent.setDescription(dto.getDescription());
        savedEvent.setStartDate(dto.getStartDate());
        savedEvent.setEndDate(dto.getEndDate());
        savedEvent.setMaxStudent(dto.getMaxStudent());

        when(eventRepository.save(any(Event.class))).thenReturn(savedEvent);

        EventDto result = eventService.createEvent(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Event", result.getTitle());
        verify(eventRepository, times(1)).save(any(Event.class));
    }
}
