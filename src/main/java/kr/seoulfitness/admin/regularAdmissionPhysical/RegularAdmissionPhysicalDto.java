package kr.seoulfitness.admin.regularAdmissionPhysical;

import java.util.Date;

import lombok.Data;

@Data
public class RegularAdmissionPhysicalDto {
    // 입시(정시) 모집 실기 점수 정보
    private int regularAdmissionPhysicalId;       // 입시(정시) 모집 실기 점수 아이디
    private int admissionId;                    // 입시 요강 아이디
    private String useType;                     // 사용 구분('Y' : 사용, 'N' : 미사용)

    // 실기 교과목 반영 점수
    private int useSubject1ReflectedScore;         // 100m 달리기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject2ReflectedScore;         // 10m 왕복달리기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject3ReflectedScore;         // 20m 왕복달리기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject4ReflectedScore;         // 20m 왕복오래달리기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject5ReflectedScore;         // 20m 중량달리기(동국대-체교) ('Y' : 사용, 'N' : 미사용)
    private int useSubject6ReflectedScore;         // 25m 왕복달리기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject7ReflectedScore;         // 50m 달리기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject8ReflectedScore;         // 800m 달리기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject9ReflectedScore;         // 80m 달리기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject10ReflectedScore;         // Z런 ('Y' : 사용, 'N' : 미사용)
    private int useSubject11ReflectedScore;         // 농구 골밑슛 ('Y' : 사용, 'N' : 미사용)
    private int useSubject12ReflectedScore;         // 농구 레이업슛 ('Y' : 사용, 'N' : 미사용)
    private int useSubject13ReflectedScore;         // 농구(건국대 글로컬) ('Y' : 사용, 'N' : 미사용)
    private int useSubject14ReflectedScore;         // 농구(건국대 체교) ('Y' : 사용, 'N' : 미사용)
    private int useSubject15ReflectedScore;         // 농구(명지대) ('Y' : 사용, 'N' : 미사용)
    private int useSubject16ReflectedScore;         // 농구(인천대 체교) ('Y' : 사용, 'N' : 미사용)
    private int useSubject17ReflectedScore;         // 농구공 던지기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject18ReflectedScore;         // 높이뛰기(배면뛰기) ('Y' : 사용, 'N' : 미사용)
    private int useSubject19ReflectedScore;         // 메디신볼 던지기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject20ReflectedScore;         // 물구나무서 앞구르기(앞구르기자세) ('Y' : 사용, 'N' : 미사용)
    private int useSubject21ReflectedScore;         // 물구나무서서 앞구르기(물구나무자세) ('Y' : 사용, 'N' : 미사용)
    private int useSubject22ReflectedScore;         // 배구(관동대 체교) ('Y' : 사용, 'N' : 미사용)
    private int useSubject23ReflectedScore;         // 배구(교원대) ('Y' : 사용, 'N' : 미사용)
    private int useSubject24ReflectedScore;         // 배구(단국대 체교) ('Y' : 사용, 'N' : 미사용)
    private int useSubject25ReflectedScore;         // 배구(원광대 체교) ('Y' : 사용, 'N' : 미사용)
    private int useSubject26ReflectedScore;         // 배구(한체대-특체) ('Y' : 사용, 'N' : 미사용)
    private int useSubject27ReflectedScore;         // 배근력 ('Y' : 사용, 'N' : 미사용)
    private int useSubject28ReflectedScore;         // 사이드스텝 ('Y' : 사용, 'N' : 미사용)
    private int useSubject29ReflectedScore;         // 손짚고 앞돌기 핸드스프링(도입자세) ('Y' : 사용, 'N' : 미사용)
    private int useSubject30ReflectedScore;         // 손짚고 앞돌기 핸드스프링(중간자세) ('Y' : 사용, 'N' : 미사용)
    private int useSubject31ReflectedScore;         // 손짚고 앞돌기 핸드스프링(착지자세) ('Y' : 사용, 'N' : 미사용)
    private int useSubject32ReflectedScore;         // 손짚고 옆돌기(도입자세) ('Y' : 사용, 'N' : 미사용)
    private int useSubject33ReflectedScore;         // 손짚고 옆돌기(중간자세) ('Y' : 사용, 'N' : 미사용)
    private int useSubject34ReflectedScore;         // 손짚고 옆돌기(착지자세)('Y' : 사용, 'N' : 미사용)
    private int useSubject35ReflectedScore;         // 수직 점프 ('Y' : 사용, 'N' : 미사용)
    private int useSubject36ReflectedScore;         // 십자달리기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject37ReflectedScore;         // 악력 ('Y' : 사용, 'N' : 미사용)
    private int useSubject38ReflectedScore;         // 앉아메디신볼던지기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject39ReflectedScore;         // 윗몸일으키기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject40ReflectedScore;         // 전공 ('Y' : 사용, 'N' : 미사용)
    private int useSubject41ReflectedScore;         // 전공(검도) ('Y' : 사용, 'N' : 미사용)
    private int useSubject42ReflectedScore;         // 전공(골프) ('Y' : 사용, 'N' : 미사용)
    private int useSubject43ReflectedScore;         // 전공(농구) ('Y' : 사용, 'N' : 미사용)
    private int useSubject44ReflectedScore;         // 전공(배구) ('Y' : 사용, 'N' : 미사용)
    private int useSubject45ReflectedScore;         // 전공(배드민턴) ('Y' : 사용, 'N' : 미사용)
    private int useSubject46ReflectedScore;         // 전공(수영) ('Y' : 사용, 'N' : 미사용)
    private int useSubject47ReflectedScore;         // 전공(유도) ('Y' : 사용, 'N' : 미사용)
    private int useSubject48ReflectedScore;         // 전공(체조) ('Y' : 사용, 'N' : 미사용)
    private int useSubject49ReflectedScore;         // 전공(축구) ('Y' : 사용, 'N' : 미사용)    
    private int useSubject50ReflectedScore;         // 전공(태권도) ('Y' : 사용, 'N' : 미사용)
    private int useSubject51ReflectedScore;         // 전공(핸드볼) ('Y' : 사용, 'N' : 미사용)
    private int useSubject52ReflectedScore;         // 제자리 멀리뛰기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject53ReflectedScore;         // 제자리 세단뛰기(공주대-생체) ('Y' : 사용, 'N' : 미사용)
    private int useSubject54ReflectedScore;         // 좌전굴 ('Y' : 사용, 'N' : 미사용)
    private int useSubject55ReflectedScore;         // 지그재그 달리기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject56ReflectedScore;         // 체전굴 ('Y' : 사용, 'N' : 미사용)
    private int useSubject57ReflectedScore;         // 체조(전남대) ('Y' : 사용, 'N' : 미사용)
    private int useSubject58ReflectedScore;         // 체조(전북대) ('Y' : 사용, 'N' : 미사용)
    private int useSubject59ReflectedScore;         // 체조(충남대 체교) ('Y' : 사용, 'N' : 미사용)
    private int useSubject60ReflectedScore;         // 축구 장애물 드리블 ('Y' : 사용, 'N' : 미사용)
    private int useSubject61ReflectedScore;         // 태권도 ('Y' : 사용, 'N' : 미사용)
    private int useSubject62ReflectedScore;         // 턱걸이 ('Y' : 사용, 'N' : 미사용)
    private int useSubject63ReflectedScore;         // 팔굽혀 매달리기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject64ReflectedScore;         // 한양대(순환계측) ('Y' : 사용, 'N' : 미사용)
    private int useSubject65ReflectedScore;         // 핸드볼 벽치기 ('Y' : 사용, 'N' : 미사용)    
    private int useSubject66ReflectedScore;         // 핸드볼공 던지기 ('Y' : 사용, 'N' : 미사용)
    private int useSubject67ReflectedScore;         // 핸드스프링 ('Y' : 사용, 'N' : 미사용)
    private int useSubject68ReflectedScore;         // 허들 ('Y' : 사용, 'N' : 미사용)

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
