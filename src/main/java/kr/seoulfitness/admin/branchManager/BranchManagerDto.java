package kr.seoulfitness.admin.branchManager;

import java.util.Date;

import lombok.Data;

@Data
public class BranchManagerDto {
    // 지점 관리자 정보
    private int branchManagerId;    // 구분자
    private int branchId;           // 지점 아이디
    private String userId;          // 사용자 아이디
    private Date createdAt;       // 생성일시
    private Date updatedAt;       // 수정일시
    private String createdBy;       // 생성자
    private String updatedBy;       // 수정한 사람

    // 지점 정보
    private String branchName;      // 지점 이름
    private String branchAddress;   // 지점 주소
    private String branchPhone;     // 지점 전화번호

    // 사용자 정보
    private String userName;        // 사용자 이름
    private String userEmail;       // 사용자 이메일
    private String userPhone;       // 사용자 전화번호
    private String profileImage;    // 사용자 프로필 이미지
    
    // 등록한 사람, 수정한 사람 정보
    private String createdUserName; // 등록한 사람 이름
    private String updatedUserName; // 수정한 사람 이름
    private String createdUserPhone; // 등록한 사람 전화번호
    private String updatedUserPhone; // 수정한 사람 전화번호
}
