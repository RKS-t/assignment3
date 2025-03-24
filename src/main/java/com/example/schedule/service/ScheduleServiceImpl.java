package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto dto) {


        Schedule schedule = new Schedule(dto.getName(), dto.getPassword(), dto.getComments(), dto.getDate(), LocalDateTime.now(), null );

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
    public List<ScheduleResponseDto> findScheduleByDateUser(LocalDate date, String name) {
        List<ScheduleResponseDto> schedules = scheduleRepository.findScheduleByDateUser(date, name);

        if(schedules.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, date + "에" + name + "의 일정이 없습니다." );
        }

        return schedules;
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        if(optionalSchedule.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 없습니다." );
        }

        return new ScheduleResponseDto(optionalSchedule.get());
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, String password, LocalDate date, String name, String contents) {

        if(password == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호를 입력해 주세요");
        } else if (name == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이름을 입력해 주세요");
        } else if (date == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "날짜를 입력해 주세요");
        }



        Optional<Schedule> optionalPasswordSchedule = scheduleRepository.findScheduleByPassword(id, password);
        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        if (optionalSchedule.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 없습니다." );
        } else if (optionalPasswordSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀립니다." );
        }

        int updatedRow = scheduleRepository.updateSchedule(id, date, name, contents, LocalDateTime.now());
        System.out.println(updatedRow);
        if(updatedRow==0){
            System.out.println(updatedRow);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "변경된 사항이 없습니다.");
        }


        return new ScheduleResponseDto(optionalPasswordSchedule.get());
    }

    @Override
    public void deleteSchedule(Long id, String password) {

        Optional<Schedule> optionalPasswordSchedule = scheduleRepository.findScheduleByPassword(id, password);
        Optional<Schedule> optionalSchedule = scheduleRepository.findScheduleById(id);

        if (optionalSchedule.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일정이 없습니다." );
        } else if (optionalPasswordSchedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀립니다." );
        }

        scheduleRepository.deleteSchedule(id, password);

    }


}
