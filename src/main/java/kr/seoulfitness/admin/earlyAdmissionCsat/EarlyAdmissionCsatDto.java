package kr.seoulfitness.admin.earlyAdmissionCsat;

import java.util.Date;

import lombok.Data;

@Data
public class EarlyAdmissionCsatDto {
    // 수시 모집 수능 점수 정보
    private int earlyAdmissionCsatId;       // 수시 모집 수능 점수 아이디
    private int admissionId;                // 입시 요강 아이디
    private String useType;                 // 사용 구분('Y' : 사용, 'N' : 미사용)    
    private int scoreType;                  // 점수 반영 구분(1: 표준점수, 2: 백분위, 3: 기타방법)
    private int scienceSubject;             // 탐구 교과목 반영 구분(1: 동일 과목 허용, 2: 동일 과목 미허용)
    private String earlyAdmissionCsatMemo;  // 수시 모집 수능 점수 메모  
    private int subject1ReflectedScore;     // 국어 반영 점수(0.0 ~ 1000.0)
    private int subject2ReflectedScore;     // 수학 반영 점수(0.0 ~ 1000.0)
    private int subject3ReflectedScore;     // 사회문화 반영 점수
    private int subject4ReflectedScore;     // 생활과윤리 반영 점수
    private int subject5ReflectedScore;     // 윤리와사상 반영 점수
    private int subject6ReflectedScore;     // 정치와법 반영 점수
    private int subject7ReflectedScore;     // 경제 반영 점수
    private int subject8ReflectedScore;     // 한국지리 반영 점수
    private int subject9ReflectedScore;     // 세계지리 반영 점수
    private int subject10ReflectedScore;    // 세계사 반영 점수
    private int subject11ReflectedScore;    // 동아시아사 반영 점수
    private int subject12ReflectedScore;    // 화학1 반영 점수
    private int subject13ReflectedScore;    // 화학2 반영 점수
    private int subject14ReflectedScore;    // 생명과학1 반영 점수
    private int subject15ReflectedScore;    // 생명과학2 반영 점수
    private int subject16ReflectedScore;    // 물리1 반영 점수
    private int subject17ReflectedScore;    // 물리2 반영 점수
    private int subject18ReflectedScore;    // 지구과학1 반영 점수
    private int subject19ReflectedScore;    // 지구과학2 반영 점수
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
