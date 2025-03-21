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
        return jdbcTemplate.query("select name, comments, calendarDate, inputDateTime, updateDateTime from schedule", scheduleRowMapper());
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {

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
    public Optional<Schedule> findScheduleByDate(LocalDate date) {
        List<Schedule> resultDate = jdbcTemplate.query("select name, comments, calendarDate, inputDateTime, updateDateTime from schedule where calendarDate = ?", scheduleRowMapperDate(), date);

        return resultDate.stream().findAny();

    }

    private RowMapper<Schedule> scheduleRowMapperDate() {

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

    @Override
    public Optional<Schedule> findScheduleByUser(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Schedule> findSchudule(LocalDate date, String name) {
        return Optional.empty();
    }
}
