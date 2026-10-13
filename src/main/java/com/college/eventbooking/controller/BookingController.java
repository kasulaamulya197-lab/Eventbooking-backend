package com.college.eventbooking.controller;

import com.college.eventbooking.entity.Booking;
import com.college.eventbooking.service.BookingService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return service.getAllBookings();
    }

    @GetMapping("/student")
    public List<Booking> getBookingsByEmail(
            @RequestParam String email) {
        return service.getBookingsByEmail(email);
    }

    @PostMapping
    public Booking createBooking(
            @RequestBody Booking booking) {
        return service.createBooking(booking);
    }

    @DeleteMapping("/{id}")
    public void cancelBooking(@PathVariable Long id) {
        service.cancelBooking(id);
    }
}