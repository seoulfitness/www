package kr.seoulfitness.admin.physicalRecordPeriod;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PhysicalRecordPeriodDto {
    private int physicalRecordPeriodId;   // 실기 기록 등록 기간 아이디

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;             // 기록 등록 시작일

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;               // 기록 등록 종료일
    private String title;                 // 기록 등록 제목
    private String memo;                  // 기록 등록 메모
    private String grantBranchUser;       // 지점 회원의 기록 등록 권한 ('Y' : 있음, 'N' : 없음)
    private String grantNormalUser;       // 일반 회원의 기록 등록 권한 ('Y' : 있음, 'N' : 없음)

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
