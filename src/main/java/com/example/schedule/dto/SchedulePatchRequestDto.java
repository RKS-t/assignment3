package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchedulePatchRequestDto {

    private String password;
    private String comments;

}
