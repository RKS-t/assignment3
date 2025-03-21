package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {


    private String name;
    private String password;
    private String comments;
    private LocalDate calenderDate;
    private LocalDateTime inputDateTime;
    private LocalDateTime updateDateTime;


    public Schedule (String name, String password, String comments, LocalDate calenderDate){
        this.name=name;
        this.password = password;
        this.comments = comments;
        this.calenderDate =calenderDate;
    }

    public Schedule (String name, String comments, LocalDate calenderDate, LocalDate inputDateTime, LocalDate updateDateTime){
        this.name=name;
        this.comments = comments;
        this.calenderDate =calenderDate;
        this.inputDateTime = inputDateTime;
        this.updateDateTime = updateDateTime;
    }

}
