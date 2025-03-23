package kr.seoulfitness.admin.admission;

import java.util.Date;

import lombok.Data;

@Data
public class AdmissionDto {
    // 입시 요강 정보
    private int admissionId;            // 입시 요강 아이디
    private int admissionYear;          // 입시 요강 연도
    private String admissionType;       // 입시 요강 구분
    private String earlyAdmission;      // 수시 입시 여부
    private String regularAdmission;    // 정시 입시 여부
    private int schoolId;               // 입시 요강 학교 아이디
    private int departmentId;           // 입시 요강 학과 아이디
    private String admissionMemo;       // 입시 요강 메모
    private Date createdAt;             // 생성일시
    private Date updatedAt;             // 수정일시
    private String createdBy;           // 생성자
    private String updatedBy;           // 수정한 사람

    // 학교 정보
    private String schoolName;          // 학교 이름

    // 학과 정보
    private String departmentName;      // 학과 이름

    // 등록한 사람, 수정한 사람 정보
    private String createdUserName;     // 등록한 사람 이름
    private String updatedUserName;     // 수정한 사람 이름
    private String createdUserPhone;    // 등록한 사람 전화번호
    private String updatedUserPhone;    // 수정한 사람 전화번호
}
