package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto dto);
    List<ScheduleResponseDto> findAllSchedule();
    List<ScheduleResponseDto> findScheduleByDate(LocalDate date);
    List<ScheduleResponseDto> findScheduleByUser(String name);
    List<ScheduleResponseDto> findScheduleByDateUser (LocalDate date, String name);
    ScheduleResponseDto findScheduleById(Long id);
    ScheduleResponseDto updateSchedule (Long id, String password, LocalDate date, String name, String comments);
    void deleteSchedule (Long id, String password);
}
