use schedule;
CREATE DATABASE schedule;

-- 스케쥴 테이블 생성
CREATE TABLE schedule
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자',
    name VARCHAR(50) NOT NULL COMMENT  '이름',
    password VARCHAR(50) NOT NULL COMMENT '비밀번호',
    comments TEXT COMMENT '일정',
    inputDateTime DATETIME NOT NULL COMMENT '입력날짜',
    updateDateTime DATETIME COMMENT '수정날짜',
    calendarDate DATE NOT NULL COMMENT '일정날짜'
);

