# ScheduleProject

### 프로젝트 소개

* 달력에 일정을 메모하는 스프링 백엔드 프로젝트

* 스프링에 대한 기본적인 사용법과 이해 목표

  


---
### Stacks

Environoment

 <img src="https://img.shields.io/badge/Intellij-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white">

Development

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=&logoColor=white"> 

Frmaework

<img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">

DB

<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 

Communication

<img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white"> <img src="https://img.shields.io/badge/notion-333333?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> 



---

### 주요 기능

* 날짜에 따른 일정을 입력 

* 날짜별, 이름별 이름 조회 기능 
      
* 단건 일정 조회 및 수정 기능

* 일정 삭제 기능

---
### 이 코드의 특징

* 발제에는 일정 날짜가 따로 없지만 이를 추가하여 일정표 뷰에 맞는 코드 제작

* 달력 뷰에 맞는 POST 입력 방식 (경로변수로 입력받음)

* Valid와 일정 핸들러 클래스르를 이용한 예외 처리

* 다건 정보 조회시 일정날짜 순, 최신 수정순으로 일정 정보 반환

---
### API 명세서 

| 기능              | Method | URL                                  | request                                                                                                                                           | response                                                                                                                                                                    | 상태코드         |
|-----------------|--------|--------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------|
| 일정 생성           | POST   | api/schedule/date/{date}             | 요청 body <br>{<br>”name” : “이름”,<br>”password” : “비밀번호”,<br>”contents” :  “일정 내용”<br>}<br>요청 pathvaluable<br>{date} in /date/{date}                | 등록 정보                                                                                                                                                                       | 201: CREATED |
| 전체 일정 조회        | GET    | api/schedule                         | -                                                                                                                                                 | 다건 응답 정보<br>[{<br>”id” = “식별번호”<br>”name” : “이름”,<br>”contents” :  “일정 내용”,<br>”calendarDate” : “ 일정 날짜”,<br>”inputDateTime”: “등록 날짜”,<br>”updateDateTime” : “수정 날짜”<br>}]  | 200: OK      |
| 날짜 선택 일정 조회     | GET    | api/schedule/date/{date}             | 요청 pathvaluable<br>{date} in /date/{date}                                                                                                         | 다건 응답 정보<br>[{<br>”id” = “식별번호”<br>”name” : “이름”,<br>”contents” :  “일정 내용”,<br>”calendarDate” : “ 일정 날짜”,<br>”inputDateTime”: “등록 날짜”,<br>”updateDateTime” : “수정 날짜”<br>}]  | 200: OK      |
| 유저 선택 일정 조회     | GET    | api/schedule/user/{user}             | 요청 pathvaluable<br>{name} in /user/{name}                                                                                                         | 다건 응답 정보<br>[{<br>”id” = “식별번호”<br>”name” : “이름”,<br>”contents” :  “일정 내용”,<br>”calendarDate” : “ 일정 날짜”,<br>”inputDateTime”: “등록 날짜”,<br>”updateDateTime” : “수정 날짜”<br>}]  | 200: OK      |
| 날짜 유저 선택 일정 조회  | GET    | api/schedule/date/{date}/user/{user} | 요청 pathvaluable<br>{date}, {name} in<br>/date/{date}/user/{user}                                                                                  | 다건 응답 정보 <br>[{<br>”id” = “식별번호”<br>”name” : “이름”,<br>”contents” :  “일정 내용”,<br>”calendarDate” : “ 일정 날짜”,<br>”inputDateTime”: “등록 날짜”,<br>”updateDateTime” : “수정 날짜”<br>}] | 200: OK      |
| 날짜 유저 단건 일정 조회  | GET    | api/schedule/post/{id}               | 요청 pathvaluable<br>{id} in /post/{id}                                                                                                             | 단건 응답 정보<br>{<br>”id” = “식별번호”<br>”name” : “이름”,<br>”contents” :  “일정 내용”,<br>”calendarDate” : “ 일정 날짜”,<br>”inputDateTime”: “등록 날짜”,<br>”updateDateTime” : “수정 날짜”<br>}    | 200: OK      |
|  일정 수정          | PATCH  | api/schedule/post/{id}               | 요청 body<br>{<br>”name” : “이름”,<br>”date” : “일정 날짜”<br>”password” : “비밀번호”,<br>”contents” :  “일정 내용”<br>}<br>요청 pathvaluable<br>{id} in /post/{id} | 수정 정보<br>{<br>”id” = “식별번호”<br>”name” : “이름”,<br>”contents” :  “일정 내용”,<br>”calendarDate” : “ 일정 날짜”,<br>”inputDateTime”: “등록 날짜”,<br>”updateDateTime” : “수정 날짜”<br>}       | 200: OK      |
| 선택한 일정 삭제       | DELETE | api/schedule/post/{id}               | 요청 pathvaluable<br> {id} in /post/{id}                                                                                                            | -                                                                                                                                                                           | 200: OK      |

  
  [api 명세서 링크 바로가기](https://www.notion.so/1bd1384f860280cbbf41de3e9907f77d?v=1bd1384f860280a08e2a000c57ec49f3)

---
### ERD

![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbnVWFA%2FbtsMXOD0YGg%2FCg4UepCn8fRO08nH3vFJi0%2Fimg.png)


* 도전과제를 진행하지 않았기 때문에 한개의 표로 표현


### 디렉토리
📦main

 ┣ 📂java
 
 ┃ ┗ 📂com
 
 ┃ ┃ ┗ 📂example
 
 ┃ ┃ ┃ ┗ 📂schedule
 
 ┃ ┃ ┃ ┃ ┣ 📂controller
 
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ScheduleController.java
 
 ┃ ┃ ┃ ┃ ┣ 📂dto
 
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ScheduleDeleteRequestDto.java
 
 ┃ ┃ ┃ ┃ ┃ ┣ 📜SchedulePostRequestDto.java
 
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ScheduleRequestDto.java
 
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ScheduleResponseDto.java
 
 ┃ ┃ ┃ ┃ ┣ 📂entity
 
 ┃ ┃ ┃ ┃ ┃ ┗ 📜Schedule.java
 
 ┃ ┃ ┃ ┃ ┣ 📂handler
 
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ScheduleExceptionHandler.java
 
 ┃ ┃ ┃ ┃ ┣ 📂repository
 
 ┃ ┃ ┃ ┃ ┃ ┣ 📜JdbdTemplateScheduleRepository.java
 
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ScheduleRepository.java
 
 ┃ ┃ ┃ ┃ ┣ 📂service
 
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ScheduleService.java
 
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ScheduleServiceImpl.java
 
 ┃ ┃ ┃ ┃ ┗ 📜ScheduleApplication.java
 
 ┗ 📂resources
 
 ┃ ┣ 📂static
 
 ┃ ┣ 📂templates
 
 ┃ ┗ 📜application.properties
 
 ----

 ### 개발일지기록 
 [티스토리블로그](https://rudtjs2.tistory.com/category/%EA%B3%BC%EC%A0%9C3)




