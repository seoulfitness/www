package kr.seoulfitness.admin.earlyAdmissionInternal;

import java.util.Date;

import lombok.Data;

@Data
public class EarlyAdmissionInternalDto {
    private int earlyAdmissionInternalId; // 수시 모집 내신 점수 아이디
    private int admissionId;              // 입시 요강 아이디

    // 내신 점수
    private double internalReflectedGrade1;    // 내신 등급 1 반영 점수(0.000 ~ 100.000)
    private double internalReflectedGrade2;    // 내신 등급 2 반영 점수(0.000 ~ 100.000)
    private double internalReflectedGrade3;    // 내신 등급 3 반영 점수(0.000 ~ 100.000)
    private double internalReflectedGrade4;    // 내신 등급 4 반영 점수(0.000 ~ 100.000)
    private double internalReflectedGrade5;    // 내신 등급 5 반영 점수(0.000 ~ 100.000)
    private double internalReflectedGrade6;    // 내신 등급 6 반영 점수(0.000 ~ 100.000)
    private double internalReflectedGrade7;    // 내신 등급 7 반영 점수(0.000 ~ 100.000)
    private double internalReflectedGrade8;    // 내신 등급 8 반영 점수(0.000 ~ 100.000)
    private double internalReflectedGrade9;    // 내신 등급 9 반영 점수(0.000 ~ 100.000)

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
