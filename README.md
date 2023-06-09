<details>
<summary><a href="/ch14/">더 학습할 내용</a></summary>
<div markdown="1">

- 단축 URL 기능 개발하기
- 프로파일 설정하기
- 캐시 적용하기
- 인터셉터 적용하기

</div>
</details>

## 02장: 개발에 앞서 알면 좋은 기초 지식
### 2.1 서버 간 통신
### 2.2 스프링 부트의 동작 방식
### 2.3 레이어드 아키텍처
### 2.4 디자인 패턴
- 2.4.1 디자인 패턴의 종류
- 2.4.2 생성 패턴
- 2.4.3 구조 패턴
- 2.4.4 행위 패턴
### 2.5 REST API
- 2.5.1 REST란?
- 2.5.2 REST API란?
- 2.5.3 REST의 특징
- 2.5.4 REST의 URI 설계 규칙

## [05장: API를 작성하는 다양한 방법](/ch5/)
### 5.1 프로젝트 설정
### 5.2 GET API 만들기
- 5.2.1 @RequestMapping으로 구현하기
- 5.2.2 매개변수가 없는 GET 메서드 구현
- 5.2.3 @PathVariable을 활용한 GET 메서드 구현
- 5.2.4 @RequestParam을 활용한 GET 메서드 구현
- 5.2.5 DTO 객체를 활용한 GET 메서드 구현
### 5.3 POST API 만들기
- 5.3.1 @RequestMapping으로 구현하기
- 5.3.2 @RequestBody를 활용한 POST 메서드 구현
### 5.4 PUT API 만들기
- 5.4.1 @RequestBody를 활용한 PUT 메서드 구현
- 5.4.2 ResponseEntity를 활용한 PUT 메서드 구현
### 5.5 DELETE API 만들기
- 5.5.1 @PathVariable과 @RequestParam을 활용한 DELETE 메서드 구현
### 5.6 REST API 명세를 문서화하는 방법 – Swagger
### 5.7 로깅 라이브러리 – Logback
- 5.7.1 Logback 설정
- 5.7.2 Logback 적용하기
 
## [06장: 데이터베이스 연동](/ch6/)
### 6.1 마리아DB 설치
### 6.2 ORM
### 6.3 JPA
### 6.4 하이버네이트
- 6.4.1 Spring Data JPA
### 6.5 영속성 컨텍스트
- 6.5.1 엔티티 매니저
- 6.5.2 엔티티의 생명주기
### 6.6 데이터베이스 연동
- 6.6.1 프로젝트 생성
### 6.7 엔티티 설계
- 6.7.1 엔티티 관련 기본 어노테이션
### 6.8 리포지토리 인터페이스 설계
- 6.8.1 리포지토리 인터페이스 생성
- 6.8.2 리포지토리 메서드의 생성 규칙
### 6.9 DAO 설계
- 6.9.1 DAO 클래스 생성
### 6.10 DAO 연동을 위한 컨트롤러와 서비스 설계
- 6.10.1 서비스 클래스 만들기
- 6.10.2 컨트롤러 생성
- 6.10.3 Swagger API를 통한 동작 확인
 
## [07장: 테스트 코드 작성하기](/ch6/)
### 7.1 테스트 코드를 작성하는 이유
### 7.2 단위 테스트와 통합 테스트
- 7.2.1 단위 테스트의 특징
- 7.2.2 통합 테스트의 특징
### 7.3 테스트 코드를 작성하는 방법
- 7.3.1 Given-When-Then 패턴
- 7.3.2 좋은 테스트를 작성하는 5가지 속성(F.I.R.S.T)
### 7.4 JUnit을 활용한 테스트 코드 작성
- 7.4.1 JUnit의 세부 모듈
- 7.4.2 스프링 부트 프로젝트 생성
- 7.4.3 스프링 부트의 테스트 설정
- 7.4.4 JUnit의 생명주기
- 7.4.5 스프링 부트에서의 테스트
- 7.4.6 컨트롤러 객체의 테스트
- 7.4.7 서비스 객체의 테스트
- 7.4.8 리포지토리 객체의 테스트
### 7.5 JaCoCo를 활용한 테스트 커버리지 확인
- 7.5.1 JaCoCo 플러그인 설정
- 7.5.2 JaCoCo 테스트 커버리지 확인
### 7.6 테스트 주도 개발(TDD)
- 7.6.1 테스트 주도 개발의 개발 주기
- 7.6.2 테스트 주도 개발의 효과
 
