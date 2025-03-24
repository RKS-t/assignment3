package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class SchedulePatchRequestDto {

    private String name;
    private String password;
    private String comments;

}
