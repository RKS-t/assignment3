package com.example.schedule.repository;


import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbdTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbdTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert((jdbcTemplate));
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", schedule.getName());
        parameters.put("comments", schedule.getComments());
        parameters.put("calendarDate", schedule.getCalendarDate());
        parameters.put("inputDateTime", schedule.getInputDateTime());
        parameters.put("password", schedule.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new ScheduleResponseDto(
                schedule.getName(),
                schedule.getComments(),
                schedule.getCalendarDate(),
                schedule.getInputDateTime(),
                schedule.getUpdateDateTime()
        );
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return jdbcTemplate.query("select name, comments, calendarDate, inputDateTime, updateDateTime from schedule",
                scheduleRowsMapper());
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByDate(LocalDate date) {
        return jdbcTemplate.query("select name, comments, calendarDate, inputDateTime, updateDateTime from schedule where calendarDate = ?",
                scheduleRowsMapper(), date);
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByUser(String name) {
        return jdbcTemplate.query("select name, comments, calendarDate, inputDateTime, updateDateTime from schedule where name = ?",
                scheduleRowsMapper(), name);
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByDateUser(LocalDate date, String name) {
        return jdbcTemplate.query("select name, comments, calendarDate, inputDateTime, updateDateTime from schedule where name = ? and calendarDate = ?",
                scheduleRowsMapper(), name, date);
    }

    private RowMapper<ScheduleResponseDto> scheduleRowsMapper() {

        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getString("name"),
                        rs.getString("comments"),
                        rs.getDate("calendarDate").toLocalDate(),
                        rs.getTimestamp("inputDateTime").toLocalDateTime(),
                        Optional.ofNullable(rs.getTimestamp("updateDateTime")).map(Timestamp::toLocalDateTime).orElse(null)
                );
            }
        };
    }



    @Override
    public Optional<Schedule> findScheduleById(LocalDate date, String name, Long id) {
        List<Schedule> resultDate = jdbcTemplate.query(
                "select name, comments, calendarDate, inputDateTime, updateDateTime from schedule " +
                    "where calendarDate = ? and name = ? and id = ?",
                scheduleRowMapper(), date, name);

        return resultDate.stream().findAny();
    }

    @Override
    public Optional<Schedule> findScheduleByPassword(LocalDate date, String name, Long id, String password){
        List<Schedule> resultData = jdbcTemplate.query(
                "select name, comments, calendarDate, inputDateTime, updateDateTime from schedule " +
                    "where name = ? and password = ? and calendarDate = ? and id = ?",
                scheduleRowMapper(), name, password, date, id);

        return  resultData.stream().findAny();
    }

    private RowMapper<Schedule> scheduleRowMapper() {

        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getString("name"),
                        rs.getString("comments"),
                        rs.getDate("calendarDate").toLocalDate(),
                        rs.getTimestamp("inputDateTime").toLocalDateTime(),
                        Optional.ofNullable(rs.getTimestamp("updateDateTime")).map(Timestamp::toLocalDateTime).orElse(null)
                );
            }
        };
    }

    public int updateSchedule(LocalDate date, String name, String comments, LocalDateTime updateDateTime){
        return jdbcTemplate.update(
                "update schedule set comments = ?, updateDateTime = ? " +
                    "where calendardate = ? and name = ? and comments != ?",
                comments, updateDateTime, date, name);
    }

    @Override
    public void deleteSchedule(LocalDate date, String name, Long id, String password) {

    }
}
