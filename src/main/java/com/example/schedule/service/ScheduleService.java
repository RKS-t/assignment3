package com.example.schedule.service;

import com.example.schedule.dto.FullInputDto;
import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(FullInputDto dto);
    List<ScheduleResponseDto> findAllSchedule();
    List<ScheduleResponseDto> findScheduleByDate(LocalDate date);
    List<ScheduleResponseDto> findScheduleByUser(String name);
    ScheduleResponseDto findSchedule (LocalDate date, String name);
}
