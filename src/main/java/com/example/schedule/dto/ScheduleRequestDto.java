package com.example.schedule.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleRequestDto {

    private String name;
    private String password;
    private String comments;
    private LocalDate calenderDate;

}
