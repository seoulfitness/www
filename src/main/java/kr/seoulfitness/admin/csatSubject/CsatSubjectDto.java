package kr.seoulfitness.admin.csatSubject;

import java.util.Date;

import lombok.Data;

@Data
public class CsatSubjectDto {
    private int csatSubjectId;               // 수능 교과목 아이디
    private String csatSubjectName;          // 수능 교과목 이름    
    private String csatSubjectType;          // 수능 교과목 타입(점수, 등급)
    private String csatSubjectMemo;          // 수능 교과목 메모
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
