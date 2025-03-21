package com.example.schedule.controller;


import com.example.schedule.dto.FullInputDto;
import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/date/{date}")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto, @PathVariable LocalDate date){

        FullInputDto fullInputDto = new FullInputDto(dto, date);

        return new ResponseEntity<>(scheduleService.saveSchedule(fullInputDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule(){
        return scheduleService.findAllSchedule();
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<ScheduleResponseDto> findScheduleByDate(@PathVariable LocalDate date){

        return new ResponseEntity<>(scheduleService.findScheduleByDate(date),HttpStatus.OK);
    }


}
