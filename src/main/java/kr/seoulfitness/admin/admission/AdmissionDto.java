package kr.seoulfitness.admin.admission;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import kr.seoulfitness.admin.earlyAdmission.EarlyAdmissionDto;
import kr.seoulfitness.admin.earlyAdmissionCsat.EarlyAdmissionCsatDto;
import kr.seoulfitness.admin.earlyAdmissionEnglish.EarlyAdmissionEnglishDto;
import kr.seoulfitness.admin.earlyAdmissionHistory.EarlyAdmissionHistoryDto;
import kr.seoulfitness.admin.earlyAdmissionPhysical.EarlyAdmissionPhysicalDto;
import kr.seoulfitness.admin.regularAdmission.RegularAdmissionDto;
import kr.seoulfitness.admin.regularAdmissionCsat.RegularAdmissionCsatDto;
import kr.seoulfitness.admin.regularAdmissionEnglish.RegularAdmissionEnglishDto;
import kr.seoulfitness.admin.regularAdmissionHistory.RegularAdmissionHistoryDto;
import kr.seoulfitness.admin.regularAdmissionPhysical.RegularAdmissionPhysicalDto;
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
    private String schoolAddress;       // 학교 주소
    private String schoolPhone;         // 학교 전화번호
    private String schoolUrl;           // 학교 웹사이트
    private String admissionInfoUrl;    // 학교 입학안내
    private String schoolLogo;          // 학교 로고 선택 (none, url, file)
    private String schoolLogoUrl;       // 학교 로고 이미지 경로
    private MultipartFile schoolLogoFile; // 학교 로고 이미지 파일
    private String schoolLogoFileName;  // 학교 로고 이미지 파일명
    private String schoolLogoOriginalFileName; // 학교 로고 이미지 원본 파일명

    // 학과 정보
    private String departmentName;      // 학과 이름

    // 등록한 사람, 수정한 사람 정보
    private String createdUserName;     // 등록한 사람 이름
    private String updatedUserName;     // 수정한 사람 이름
    private String createdUserPhone;    // 등록한 사람 전화번호
    private String updatedUserPhone;    // 수정한 사람 전화번호

    // 수시 모집 정보
    private EarlyAdmissionDto earlyAdmissionDto;
    private EarlyAdmissionCsatDto earlyAdmissionCsatDto;
    private EarlyAdmissionEnglishDto earlyAdmissionEnglishDto;
    private EarlyAdmissionHistoryDto earlyAdmissionHistoryDto;
    private EarlyAdmissionPhysicalDto earlyAdmissionPhysicalDto;

    // 정시 모집 정보
    private RegularAdmissionDto regularAdmissionDto;
    private RegularAdmissionCsatDto regularAdmissionCsatDto;
    private RegularAdmissionEnglishDto regularAdmissionEnglishDto;
    private RegularAdmissionHistoryDto regularAdmissionHistoryDto;
    private RegularAdmissionPhysicalDto regularAdmissionPhysicalDto;
}
