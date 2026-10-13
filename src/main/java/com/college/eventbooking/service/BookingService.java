package com.college.eventbooking.service;

import com.college.eventbooking.entity.Booking;
import com.college.eventbooking.entity.Event;
import com.college.eventbooking.repository.BookingRepository;
import com.college.eventbooking.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;

    public BookingService(
            BookingRepository bookingRepository,
            EventRepository eventRepository) {
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByEmail(String email) {
        return bookingRepository.findByStudentEmail(email);
    }

    @Transactional
    public Booking createBooking(Booking booking) {
        if (booking.getTickets() <= 0) {
            throw new IllegalArgumentException(
                    "Ticket quantity must be greater than zero");
        }

        Event event = eventRepository.findById(
                        booking.getEventId())
                .orElseThrow(() ->
                        new RuntimeException("Event not found"));

        if (booking.getStudentName() == null ||
                booking.getStudentName().isBlank() ||
                booking.getStudentEmail() == null ||
                booking.getStudentEmail().isBlank()) {
            throw new IllegalArgumentException(
                    "Student name and email are required");
        }

        if (event.getAvailableSeats() < booking.getTickets()) {
            throw new IllegalArgumentException(
                    "Not enough tickets available");
        }

        event.setAvailableSeats(
                event.getAvailableSeats() - booking.getTickets());

        eventRepository.save(event);

        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        Event event = eventRepository.findById(
                        booking.getEventId())
                .orElseThrow(() ->
                        new RuntimeException("Event not found"));

        event.setAvailableSeats(
                event.getAvailableSeats() + booking.getTickets());

        eventRepository.save(event);
        bookingRepository.delete(booking);
    }
}