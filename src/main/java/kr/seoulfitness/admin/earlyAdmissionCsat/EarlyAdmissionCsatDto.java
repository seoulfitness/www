package kr.seoulfitness.admin.earlyAdmissionCsat;

import java.util.Date;

import lombok.Data;

@Data
public class EarlyAdmissionCsatDto {
    // 수시 모집 수능 정보
    private int earlyAdmissionCsatId;       // 수시 모집 수능 정보 아이디
    private int admissionId;                // 입시 요강 아이디   
    private int scoreType;                  // 점수 반영 구분(1: 표준점수, 2: 백분위, 3: 기타방법)
    private int scoreType2;             // 탐구 교과목 반영 구분(1: 동일 과목 허용, 2: 동일 과목 미허용)
    private String earlyAdmissionCsatMemo;  // 수시 모집 수능 점수 메모  
    
    // 수능 교과목 반영 점수
    private double subject1ReflectedScore;     // 국어 반영 점수(0.0 ~ 100.0)
    private double subject2ReflectedScore;     // 수학 반영 점수(0.0 ~ 100.0)
    private double subject3ReflectedScore;     // 사회문화 반영 점수(0.0 ~ 100.0)
    private double subject4ReflectedScore;     // 생활과윤리 반영 점수(0.0 ~ 100.0)
    private double subject5ReflectedScore;     // 윤리와사상 반영 점수(0.0 ~ 100.0)
    private double subject6ReflectedScore;     // 정치와법 반영 점수(0.0 ~ 100.0)
    private double subject7ReflectedScore;     // 경제 반영 점수(0.0 ~ 100.0)
    private double subject8ReflectedScore;     // 한국지리 반영 점수(0.0 ~ 100.0)
    private double subject9ReflectedScore;     // 세계지리 반영 점수(0.0 ~ 100.0)
    private double subject10ReflectedScore;    // 세계사 반영 점수(0.0 ~ 100.0)
    private double subject11ReflectedScore;    // 동아시아사 반영 점수(0.0 ~ 100.0)
    private double subject12ReflectedScore;    // 화학1 반영 점수(0.0 ~ 100.0)
    private double subject13ReflectedScore;    // 화학2 반영 점수(0.0 ~ 100.0)
    private double subject14ReflectedScore;    // 생명과학1 반영 점수(0.0 ~ 100.0)
    private double subject15ReflectedScore;    // 생명과학2 반영 점수(0.0 ~ 100.0)
    private double subject16ReflectedScore;    // 물리1 반영 점수(0.0 ~ 100.0)
    private double subject17ReflectedScore;    // 물리2 반영 점수(0.0 ~ 100.0)
    private double subject18ReflectedScore;    // 지구과학1 반영 점수(0.0 ~ 100.0)
    private double subject19ReflectedScore;    // 지구과학2 반영 점수(0.0 ~ 100.0)
    
    private Date createdAt;                 // 생성일시
    private Date updatedAt;                 // 수정일시
    private String createdBy;               // 생성자
    private String updatedBy;               // 수정자

    // 학교 정보
    private String schoolName;          // 학교 이름

    // 학과 정보
    private String departmentName;      // 학과 이름

    // 수시 모집 수능 점수 정보
    private String admissionYear;       // 입시 연도
    private String admissionType;       // 입시 구분
    private String earlyAdmission;      // 수시 입시 여부
    private String regularAdmission;    // 정시 입시 여부
    
    // 등록한 사람, 수정한 사람 정보
    private String createdUserName;     // 등록한 사람 이름
    private String updatedUserName;     // 수정한 사람 이름
    private String createdUserPhone;    // 등록한 사람 전화번호
    private String updatedUserPhone;    // 수정한 사람 전화번호
}
