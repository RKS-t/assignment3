package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchedulePostRequestDto {

    private String name;
    private String password;
    private String comments;


}
