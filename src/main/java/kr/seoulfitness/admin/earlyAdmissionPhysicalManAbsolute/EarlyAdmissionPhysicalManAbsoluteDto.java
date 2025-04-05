package kr.seoulfitness.admin.earlyAdmissionPhysicalManAbsolute;

import java.util.Date;

import lombok.Data;

@Data
public class EarlyAdmissionPhysicalManAbsoluteDto {
    // 수시 모집 실기 절대평가 점수 정보
    private int earlyAdmissionPhysicalAbsoluteId;       // 수시 모집 실기 절대평가 점수 아이디
    private int earlyAdmissionPhysicalSubjectId;        // 수시 모집 실기 교과목 아이디
    private int earlyAdmissionPhysicalId;               // 수시 모집 실기 점수 아이디
    private int admissionId;                            // 입시 요강 아이디

    // 실기 교과목 등급 사용 여부 (1등급 ~ 40등급)
    private String useGrade1;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade2;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade3;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade4;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade5;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade6;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade7;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade8;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade9;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade10;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade11;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade12;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade13;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade14;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade15;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade16;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade17;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade18;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade19;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade20;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade21;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade22;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade23;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade24;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade25;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade26;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade27;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade28;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade29;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade30;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade31;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade32;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade33;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade34;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade35;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade36;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade37;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade38;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade39;         // 'Y' : 사용, 'N' : 미사용
    private String useGrade40;         // 'Y' : 사용, 'N' : 미사용
    
    // 실기 교과목 등급별 점수
    private int grade1Score;         // 1등급 점수 (0.0000 ~ 100.0000)
    private int grade2Score;         // 2등급 점수 (0.0000 ~ 100.0000)
    private int grade3Score;         // 3등급 점수 (0.0000 ~ 100.0000)
    private int grade4Score;         // 4등급 점수 (0.0000 ~ 100.0000)
    private int grade5Score;         // 5등급 점수 (0.0000 ~ 100.0000)
    private int grade6Score;         // 6등급 점수 (0.0000 ~ 100.0000)
    private int grade7Score;         // 7등급 점수 (0.0000 ~ 100.0000)
    private int grade8Score;         // 8등급 점수 (0.0000 ~ 100.0000)
    private int grade9Score;         // 9등급 점수 (0.0000 ~ 100.0000)
    private int grade10Score;         // 10등급 점수 (0.0000 ~ 100.0000)
    private int grade11Score;         // 11등급 점수 (0.0000 ~ 100.0000)
    private int grade12Score;         // 12등급 점수 (0.0000 ~ 100.0000)
    private int grade13Score;         // 13등급 점수 (0.0000 ~ 100.0000)
    private int grade14Score;         // 14등급 점수 (0.0000 ~ 100.0000)
    private int grade15Score;         // 15등급 점수 (0.0000 ~ 100.0000)
    private int grade16Score;         // 16등급 점수 (0.0000 ~ 100.0000)
    private int grade17Score;         // 17등급 점수 (0.0000 ~ 100.0000)
    private int grade18Score;         // 18등급 점수 (0.0000 ~ 100.0000)
    private int grade19Score;         // 19등급 점수 (0.0000 ~ 100.0000)
    private int grade20Score;         // 20등급 점수 (0.0000 ~ 100.0000)
    private int grade21Score;         // 21등급 점수 (0.0000 ~ 100.0000)
    private int grade22Score;         // 22등급 점수 (0.0000 ~ 100.0000)
    private int grade23Score;         // 23등급 점수 (0.0000 ~ 100.0000)
    private int grade24Score;         // 24등급 점수 (0.0000 ~ 100.0000)
    private int grade25Score;         // 25등급 점수 (0.0000 ~ 100.0000)
    private int grade26Score;         // 26등급 점수 (0.0000 ~ 100.0000)
    private int grade27Score;         // 27등급 점수 (0.0000 ~ 100.0000)
    private int grade28Score;         // 28등급 점수 (0.0000 ~ 100.0000)
    private int grade29Score;         // 29등급 점수 (0.0000 ~ 100.0000)
    private int grade30Score;         // 30등급 점수 (0.0000 ~ 100.0000)
    private int grade31Score;         // 31등급 점수 (0.0000 ~ 100.0000)
    private int grade32Score;         // 32등급 점수 (0.0000 ~ 100.0000)
    private int grade33Score;         // 33등급 점수 (0.0000 ~ 100.0000)
    private int grade34Score;         // 34등급 점수 (0.0000 ~ 100.0000)
    private int grade35Score;         // 35등급 점수 (0.0000 ~ 100.0000)
    private int grade36Score;         // 36등급 점수 (0.0000 ~ 100.0000)
    private int grade37Score;         // 37등급 점수 (0.0000 ~ 100.0000)
    private int grade38Score;         // 38등급 점수 (0.0000 ~ 100.0000)
    private int grade39Score;         // 39등급 점수 (0.0000 ~ 100.0000)
    private int grade40Score;         // 40등급 점수 (0.0000 ~ 100.0000)

