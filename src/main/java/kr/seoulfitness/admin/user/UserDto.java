package kr.seoulfitness.admin.user;

import java.util.Date;

import lombok.Data;

@Data
public class UserDto {
    private String userId;              // 아이디   
    private String password;            // 비밀번호
    private String userName;            // 이름
    private String userEmail;           // 이메일
    private String userPhone;           // 전화번호
    private String profileImage;        // 프로필 이미지
    private String role;                // 역할 ('admin', 'branchManager', 'branchUser', 'normalUser')
    private String status;              // 상태
    private String deleteRequest;       // 삭제 요청
    private String deleteRequestedAt;   // 삭제 요청일시
    private Date createdAt;           // 생성일시
    private Date updatedAt;           // 수정일시
    private String createdBy;           // 생성자
    private String updatedBy;           // 수정한 사람

    // 학교 정보
    private int provinceId;          // 시/도 아이디
    private String provinceName;        // 시/도 이름
    private int districtId;          // 구/군 아이디
    private String districtName;        // 구/군 이름
    private int highSchoolId;        // 고등학교 아이디
    private String highSchoolName;      // 고등학교 이름
    private int grade;               // 학년
    private int studentParent;       // 학생/학부모 구분

    // 지점 정보
    private String branchId;            // 지점 아이디
    private String branchName;          // 지점 이름
    private String branchAddress;       // 지점 주소
    private String branchPhone;         // 지점 전화번호

    // 등록한 사람, 수정한 사람 정보
    private String createdUserName; // 등록한 사람 이름
    private String updatedUserName; // 수정한 사람 이름
    private String createdUserPhone; // 등록한 사람 전화번호
    private String updatedUserPhone; // 수정한 사람 전화번호
}
