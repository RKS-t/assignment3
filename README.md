# ScheduleProject

### 프로젝트 소개

* 달력에 일정을 메모하는 스프링 백엔드 프로젝트

* 스프링에 대한 기본적인 사용법과 이해 목표


---
### Stacks

Environoment

 <img src="https://img.shields.io/badge/Intellij-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> 

Development

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 

Communication

<img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white"> <img src="https://img.shields.io/badge/notion-333333?style=for-the-badge&logo=notion&logoColor=white">

<img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white">

---

### 주요 기능

* 날짜에 따른 POST 로 일정 입력 

* 날짜별, 이름별 이름 조회 기능 
      
* 단건 일정 조회 및 수정 기능

* 일정 삭제 기능

---
### 이 코드의 특징

* Valid와 일정 핸들러 클래스르를 이용한 예외 처리

* 다건 정보 조회시 일정날짜 순, 최신 수정순으로 일정 정보 반환

* 

* 예외 처리 후 코드의 방향성을 설정 (계산기 피드백 반영)

* 반복되는 출력함수를 함수로 최대한 간결하게 표기

* 메뉴가 추가되어도 기능이 정상 작동하도록 함

---
### API 명세서 

* 메뉴 아이템 항목 추가시 Menu 생성자 라인에 추가하세요

*<a href= " { https://www.notion.so/1bd1384f860280cbbf41de3e9907f77d?v=1bd1384f860280a08e2a000c57ec49f3 } "></a> 

* 상위그룹이름과 리스트는 순서가 같도록 넣어주세요.

---
### ERD

![image]([https://user-images.githubusercontent.com/110077343/189138295-4cb8995b-fc9b-48b9-b790-2426ce1b59b6.png](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbnVWFA%2FbtsMXOD0YGg%2FCg4UepCn8fRO08nH3vFJi0%2Fimg.png))


* 반복되는 긴 항목의 변수, 리스트, 객체들 미리 선언하여 거기에 저장함으로써 가독성 향상 및 최적화해보기

* 주문리스트 저장시도 스트림으로 표현, 카운트 구분하는것도 포함하여 삭제함수를 스트림으로 표현하여 코드 간결화해보기

* 스캐너 입력문을 일일이 예외처리하지말고 하나의 함수로 표현해보기

* 깃 허브 이용을 일찍하여 커밋을 잘 하기

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
 [티스토리블로그](https://rudtjs2.tistory.com/category/%EA%B3%BC%EC%A0%9C2)




