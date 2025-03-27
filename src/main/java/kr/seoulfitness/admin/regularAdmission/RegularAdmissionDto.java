package kr.seoulfitness.admin.regularAdmission;

import java.util.Date;

import lombok.Data;

@Data
public class RegularAdmissionDto {
    // 입시(정시) 모집 정보
    private int regularAdmissionId;           // 입시(정시) 모집 아이디
    private int admissionId;                // 입시 요강 아이디
    private String useCsatReflectedScore;  // 수능 반영 점수 사용 여부
    private double csatReflectedScore;         // 수능 반영 점수(0.000 ~ 1000.000)
    private String usePhysicalReflectedScore;  // 실기 반영 점수 사용 여부
    private double physicalReflectedScore;     // 실기 반영 점수(0.000 ~ 1000.000)
    private String useInternalReflectedScore;  // 내신 반영 점수 사용 여부
    private double internalReflectedScore;     // 내신 반영 점수(0.000 ~ 1000.000)
    private String useInterviewReflectedScore;  // 면접 반영 점수 사용 여부
    private double interviewReflectedScore;    // 면접 반영 점수(0.000 ~ 1000.000)
    private String acceptedCount;              // 입시 모집 인원
    private String regularAdmissionMemo;      // 입시(정시) 모집 메모 
    private Date createdAt;                 // 생성일시
    private Date updatedAt;                 // 수정일시
    private String createdBy;               // 생성자
    private String updatedBy;               // 수정자

    // 학교 정보
    private String schoolName;          // 학교 이름

    // 학과 정보
    private String departmentName;      // 학과 이름

    // 입시(정시) 수능 점수 정보
    private String admissionYear;       // 입시 연도
    
    // 등록한 사람, 수정한 사람 정보
    private String createdUserName;     // 등록한 사람 이름
    private String updatedUserName;     // 수정한 사람 이름
    private String createdUserPhone;    // 등록한 사람 전화번호
    private String updatedUserPhone;    // 수정한 사람 전화번호

    // 입시(정시) 정보 존재 여부
    private boolean exists;
    public boolean isExists() {
        return exists;
    }
    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
