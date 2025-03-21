package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long id;
    private String name;
    private String password;
    private String comments;
    private LocalDate inputDateTime;
    private LocalDate updateDateTime;
    private LocalDate calenderDate;

    public Schedule (String name, String password, String comments, LocalDate calenderDate){
        this.name=name;
        this.password = password;
        this.comments = comments;
        this.calenderDate =calenderDate;
    }

}