## [08장: Spring Data JPA 활용](/ch8/)
### 8.1 프로젝트 생성
### 8.2 JPQL
### 8.3 쿼리 메서드 살펴보기
- 8.3.1 쿼리 메서드의 생성
- 8.3.2 쿼리 메서드의 주제 키워드
- 8.3.3 쿼리 메서드의 조건자 키워드
### 8.4 정렬과 페이징 처리
- 8.4.1 정렬 처리하기
- 8.4.2 페이징 처리
### 8.5 @Query 어노테이션 사용하기
### 8.6 QueryDSL 적용하기
- 8.6.1 QueryDSL이란?
- 8.6.2 QueryDSL의 장점
- 8.6.3 QueryDSL을 사용하기 위한 프로젝트 설정
- 8.6.4 기본적인 QueryDSL 사용하기
- 8.6.5 QuerydslPredicateExecutor, QuerydslRepositorySupport 활용
### 8.7 JPA Auditing 적용
- 8.7.1 JPA Auditing 기능 활성화
- 8.7.2 BaseEntity 만들기
 
## [09장: 연관관계 매핑](/ch9/)
### 9.1 연관관계 매핑 종류와 방향
### 9.2 프로젝트 생성
### 9.3 일대일 매핑
- 9.3.1 일대일 단방향 매핑
- 9.3.2 일대일 양방향 매핑
### 9.4 다대일, 일대다 매핑
- 9.4.1 다대일 단방향 매핑
- 9.4.2 다대일 양방향 매핑
- 9.4.3 일대다 단방향 매핑
### 9.5 다대다 매핑
- 9.5.1 다대다 단방향 매핑
- 9.5.2 다대다 양방향 매핑
### 9.6 영속성 전이
- 9.6.1 영속성 전이 적용
- 9.6.2 고아 객체
 
## [10장: 유효성 검사와 예외 처리](/ch10/)
### 10.1 일반적인 애플리케이션 유효성 검사의 문제점
### 10.2 Hibernate Validator
### 10.3 스프링 부트에서의 유효성 검사
- 10.3.1 프로젝트 생성
- 10.3.2 스프링 부트용 유효성 검사 관련 의존성 추가
- 10.3.3 스프링 부트의 유효성 검사
- 10.3.4 @Validated 활용
- 10.3.5 커스텀 Validation 추가
### 10.4 예외 처리
- 10.4.1 예외와 에러
- 10.4.2 예외 클래스
- 10.4.3 예외 처리 방법
- 10.4.4 스프링 부트의 예외 처리 방식
- 10.4.5 커스텀 예외
- 10.4.6 커스텀 예외 클래스 생성하기

## [12장: 서버 간 통신](/ch12/)
### 12.1 RestTemplate이란?
- 12.1.1 RestTemplate의 동작 원리
- 12.1.2 RestTemplate의 대표적인 메서드
### 12.2 RestTemplate 사용하기
- 12.2.1 서버 프로젝트 생성하기
- 12.2.2 RestTemplate 구현하기
- 12.2.3 RestTemplate 커스텀 설정
### 12.3 WebClient란?
- 12.3.1 WebClient 구성
### 12.4 WebClient 사용하기
- 12.4.1 WebClient 구현
 
## [13장: 서비스의 인증과 권한 부여](/ch13/)
### 13.1 보안 용어 이해
- 13.1.1 인증
- 13.1.2 인가
- 13.1.3 접근 주체
### 13.2 스프링 시큐리티
### 13.3 스프링 시큐리티의 동작 구조
### 13.4 JWT
- 13.4.1 JWT의 구조
- 13.4.2 JWT 디버거 사용하기
### 13.5 스프링 시큐리티와 JWT 적용
- 13.5.1 UserDetails와 UserDetailsService 구현
- 13.5.2 JwtTokenProvider 구현
- 13.5.3 JwtAuthenticationFilter 구현
- 13.5.4 SecurityConfiguration 구현
- 13.5.5 커스텀 AccessDeniedHandler, AuthenticationEntryPoint 구현
- 13.5.6 회원가입과 로그인 구현
- 13.5.7 스프링 시큐리티 테스트