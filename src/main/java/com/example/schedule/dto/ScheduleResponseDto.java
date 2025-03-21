package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private String name;
    private String comments;
    private LocalDate callenderDate;
    private LocalDateTime inputDateTime;
    private LocalDateTime updateDateTime;

    public ScheduleResponseDto(Schedule schedule){
        this.name = schedule.getName();
        this.comments =schedule.getComments();
        this.callenderDate = schedule.getCalenderDate();
        this.inputDateTime = schedule.getInputDateTime();
        this.updateDateTime = schedule.getUpdateDateTime();
    }

}
