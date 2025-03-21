package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class FullInputDto {

    private String name;
    private String comments;
    private String password;
    private LocalDate calenderDate;

    public  FullInputDto (ScheduleRequestDto dto, LocalDate calenderDate ){

        this.name = dto.getName();
        this.comments = dto.getComments();
        this.password = dto.getPassword();
        this.calenderDate = calenderDate;

    }



}
