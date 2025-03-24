package com.example.schedule.entity;

import com.example.schedule.dto.SchedulePatchRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String name;
    private String password;
    private String comments;
    private LocalDate calendarDate;
    private LocalDateTime inputDateTime;
    private LocalDateTime updateDateTime;


    public Schedule (Long id, String name, String password, String comments, LocalDate calendarDate){
        this.id = id;
        this.name=name;
        this.password = password;
        this.comments = comments;
        this.calendarDate = calendarDate;
    }

    public Schedule (Long id, String name, String comments, LocalDate calendarDate, LocalDateTime inputDateTime, LocalDateTime updateDateTime){
        this.id =id;
        this.name=name;
        this.comments = comments;
        this.calendarDate = calendarDate;
        this.inputDateTime = inputDateTime;
        this.updateDateTime = updateDateTime;
    }

}
