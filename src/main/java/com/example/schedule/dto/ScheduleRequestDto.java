package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    private String name;
    private String comments;
    private String password;
    private LocalDate date;

    public ScheduleRequestDto(SchedulePostRequestDto dto, LocalDate calendarDate){

        this.name = dto.getName();
        this.comments = dto.getComments();
        this.password = dto.getPassword();
        this.date = calendarDate;

    }

    public ScheduleRequestDto(){}


}
