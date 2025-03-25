package com.example.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

    @NotBlank(message = "이름을 정확히 입력하세요")
    private String name;
    @Size(max = 200, message = "한글 100자까지만 작성 가능합니다.")
    private String comments;
    @NotBlank(message = "비밀번호를 정확히 입력하세요")
    private String password;
    @NotNull(message = "날짜를 정확히 입력하세요")
    private LocalDate date;

    public ScheduleRequestDto(SchedulePostRequestDto dto, LocalDate calendarDate){


        this.name = dto.getName();
        this.comments = dto.getComments();
        this.password = dto.getPassword();
        this.date = calendarDate;

    }

    public ScheduleRequestDto(){}


}
