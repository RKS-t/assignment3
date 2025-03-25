package com.example.schedule.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public class SchedulePostRequestDto {

    @NotBlank(message = "이름을 정확히 입력하세요")
    private String name;
    @NotBlank(message = "비밀번호를 정확히 입력하세요")
    private String password;
    @Size(max = 200, message = "한글 100자까지만 작성 가능합니다.")
    private String comments;


}
