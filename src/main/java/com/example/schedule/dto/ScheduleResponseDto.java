package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;

import java.time.LocalDate;

public class ScheduleResponseDto {

    private String name;
    private String comments;
    private LocalDate date;

    public ScheduleResponseDto(Schedule schedule){
        this.name = schedule.getName();
        this.comments =schedule.getComments();
        this.date = schedule.getInputDateTime();
    }

}
