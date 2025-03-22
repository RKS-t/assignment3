package com.example.schedule.service;

import com.example.schedule.dto.FullInputDto;
import com.example.schedule.dto.SchedulePatchRequestDto;
import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    @Override
    public ScheduleResponseDto saveSchedule(FullInputDto dto) {


        Schedule schedule = new Schedule(dto.getName(), dto.getPassword(), dto.getComments(), dto.getCalendarDate(), LocalDateTime.now(), null );

        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return scheduleRepository.findAllSchedule();
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByDate(LocalDate date) {
        List<ScheduleResponseDto> schedules = scheduleRepository.findScheduleByDate(date);

        if(schedules.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, date + "에 일정이 없습니다." );
        }

        return schedules;
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByUser(String name) {
        List<ScheduleResponseDto> schedules = scheduleRepository.findScheduleByUser(name);

        if(schedules.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, name + "의 일정이 없습니다." );
        }

        return schedules;
    }

    @Override
    public ScheduleResponseDto findSchedule(LocalDate date, String name) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findSchedule(date, name);

        if(optionalSchedule.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, date + "에" + name + "의 일정이 없습니다." );
        }

        return new ScheduleResponseDto(optionalSchedule.get());
    }

    @Override
    public ScheduleResponseDto updateSchedule(LocalDate date, String name, String password, String contents) {

        if(password == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호를 입력해 주세요");
        }

        Optional<Schedule> optionalPasswordSchedule = scheduleRepository.findScheduleByPassword(date, name, password);
        Optional<Schedule> optionalSchedule = scheduleRepository.findSchedule(date, name);

        if (optionalSchedule.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, date + "에" + name + "의 일정이 없습니다." );
        } else if (optionalPasswordSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀립니다." );
        }

        int updatedRow = scheduleRepository.updateSchedule(date, name, contents, LocalDateTime.now());

        if(updatedRow==1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "변경된 사항이 없습니다.");
        }


        return new ScheduleResponseDto(optionalPasswordSchedule.get());
    }
}
