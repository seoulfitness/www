package kr.seoulfitness.admin.earlyAdmissionPhysicalManAbsolute;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class EarlyAdmissionPhysicalManAbsoluteDto {
    // 수시 모집 실기 남자 절대평가 점수 정보
    private int earlyAdmissionPhysicalAbsoluteId;       // 수시 모집 실기 남자 절대평가 점수 아이디
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
    private double grade1Score;         // 1등급 점수 (0.0000 ~ 100.0000)
    private double grade2Score;         // 2등급 점수 (0.0000 ~ 100.0000)
    private double grade3Score;         // 3등급 점수 (0.0000 ~ 100.0000)
    private double grade4Score;         // 4등급 점수 (0.0000 ~ 100.0000)
    private double grade5Score;         // 5등급 점수 (0.0000 ~ 100.0000)
    private double grade6Score;         // 6등급 점수 (0.0000 ~ 100.0000)
    private double grade7Score;         // 7등급 점수 (0.0000 ~ 100.0000)
    private double grade8Score;         // 8등급 점수 (0.0000 ~ 100.0000)
    private double grade9Score;         // 9등급 점수 (0.0000 ~ 100.0000)
    private double grade10Score;         // 10등급 점수 (0.0000 ~ 100.0000)
    private double grade11Score;         // 11등급 점수 (0.0000 ~ 100.0000)
    private double grade12Score;         // 12등급 점수 (0.0000 ~ 100.0000)
    private double grade13Score;         // 13등급 점수 (0.0000 ~ 100.0000)
    private double grade14Score;         // 14등급 점수 (0.0000 ~ 100.0000)
    private double grade15Score;         // 15등급 점수 (0.0000 ~ 100.0000)
    private double grade16Score;         // 16등급 점수 (0.0000 ~ 100.0000)
    private double grade17Score;         // 17등급 점수 (0.0000 ~ 100.0000)
    private double grade18Score;         // 18등급 점수 (0.0000 ~ 100.0000)
    private double grade19Score;         // 19등급 점수 (0.0000 ~ 100.0000)
    private double grade20Score;         // 20등급 점수 (0.0000 ~ 100.0000)
    private double grade21Score;         // 21등급 점수 (0.0000 ~ 100.0000)
    private double grade22Score;         // 22등급 점수 (0.0000 ~ 100.0000)
    private double grade23Score;         // 23등급 점수 (0.0000 ~ 100.0000)
    private double grade24Score;         // 24등급 점수 (0.0000 ~ 100.0000)
    private double grade25Score;         // 25등급 점수 (0.0000 ~ 100.0000)
    private double grade26Score;         // 26등급 점수 (0.0000 ~ 100.0000)
    private double grade27Score;         // 27등급 점수 (0.0000 ~ 100.0000)
    private double grade28Score;         // 28등급 점수 (0.0000 ~ 100.0000)
    private double grade29Score;         // 29등급 점수 (0.0000 ~ 100.0000)
    private double grade30Score;         // 30등급 점수 (0.0000 ~ 100.0000)
    private double grade31Score;         // 31등급 점수 (0.0000 ~ 100.0000)
    private double grade32Score;         // 32등급 점수 (0.0000 ~ 100.0000)
    private double grade33Score;         // 33등급 점수 (0.0000 ~ 100.0000)
    private double grade34Score;         // 34등급 점수 (0.0000 ~ 100.0000)
    private double grade35Score;         // 35등급 점수 (0.0000 ~ 100.0000)
    private double grade36Score;         // 36등급 점수 (0.0000 ~ 100.0000)
    private double grade37Score;         // 37등급 점수 (0.0000 ~ 100.0000)
    private double grade38Score;         // 38등급 점수 (0.0000 ~ 100.0000)
    private double grade39Score;         // 39등급 점수 (0.0000 ~ 100.0000)
    private double grade40Score;         // 40등급 점수 (0.0000 ~ 100.0000)

    // 실기 교과목 기록 최소, 최대
    private double grade1RecordMin;         // 1등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade1RecordMax;         // 1등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade2RecordMin;         // 2등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade2RecordMax;         // 2등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade3RecordMin;         // 3등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade3RecordMax;         // 3등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade4RecordMin;         // 4등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade4RecordMax;         // 4등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade5RecordMin;         // 5등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade5RecordMax;         // 5등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade6RecordMin;         // 6등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade6RecordMax;         // 6등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade7RecordMin;         // 7등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade7RecordMax;         // 7등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade8RecordMin;         // 8등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade8RecordMax;         // 8등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade9RecordMin;         // 9등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade9RecordMax;         // 9등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade10RecordMin;         // 10등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade10RecordMax;         // 10등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade11RecordMin;         // 11등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade11RecordMax;         // 11등급 기록 최대 (0.0000 ~ 100.0000)   
    private double grade12RecordMin;         // 12등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade12RecordMax;         // 12등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade13RecordMin;         // 13등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade13RecordMax;         // 13등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade14RecordMin;         // 14등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade14RecordMax;         // 14등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade15RecordMin;         // 15등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade15RecordMax;         // 15등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade16RecordMin;         // 16등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade16RecordMax;         // 16등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade17RecordMin;         // 17등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade17RecordMax;         // 17등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade18RecordMin;         // 18등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade18RecordMax;         // 18등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade19RecordMin;         // 19등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade19RecordMax;         // 19등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade20RecordMin;         // 20등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade20RecordMax;         // 20등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade21RecordMin;         // 21등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade21RecordMax;         // 21등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade22RecordMin;         // 22등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade22RecordMax;         // 22등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade23RecordMin;         // 23등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade23RecordMax;         // 23등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade24RecordMin;         // 24등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade24RecordMax;         // 24등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade25RecordMin;         // 25등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade25RecordMax;         // 25등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade26RecordMin;         // 26등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade26RecordMax;         // 26등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade27RecordMin;         // 27등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade27RecordMax;         // 27등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade28RecordMin;         // 28등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade28RecordMax;         // 28등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade29RecordMin;         // 29등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade29RecordMax;         // 29등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade30RecordMin;         // 30등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade30RecordMax;         // 30등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade31RecordMin;         // 31등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade31RecordMax;         // 31등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade32RecordMin;         // 32등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade32RecordMax;         // 32등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade33RecordMin;         // 33등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade33RecordMax;         // 33등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade34RecordMin;         // 34등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade34RecordMax;         // 34등급 기록 최대 (0.0000 ~ 100.0000)   
    private double grade35RecordMin;         // 35등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade35RecordMax;         // 35등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade36RecordMin;         // 36등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade36RecordMax;         // 36등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade37RecordMin;         // 37등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade37RecordMax;         // 37등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade38RecordMin;         // 38등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade38RecordMax;         // 38등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade39RecordMin;         // 39등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade39RecordMax;         // 39등급 기록 최대 (0.0000 ~ 100.0000)
    private double grade40RecordMin;         // 40등급 기록 최소 (0.0000 ~ 100.0000)
    private double grade40RecordMax;         // 40등급 기록 최대 (0.0000 ~ 100.0000)

    // 메모
    private String earlyAdmissionPhysicalManAbsoluteMemo; // 메모

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

    private List<String> useGrades;
    private List<Double> gradeScores;
    private List<Double> gradeRecordMins;
    private List<Double> gradeRecordMaxs;
}
