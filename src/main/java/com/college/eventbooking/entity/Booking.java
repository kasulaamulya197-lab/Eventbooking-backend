package com.college.eventbooking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String studentEmail;
    private Long eventId;
    private int tickets;
    private LocalDateTime bookingTime;

    public Booking() {}

    @PrePersist
    public void setBookingTime() {
        if (bookingTime == null) {
            bookingTime = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public int getTickets() { return tickets; }
    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
}