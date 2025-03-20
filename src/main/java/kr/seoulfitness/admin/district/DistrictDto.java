package kr.seoulfitness.admin.district;

import java.util.Date;

import lombok.Data;

@Data
public class DistrictDto {
    // 구/군 정보
    private int districtId;           // 구/군 아이디
    private int provinceId;           // 시/도 아이디
    private String districtName;      // 구/군 이름
    private String districtMemo;      // 구/군 메모
    private Date createdAt;         // 생성일시
    private Date updatedAt;         // 수정일시
    private String createdBy;         // 생성한 사람
    private String updatedBy;         // 수정한 사람

    // 등록한 사람, 수정한 사람 정보
    private String createdUserName;   // 등록한 사람 이름
    private String updatedUserName;   // 수정한 사람 이름
    private String createdUserPhone;  // 등록한 사람 전화번호
    private String updatedUserPhone;  // 수정한 사람 전화번호

    // 시/도 정보
    private String provinceName;      // 시/도 이름
}