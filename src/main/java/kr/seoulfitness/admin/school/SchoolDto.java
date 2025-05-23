package kr.seoulfitness.admin.school;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class SchoolDto {
    private int schoolId;               // 학교 아이디
    private String schoolName;          // 학교 이름
    private String schoolAddress;       // 학교 주소
    private String schoolPhone;         // 학교 전화번호
    private String schoolUrl;           // 학교 웹사이트
    private String admissionInfoUrl;    // 학교 입학안내
    private String schoolLogo;          // 학교 로고 선택 (none, url, file)
    private String schoolLogoUrl;       // 학교 로고 이미지 경로
    private MultipartFile schoolLogoFile; // 학교 로고 이미지 파일
    private String schoolLogoFileName;  // 학교 로고 이미지 파일명
    private String schoolLogoOriginalFileName; // 학교 로고 이미지 원본 파일명
    private String schoolMemo;          // 학교 메모
    private String deleteFile;          // 파일 삭제
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
