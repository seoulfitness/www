package kr.seoulfitness.admin.regularAdmissionPhysical;

import java.util.Date;

import lombok.Data;

@Data
public class RegularAdmissionPhysicalDto {
    // 정시 모집 실기 점수 정보
    private int regularAdmissionPhysicalId;       // 정시 모집 실기 점수 아이디
    private int admissionId;                    // 입시 요강 아이디
    private String useType;                     // 사용 구분('Y' : 사용, 'N' : 미사용)

    // 실기 교과목 사용 여부
    private String useSubject1;         // 'Y' : 사용, 'N' : 미사용
    private String useSubject2;         // 'Y' : 사용, 'N' : 미사용
    private String useSubject3;         // 'Y' : 사용, 'N' : 미사용
    private String useSubject4;         // 'Y' : 사용, 'N' : 미사용
    private String useSubject5;         // 'Y' : 사용, 'N' : 미사용
    private String useSubject6;         // 'Y' : 사용, 'N' : 미사용
    private String useSubject7;         // 'Y' : 사용, 'N' : 미사용
    private String useSubject8;         // 'Y' : 사용, 'N' : 미사용
    private String useSubject9;         // 'Y' : 사용, 'N' : 미사용
    private String useSubject10;         // 'Y' : 사용, 'N' : 미사용

    // 실기 교과목 ID
    private int subject1Id;         // 실기 교과목 1
    private int subject2Id;         // 실기 교과목 2
    private int subject3Id;         // 실기 교과목 3
    private int subject4Id;         // 실기 교과목 4
    private int subject5Id;         // 실기 교과목 5
    private int subject6Id;         // 실기 교과목 6
    private int subject7Id;         // 실기 교과목 7
    private int subject8Id;         // 실기 교과목 8
    private int subject9Id;         // 실기 교과목 9
    private int subject10Id;         // 실기 교과목 10

    // 실기 교과목 평가 방법
    private String subject1EvaluationMethod;         // '1' : 절대평가, '2' : 상대평가
    private String subject2EvaluationMethod;         // '1' : 절대평가, '2' : 상대평가
    private String subject3EvaluationMethod;         // '1' : 절대평가, '2' : 상대평가
    private String subject4EvaluationMethod;         // '1' : 절대평가, '2' : 상대평가
    private String subject5EvaluationMethod;         // '1' : 절대평가, '2' : 상대평가
    private String subject6EvaluationMethod;         // '1' : 절대평가, '2' : 상대평가
    private String subject7EvaluationMethod;         // '1' : 절대평가, '2' : 상대평가
    private String subject8EvaluationMethod;         // '1' : 절대평가, '2' : 상대평가
    private String subject9EvaluationMethod;         // '1' : 절대평가, '2' : 상대평가
    private String subject10EvaluationMethod;         // '1' : 절대평가, '2' : 상대평가

    private Date createdAt;                 // 생성일시
    private Date updatedAt;                 // 수정일시
    private String createdBy;               // 생성자
    private String updatedBy;               // 수정자

    // 학교 정보
    private String schoolName;          // 학교 이름

    // 학과 정보
    private String departmentName;      // 학과 이름

    // 수시 모집 수능 점수 정보
    private String admissionYear;       // 입시 연도
    private String admissionType;       // 입시 구분
    private String earlyAdmission;      // 수시 입시 여부
    private String regularAdmission;    // 정시 입시 여부
    
    // 등록한 사람, 수정한 사람 정보
    private String createdUserName;     // 등록한 사람 이름
    private String updatedUserName;     // 수정한 사람 이름
    private String createdUserPhone;    // 등록한 사람 전화번호
    private String updatedUserPhone;    // 수정한 사람 전화번호
}
