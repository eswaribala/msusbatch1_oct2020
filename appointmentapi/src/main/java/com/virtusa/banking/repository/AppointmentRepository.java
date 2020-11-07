package com.virtusa.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virtusa.banking.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

}
