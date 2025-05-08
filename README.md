# 서울피트니스 웹 애플리케이션

서울피트니스 웹 애플리케이션은 입시 요강 및 모집 정보를 관리하고 제공하는 웹 서비스입니다.

## 기술 스택

- **백엔드**: Spring MVC 5.3.x
- **프론트엔드**: JSP, JSTL
- **데이터베이스**: MariaDB
- **ORM**: MyBatis
- **빌드 도구**: Maven
- **서버**: Tomcat (내장)
- **기타 라이브러리**:
  - HikariCP (커넥션 풀)
  - Lombok
  - Jackson (JSON 처리)
  - Spring Security Crypto
  - Log4j 2

## 주요 기능

- 입시 요강 관리 및 조회
- 수시 모집 정보 관리
- 정시 모집 정보 관리
- 관리자 기능
- 사용자 인증

## 프로젝트 구조

```
src/main/
├── java/kr/seoulfitness/
│   ├── HomeController.java (메인 컨트롤러)
│   ├── admin/ (관리자 기능)
│   ├── auth/ (인증 관련)
│   ├── interceptor/ (인터셉터)
│   ├── api/ (API 관련)
│   ├── libs/ (유틸리티)
│   └── user/ (사용자 관련)
├── resources/ (설정 파일)
└── webapp/
    ├── static/ (정적 자원)
    └── WEB-INF/
        ├── views/ (JSP 뷰)
        ├── spring/ (스프링 설정)
        └── web.xml (웹 설정)
```

## 빌드 및 실행 방법

### 필수 요구사항
- Java 8 이상
- Maven
- MariaDB

### 빌드 방법
```bash
# 프로젝트 빌드
mvn clean package
```

### 실행 방법
```bash
# 내장 Tomcat으로 실행
mvn tomcat7:run
```

## 관리자 접속
- URL: `/admin`
- 초기 계정은 관리자에게 문의하세요.

## 라이선스
이 프로젝트는 독점 소프트웨어로, 저작권은 서울피트니스에 있습니다.
