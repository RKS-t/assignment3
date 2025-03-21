package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class FullInputDto {

    private String name;
    private String comments;
    private String password;
    private LocalDate calendarDate;

    public  FullInputDto (ScheduleRequestDto dto, LocalDate calendarDate){

        this.name = dto.getName();
        this.comments = dto.getComments();
        this.password = dto.getPassword();
        this.calendarDate = calendarDate;

    }



}