    // 실기 교과목 기록 최소, 최대
    private int grade1RecordMin;         // 1등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade1RecordMax;         // 1등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade2RecordMin;         // 2등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade2RecordMax;         // 2등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade3RecordMin;         // 3등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade3RecordMax;         // 3등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade4RecordMin;         // 4등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade4RecordMax;         // 4등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade5RecordMin;         // 5등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade5RecordMax;         // 5등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade6RecordMin;         // 6등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade6RecordMax;         // 6등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade7RecordMin;         // 7등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade7RecordMax;         // 7등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade8RecordMin;         // 8등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade8RecordMax;         // 8등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade9RecordMin;         // 9등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade9RecordMax;         // 9등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade10RecordMin;         // 10등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade10RecordMax;         // 10등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade11RecordMin;         // 11등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade11RecordMax;         // 11등급 기록 최대 (0.0000 ~ 100.0000)   
    private int grade12RecordMin;         // 12등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade12RecordMax;         // 12등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade13RecordMin;         // 13등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade13RecordMax;         // 13등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade14RecordMin;         // 14등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade14RecordMax;         // 14등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade15RecordMin;         // 15등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade15RecordMax;         // 15등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade16RecordMin;         // 16등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade16RecordMax;         // 16등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade17RecordMin;         // 17등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade17RecordMax;         // 17등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade18RecordMin;         // 18등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade18RecordMax;         // 18등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade19RecordMin;         // 19등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade19RecordMax;         // 19등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade20RecordMin;         // 20등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade20RecordMax;         // 20등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade21RecordMin;         // 21등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade21RecordMax;         // 21등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade22RecordMin;         // 22등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade22RecordMax;         // 22등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade23RecordMin;         // 23등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade23RecordMax;         // 23등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade24RecordMin;         // 24등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade24RecordMax;         // 24등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade25RecordMin;         // 25등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade25RecordMax;         // 25등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade26RecordMin;         // 26등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade26RecordMax;         // 26등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade27RecordMin;         // 27등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade27RecordMax;         // 27등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade28RecordMin;         // 28등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade28RecordMax;         // 28등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade29RecordMin;         // 29등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade29RecordMax;         // 29등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade30RecordMin;         // 30등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade30RecordMax;         // 30등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade31RecordMin;         // 31등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade31RecordMax;         // 31등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade32RecordMin;         // 32등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade32RecordMax;         // 32등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade33RecordMin;         // 33등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade33RecordMax;         // 33등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade34RecordMin;         // 34등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade34RecordMax;         // 34등급 기록 최대 (0.0000 ~ 100.0000)   
    private int grade35RecordMin;         // 35등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade35RecordMax;         // 35등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade36RecordMin;         // 36등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade36RecordMax;         // 36등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade37RecordMin;         // 37등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade37RecordMax;         // 37등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade38RecordMin;         // 38등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade38RecordMax;         // 38등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade39RecordMin;         // 39등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade39RecordMax;         // 39등급 기록 최대 (0.0000 ~ 100.0000)
    private int grade40RecordMin;         // 40등급 기록 최소 (0.0000 ~ 100.0000)
    private int grade40RecordMax;         // 40등급 기록 최대 (0.0000 ~ 100.0000)

    // 메모
    private String earlyAdmissionPhysicalAbsoluteMemo; // 메모

    // 등록일시, 수정일시, 등록자, 수정자
    private Date createdAt;                 // 생성일시
    private Date updatedAt;                 // 수정일시
    private String createdBy;               // 생성자
    private String updatedBy;               // 수정자
    
    // 등록한 사람, 수정한 사람 정보
    private String createdUserName;     // 등록한 사람 이름
    private String updatedUserName;     // 수정한 사람 이름
    private String createdUserPhone;    // 등록한 사람 전화번호
    private String updatedUserPhone;    // 수정한 사람 전화번호
}
