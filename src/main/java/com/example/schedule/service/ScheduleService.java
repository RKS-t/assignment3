package com.example.schedule.service;

import com.example.schedule.dto.FullInputDto;
import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(FullInputDto dto);
    List<ScheduleResponseDto> findAllSchedule();
    ScheduleResponseDto findScheduleByDate(LocalDate date);

}
