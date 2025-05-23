# ADMISSION

```sql
-- 정시 수능 점수
CREATE TABLE IF NOT EXISTS REGULAR_ADMISSION_CSAT (
    REGULAR_ADMISSION_CSAT_ID INT AUTO_INCREMENT PRIMARY KEY COMMENT '수시(입시) 요강 아이디',
    ADMISSION_ID INT COMMENT '수능 정보 아이디',
    SCORE_TYPE INT NOT NULL COMMENT '점수 반영 구분(1: 표준점수, 2: 백분위, 3: 기타방법)',
    SCORE_TYPE2 INT NOT NULL COMMENT '탐구 교과목 반영 구분(1: 동일 과목 허용, 2: 동일 과목 미허용)',
    SUBJECT1_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '국어 반영 점수(0.0 ~ 100.0)',
    SUBJECT2_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '수학 반영 점수(0.0 ~ 100.0)',
    SUBJECT3_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '사회문화 반영 점수(0.0 ~ 100.0)',
    SUBJECT4_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '생활과윤리 반영 점수(0.0 ~ 100.0)',
    SUBJECT5_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '윤리와사상 반영 점수(0.0 ~ 100.0)',
    SUBJECT6_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '정치와법 반영 점수(0.0 ~ 100.0)',
    SUBJECT7_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '경제 반영 점수(0.0 ~ 100.0)',
    SUBJECT8_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '한국지리 반영 점수(0.0 ~ 100.0)',
    SUBJECT9_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '세계지리 반영 점수(0.0 ~ 100.0)',
    SUBJECT10_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '세계사 반영 점수(0.0 ~ 100.0)',
    SUBJECT11_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '동아시아사 반영 점수(0.0 ~ 100.0)',
    SUBJECT12_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '화학1 반영 점수(0.0 ~ 100.0)',
    SUBJECT13_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '화학2 반영 점수(0.0 ~ 100.0)',
    SUBJECT14_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '생명과학1 반영 점수(0.0 ~ 100.0)',
    SUBJECT15_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '생명과학2 반영 점수(0.0 ~ 100.0)',
    SUBJECT16_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '물리1 반영 점수(0.0 ~ 100.0)',
    SUBJECT17_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '물리2 반영 점수(0.0 ~ 100.0)',
    SUBJECT18_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '지구과학1 반영 점수(0.0 ~ 100.0)',
    SUBJECT19_REFLECTED_SCORE DECIMAL(5, 2) COMMENT '지구과학2 반영 점수(0.0 ~ 100.0)',
    REGULAR_ADMISSION_CSAT_MEMO TEXT COMMENT '수시(입시) 요강 메모',
    CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성일시',
    UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    CREATED_BY VARCHAR(50) COMMENT '등록한 사람',
    UPDATED_BY VARCHAR(50) COMMENT '수정한 사람'
);
```
