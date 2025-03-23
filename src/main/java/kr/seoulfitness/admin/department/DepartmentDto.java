package kr.seoulfitness.admin.department;

import java.util.Date;

import lombok.Data;

@Data
public class DepartmentDto {
    private int departmentId;               // 학과 아이디
    private String departmentName;          // 학과 이름
    private String departmentAddress;       // 학과 주소
    private String departmentPhone;         // 학과 전화번호
    private String departmentUrl;           // 학과 웹사이트
    private String departmentMemo;          // 학과 메모
    private Date createdAt;             // 생성일시
    private Date updatedAt;             // 수정일시
    private String createdBy;           // 생성자
    private String updatedBy;           // 수정한 사람

    // 등록한 사람, 수정한 사람 정보
    private String createdUserName;     // 등록한 사람 이름
    private String updatedUserName;     // 수정한 사람 이름
    private String createdUserPhone;    // 등록한 사람 전화번호
    private String updatedUserPhone;    // 수정한 사람 전화번호
}
