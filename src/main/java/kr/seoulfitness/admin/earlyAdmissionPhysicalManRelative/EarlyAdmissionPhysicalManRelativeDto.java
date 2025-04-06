package kr.seoulfitness.admin.earlyAdmissionPhysicalManRelative;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class EarlyAdmissionPhysicalManRelativeDto {
    // 수시 모집 실기 남자 상대평가 점수 정보
    private int earlyAdmissionPhysicalRelativeId;       // 수시 모집 실기 남자 상대평가 점수 아이디
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

    // 실기 교과목 비율 최소, 최대
    private double grade1RangeMin;         // 1등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade1RangeMax;         // 1등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade2RangeMin;         // 2등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade2RangeMax;         // 2등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade3RangeMin;         // 3등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade3RangeMax;         // 3등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade4RangeMin;         // 4등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade4RangeMax;         // 4등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade5RangeMin;         // 5등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade5RangeMax;         // 5등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade6RangeMin;         // 6등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade6RangeMax;         // 6등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade7RangeMin;         // 7등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade7RangeMax;         // 7등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade8RangeMin;         // 8등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade8RangeMax;         // 8등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade9RangeMin;         // 9등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade9RangeMax;         // 9등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade10RangeMin;         // 10등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade10RangeMax;         // 10등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade11RangeMin;         // 11등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade11RangeMax;         // 11등급 비율 최대 (0.0000 ~ 100.0000)   
    private double grade12RangeMin;         // 12등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade12RangeMax;         // 12등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade13RangeMin;         // 13등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade13RangeMax;         // 13등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade14RangeMin;         // 14등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade14RangeMax;         // 14등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade15RangeMin;         // 15등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade15RangeMax;         // 15등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade16RangeMin;         // 16등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade16RangeMax;         // 16등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade17RangeMin;         // 17등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade17RangeMax;         // 17등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade18RangeMin;         // 18등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade18RangeMax;         // 18등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade19RangeMin;         // 19등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade19RangeMax;         // 19등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade20RangeMin;         // 20등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade20RangeMax;         // 20등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade21RangeMin;         // 21등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade21RangeMax;         // 21등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade22RangeMin;         // 22등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade22RangeMax;         // 22등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade23RangeMin;         // 23등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade23RangeMax;         // 23등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade24RangeMin;         // 24등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade24RangeMax;         // 24등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade25RangeMin;         // 25등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade25RangeMax;         // 25등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade26RangeMin;         // 26등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade26RangeMax;         // 26등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade27RangeMin;         // 27등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade27RangeMax;         // 27등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade28RangeMin;         // 28등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade28RangeMax;         // 28등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade29RangeMin;         // 29등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade29RangeMax;         // 29등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade30RangeMin;         // 30등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade30RangeMax;         // 30등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade31RangeMin;         // 31등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade31RangeMax;         // 31등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade32RangeMin;         // 32등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade32RangeMax;         // 32등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade33RangeMin;         // 33등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade33RangeMax;         // 33등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade34RangeMin;         // 34등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade34RangeMax;         // 34등급 비율 최대 (0.0000 ~ 100.0000)   
    private double grade35RangeMin;         // 35등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade35RangeMax;         // 35등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade36RangeMin;         // 36등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade36RangeMax;         // 36등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade37RangeMin;         // 37등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade37RangeMax;         // 37등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade38RangeMin;         // 38등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade38RangeMax;         // 38등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade39RangeMin;         // 39등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade39RangeMax;         // 39등급 비율 최대 (0.0000 ~ 100.0000)
    private double grade40RangeMin;         // 40등급 비율 최소 (0.0000 ~ 100.0000)
    private double grade40RangeMax;         // 40등급 비율 최대 (0.0000 ~ 100.0000)

    // 메모
    private String earlyAdmissionPhysicalRelativeMemo; // 메모

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
    private List<Double> gradeRangeMins;
    private List<Double> gradeRangeMaxs;
}
