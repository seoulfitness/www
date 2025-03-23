package kr.seoulfitness.admin.highSchool;

import java.util.Date;

import lombok.Data;

@Data
public class HighSchoolDto {
    // 고등학교 정보
    private int highSchoolId;         // 고등학교 아이디
    private String highSchoolName;    // 고등학교 이름
    private String highSchoolAddress; // 고등학교 주소
    private String highSchoolPhone;   // 고등학교 전화번호
    private String highSchoolWebsite; // 고등학교 웹사이트
    private String highSchoolMemo;    // 고등학교 메모
    private Date createdAt;         // 생성일시
    private Date updatedAt;         // 수정일시
    private String createdBy;         // 생성한 사람
    private String updatedBy;         // 수정한 사람

    // 시/도 정보
    private int provinceId;           // 시/도 아이디
    private String provinceName;      // 시/도 이름
    
    // 구/군 정보
    private int districtId;           // 구/군 아이디
    private String districtName;      // 구/군 이름

    // 등록한 사람, 수정한 사람 정보
    private String createdUserName;   // 등록한 사람 이름
    private String updatedUserName;   // 수정한 사람 이름
    private String createdUserPhone;  // 등록한 사람 전화번호
    private String updatedUserPhone;  // 수정한 사람 전화번호
}