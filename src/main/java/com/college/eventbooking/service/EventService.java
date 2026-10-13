package com.college.eventbooking.service;

import com.college.eventbooking.entity.Event;
import com.college.eventbooking.repository.EventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    public Event getEvent(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Event not found"));
    }

    public Event addEvent(Event event) {
        if (event.getTotalSeats() < 0 ||
                event.getPrice() < 0) {
            throw new IllegalArgumentException(
                    "Price and seats cannot be negative");
        }

        if (event.getAvailableSeats() == 0 &&
                event.getTotalSeats() > 0) {
            event.setAvailableSeats(event.getTotalSeats());
        }

        if (event.getAvailableSeats() > event.getTotalSeats()) {
            throw new IllegalArgumentException(
                    "Available seats cannot exceed total seats");
        }

        return repository.save(event);
    }

    public Event updateEvent(Long id, Event updated) {
        Event event = getEvent(id);

        event.setName(updated.getName());
        event.setDescription(updated.getDescription());
        event.setDate(updated.getDate());
        event.setVenue(updated.getVenue());
        event.setPrice(updated.getPrice());
        event.setTotalSeats(updated.getTotalSeats());
        event.setAvailableSeats(updated.getAvailableSeats());

        return repository.save(event);
    }

    public void deleteEvent(Long id) {
        repository.delete(getEvent(id));
    }
}