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

    //저장 레파지토리 매서드
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
                key.longValue(),
                schedule.getName(),
                schedule.getComments(),
                schedule.getCalendarDate(),
                schedule.getInputDateTime(),
                schedule.getUpdateDateTime()
        );
    }

    //전체 조회
    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return jdbcTemplate.query("select id, name, comments, calendarDate, inputDateTime, updateDateTime from schedule order by calendarDate, updateDateTime desc",
                scheduleRowsMapper());
    }

    //날짜별 조회
    @Override
    public List<ScheduleResponseDto> findScheduleByDate(LocalDate date) {
        return jdbcTemplate.query("select id, name, comments, calendarDate, inputDateTime, updateDateTime from schedule where calendarDate = ?order by updateDateTime desc",
                scheduleRowsMapper(), date);
    }

    //이름별 조회
    @Override
    public List<ScheduleResponseDto> findScheduleByUser(String name) {
        return jdbcTemplate.query("select id, name, comments, calendarDate, inputDateTime, updateDateTime " +
                        "from schedule where name = ? order by calendarDate",
                scheduleRowsMapper(), name);
    }

    //날짜별, 이름별 조회
    @Override
    public List<ScheduleResponseDto> findScheduleByDateUser(LocalDate date, String name) {
        return jdbcTemplate.query("select id, name, comments, calendarDate, inputDateTime, updateDateTime " +
                        "from schedule where name = ? and calendarDate = ? ",
                scheduleRowsMapper(), name, date);
    }

    //다건 매핑 함수
    private RowMapper<ScheduleResponseDto> scheduleRowsMapper() {

        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("comments"),
                        rs.getDate("calendarDate").toLocalDate(),
                        rs.getTimestamp("inputDateTime").toLocalDateTime(),
                        Optional.ofNullable(rs.getTimestamp("updateDateTime")).map(Timestamp::toLocalDateTime).orElse(null)
                );
            }
        };
    }


    //이름 단건 조회
    @Override
    public Optional<Schedule> findScheduleById(Long id) {
        List<Schedule> resultDate = jdbcTemplate.query(
                "select id, name, password, comments, calendarDate, inputDateTime, updateDateTime " +
                        "from schedule where id = ?",
                scheduleRowMapper(), id);

        return resultDate.stream().findAny();
    }

    //단건 매핑 함수
    private RowMapper<Schedule> scheduleRowMapper() {

        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        rs.getString("comments"),
                        rs.getDate("calendarDate").toLocalDate(),
                        rs.getTimestamp("inputDateTime").toLocalDateTime(),
                        Optional.ofNullable(rs.getTimestamp("updateDateTime")).map(Timestamp::toLocalDateTime).orElse(null)
                );
            }
        };
    }

    //단건 수정 함수
    public int updateSchedule(Long id, LocalDate date, String name, String comments, LocalDateTime updateDateTime){
        return jdbcTemplate.update(
                "update schedule set calendardate = ?, name = ?, comments = ?, updateDateTime = ?" +
                    "where (calendardate != ? or name != ? or comments != ?) and id = ?",
                date, name, comments, updateDateTime, date, name, comments, id);
    }

    //단건 삭제 함수
    @Override
    public void deleteSchedule(Long id, String password) {
        jdbcTemplate.update("delete FROM schedule where id = ? and password = ?",
                id, password);
    }
}
