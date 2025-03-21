package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);
    List<ScheduleResponseDto> findAllSchedule();
    List<ScheduleResponseDto> findScheduleByDate(LocalDate date);
    List<ScheduleResponseDto> findScheduleByUser(String name);
    Optional<Schedule> findSchedule (LocalDate date, String name);
}
