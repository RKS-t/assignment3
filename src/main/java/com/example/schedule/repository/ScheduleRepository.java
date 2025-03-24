package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    ScheduleResponseDto saveSchedule(Schedule schedule);
    List<ScheduleResponseDto> findAllSchedule();
    List<ScheduleResponseDto> findScheduleByDate(LocalDate date);
    List<ScheduleResponseDto> findScheduleByUser(String name);
    List<ScheduleResponseDto> findScheduleByDateUser(LocalDate date, String name);
    Optional<Schedule> findScheduleById(Long id);
    Optional<Schedule> findScheduleByPassword(Long id, String password);
    int updateSchedule(Long id, LocalDate date, String name, String comments, LocalDateTime updateDateTime);
    void deleteSchedule(Long id, String password);
}
