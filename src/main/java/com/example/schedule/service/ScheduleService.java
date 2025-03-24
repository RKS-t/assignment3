package com.example.schedule.service;

import com.example.schedule.dto.FullInputDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(FullInputDto dto);
    List<ScheduleResponseDto> findAllSchedule();
    List<ScheduleResponseDto> findScheduleByDate(LocalDate date);
    List<ScheduleResponseDto> findScheduleByUser(String name);
    List<ScheduleResponseDto> findScheduleByDateUser (LocalDate date, String name);
    ScheduleResponseDto findScheduleById(LocalDate date, String name, Long id);
    ScheduleResponseDto updateSchedule (LocalDate date, String name, Long id, String password, String comments);
    void deleteSchedule (LocalDate date, String name, Long id, String password);
}
