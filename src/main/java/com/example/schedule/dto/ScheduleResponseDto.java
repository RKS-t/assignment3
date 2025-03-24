package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;
    private String name;
    private String comments;
    private LocalDate calendarDate;
    private LocalDateTime inputDateTime;
    private LocalDateTime updateDateTime;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.name = schedule.getName();
        this.comments =schedule.getComments();
        this.calendarDate = schedule.getCalendarDate();
        this.inputDateTime = schedule.getInputDateTime();
        this.updateDateTime = schedule.getUpdateDateTime();
    }

}
