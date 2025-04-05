# EARLY_ADMISSION_PHYSICAL

```sql
-- 수시 모집 실기 점수 정보
CREATE TABLE IF NOT EXISTS EARLY_ADMISSION_PHYSICAL (
    EARLY_ADMISSION_PHYSICAL_ID INT AUTO_INCREMENT PRIMARY KEY COMMENT '수시 모집 실기 점수 아이디',
    ADMISSION_ID INT COMMENT '입시 요강 아이디',
    
    -- 실기 교과목 사용 여부
    USE_SUBJECT1 CHAR(1) COMMENT '실기 교과목 1 사용 여부(Y: 사용, N: 미사용)',
    USE_SUBJECT2 CHAR(1) COMMENT '실기 교과목 2 사용 여부(Y: 사용, N: 미사용)',
    USE_SUBJECT3 CHAR(1) COMMENT '실기 교과목 3 사용 여부(Y: 사용, N: 미사용)',
    USE_SUBJECT4 CHAR(1) COMMENT '실기 교과목 4 사용 여부(Y: 사용, N: 미사용)',
    USE_SUBJECT5 CHAR(1) COMMENT '실기 교과목 5 사용 여부(Y: 사용, N: 미사용)',
    USE_SUBJECT6 CHAR(1) COMMENT '실기 교과목 6 사용 여부(Y: 사용, N: 미사용)',
    USE_SUBJECT7 CHAR(1) COMMENT '실기 교과목 7 사용 여부(Y: 사용, N: 미사용)',
    USE_SUBJECT8 CHAR(1) COMMENT '실기 교과목 8 사용 여부(Y: 사용, N: 미사용)',
    USE_SUBJECT9 CHAR(1) COMMENT '실기 교과목 9 사용 여부(Y: 사용, N: 미사용)',
    USE_SUBJECT10 CHAR(1) COMMENT '실기 교과목 10 사용 여부(Y: 사용, N: 미사용)',
    
    -- 실기 교과목 ID
    SUBJECT1_ID INT COMMENT '실기 교과목 1 ID',
    SUBJECT2_ID INT COMMENT '실기 교과목 2 ID',
    SUBJECT3_ID INT COMMENT '실기 교과목 3 ID',
    SUBJECT4_ID INT COMMENT '실기 교과목 4 ID',
    SUBJECT5_ID INT COMMENT '실기 교과목 5 ID',
    SUBJECT6_ID INT COMMENT '실기 교과목 6 ID',
    SUBJECT7_ID INT COMMENT '실기 교과목 7 ID',
    SUBJECT8_ID INT COMMENT '실기 교과목 8 ID',
    SUBJECT9_ID INT COMMENT '실기 교과목 9 ID',
    SUBJECT10_ID INT COMMENT '실기 교과목 10 ID',
    
    -- 실기 교과목 평가 방법
    SUBJECT1_EVALUATION_METHOD CHAR(1) COMMENT '실기 교과목 1 평가 방법(1: 절대평가, 2: 상대평가)',
    SUBJECT2_EVALUATION_METHOD CHAR(1) COMMENT '실기 교과목 2 평가 방법(1: 절대평가, 2: 상대평가)',
    SUBJECT3_EVALUATION_METHOD CHAR(1) COMMENT '실기 교과목 3 평가 방법(1: 절대평가, 2: 상대평가)',
    SUBJECT4_EVALUATION_METHOD CHAR(1) COMMENT '실기 교과목 4 평가 방법(1: 절대평가, 2: 상대평가)',
    SUBJECT5_EVALUATION_METHOD CHAR(1) COMMENT '실기 교과목 5 평가 방법(1: 절대평가, 2: 상대평가)',
    SUBJECT6_EVALUATION_METHOD CHAR(1) COMMENT '실기 교과목 6 평가 방법(1: 절대평가, 2: 상대평가)',
    SUBJECT7_EVALUATION_METHOD CHAR(1) COMMENT '실기 교과목 7 평가 방법(1: 절대평가, 2: 상대평가)',
    SUBJECT8_EVALUATION_METHOD CHAR(1) COMMENT '실기 교과목 8 평가 방법(1: 절대평가, 2: 상대평가)',
    SUBJECT9_EVALUATION_METHOD CHAR(1) COMMENT '실기 교과목 9 평가 방법(1: 절대평가, 2: 상대평가)',
    SUBJECT10_EVALUATION_METHOD CHAR(1) COMMENT '실기 교과목 10 평가 방법(1: 절대평가, 2: 상대평가)',
    
    CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    CREATED_BY VARCHAR(50) COMMENT '생성자',
    UPDATED_BY VARCHAR(50) COMMENT '수정자',
    
    FOREIGN KEY (ADMISSION_ID) REFERENCES ADMISSIONS(ADMISSION_ID)
) COMMENT '수시 모집 실기 점수 정보';
```
