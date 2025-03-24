package com.example.schedule.controller;


import com.example.schedule.dto.*;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
    //날짜에 따른 일정 업데이트
    @PostMapping("/date/{date}")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto dto, @PathVariable LocalDate date){

        FullInputDto fullInputDto = new FullInputDto(dto, date);

        return new ResponseEntity<>(scheduleService.saveSchedule(fullInputDto), HttpStatus.CREATED);
    }
    //전체 일정 조회
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule(){
        return scheduleService.findAllSchedule();
    }
    //날짜별 일정 조회
    @GetMapping("/date/{date}")
    public List<ScheduleResponseDto> findScheduleByDate(@PathVariable LocalDate date){
        return scheduleService.findScheduleByDate(date);
    }
    //이름별 일정 조회
    @GetMapping("/user/{name}")
    public List<ScheduleResponseDto> findScheduleByUser(@PathVariable String name){
        return scheduleService.findScheduleByUser(name);
    }
    //날짜, 이름별 일정 조회
    @GetMapping("/date/{date}/user/{name}")
    public List<ScheduleResponseDto> findScheduleByDateTime(
            @PathVariable LocalDate date, @PathVariable String name
    ){
        return scheduleService.findScheduleByDateUser(date, name);
    }
    //날짜, 이름 이름 단건 조회
    @GetMapping("/date/{date}/user/{name}/post/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(
            @PathVariable LocalDate date, @PathVariable String name,@PathVariable Long id
    ){
        return new ResponseEntity<>(scheduleService.findScheduleById(date, name, id), HttpStatus.OK);
    }
    //단건 일정 수정
    @PatchMapping("/date/{date}/user/{name}/post/{id}")
    public  ResponseEntity<ScheduleResponseDto> updateSchedule(
            @RequestBody SchedulePatchRequestDto dto,
            @PathVariable LocalDate date, @PathVariable String name, @PathVariable Long id
    ){
        return new ResponseEntity<>(scheduleService.updateSchedule(date, name, id, dto.getPassword(), dto.getComments()), HttpStatus.OK);
    }
    //단건 일정 삭제
    @DeleteMapping("/date/{date}/user/{name}/post/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @RequestBody ScheduleDeleteRequestDto dto,
            @PathVariable LocalDate date, @PathVariable String name, @PathVariable Long id){

        scheduleService.deleteSchedule(date, name, id, dto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
