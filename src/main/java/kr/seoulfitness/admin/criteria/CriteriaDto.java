package kr.seoulfitness.admin.criteria;

import java.util.Date;

import lombok.Data;

@Data
public class CriteriaDto {
    private int criteriaId;               // 입시 요강 아이디
    private int year;          // 입시 요강 연도
    private String criteriaGubun;          // 입시 요강 구분
    private int schoolId;       // 입시 요강 학교 아이디
    private int departmentId;       // 입시 요강 학과 아이디
    private int acceptedCount;          // 입시 요강 합격자 수
    private double csatReflectedScore;         // 수능 교과목 반영 점수
    private double physicalReflectedScore;         // 실기 교과목 반영 점수
    private double internalReflectedScore;         // 내신 교과목 반영 점수
    private double interviewReflectedScore;         // 면접 교과목 반영 점수
    private double totalReflectedScore;         // 총 반영 점수
    private String criteriaMemo;          // 입시 요강 메모
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
