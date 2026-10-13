package com.college.eventbooking.repository;

import com.college.eventbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository
        extends JpaRepository<Booking, Long> {

    List<Booking> findByStudentEmail(String studentEmail);

    List<Booking> findByEventId(Long eventId);
}