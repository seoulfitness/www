package kr.seoulfitness.admin.earlyAdmissionPhysicalWomanRelative;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class EarlyAdmissionPhysicalWomanRelativeDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(EarlyAdmissionPhysicalWomanRelativeDao.class);

    // 수시 모집 실기 여자 상대평가 점수 정보 등록
    public EarlyAdmissionPhysicalWomanRelativeDto insertEarlyAdmissionPhysicalWomanRelative(EarlyAdmissionPhysicalWomanRelativeDto earlyAdmissionPhysicalWomanRelativeDto) {
        try {
            sqlSession.insert("earlyAdmissionPhysicalWomanRelativeMapper.insertEarlyAdmissionPhysicalWomanRelative", earlyAdmissionPhysicalWomanRelativeDto);
            return earlyAdmissionPhysicalWomanRelativeDto;
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 여자 상대평가 점수 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 수시 모집 실기 여자 상대평가 점수 목록
    public List<EarlyAdmissionPhysicalWomanRelativeDto> getEarlyAdmissionPhysicalWomanRelativeList(Map<String, Object> params) {
        List<EarlyAdmissionPhysicalWomanRelativeDto> earlyAdmissionPhysicalWomanRelativeList = null;

        try {
            earlyAdmissionPhysicalWomanRelativeList = sqlSession.selectList("earlyAdmissionPhysicalWomanRelativeMapper.getEarlyAdmissionPhysicalWomanRelativeList", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 여자 상대평가 점수 목록 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionPhysicalWomanRelativeList;
    }

    // 수시 모집 실기 여자 상대평가 점수 정보 상세
    public EarlyAdmissionPhysicalWomanRelativeDto getEarlyAdmissionPhysicalWomanRelative(Map<String, Object> params) {
        EarlyAdmissionPhysicalWomanRelativeDto earlyAdmissionPhysicalWomanRelative = null;
        
        try {
            earlyAdmissionPhysicalWomanRelative = sqlSession.selectOne("earlyAdmissionPhysicalWomanRelativeMapper.getEarlyAdmissionPhysicalWomanRelative", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 여자 상대평가 점수 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionPhysicalWomanRelative;
    }

    // 수시 모집 실기 여자 상대평가 점수 정보 수정
    public int updateEarlyAdmissionPhysicalWomanRelative(EarlyAdmissionPhysicalWomanRelativeDto earlyAdmissionPhysicalWomanRelativeDto) {
        int result = -1;

        try {
            List<String> useGrades = new ArrayList<>();
            List<Double> gradeScores = new ArrayList<>();
            List<Double> gradeRangeMins = new ArrayList<>();
            List<Double> gradeRangeMaxs = new ArrayList<>();

            for (int i = 1; i <= 40; i++) {
                try {
                    // 등급 사용 여부 처리
                    String methodName = "getUseGrade" + i;
                    Method method = earlyAdmissionPhysicalWomanRelativeDto.getClass().getMethod(methodName);
                    useGrades.add((String) method.invoke(earlyAdmissionPhysicalWomanRelativeDto));

                    // 점수 처리
                    methodName = "getGrade" + i + "Score";
                    method = earlyAdmissionPhysicalWomanRelativeDto.getClass().getMethod(methodName);
                    gradeScores.add((Double) method.invoke(earlyAdmissionPhysicalWomanRelativeDto));
                    
                    // 기록 최소값 처리
                    methodName = "getGrade" + i + "RangeMin";
                    method = earlyAdmissionPhysicalWomanRelativeDto.getClass().getMethod(methodName);
                    gradeRangeMins.add((Double) method.invoke(earlyAdmissionPhysicalWomanRelativeDto));

                    // 기록 최대값 처리
                    methodName = "getGrade" + i + "RangeMax";
                    method = earlyAdmissionPhysicalWomanRelativeDto.getClass().getMethod(methodName);
                    gradeRangeMaxs.add((Double) method.invoke(earlyAdmissionPhysicalWomanRelativeDto));
                } catch (Exception e) {
                    logger.error("수시 모집 실기 여자 상대평가 점수 정보 수정 파라미터 처리 중 오류 발생: {}", e.getMessage(), e);
                }
            }

            earlyAdmissionPhysicalWomanRelativeDto.setUseGrades(useGrades);
            earlyAdmissionPhysicalWomanRelativeDto.setGradeScores(gradeScores);
            earlyAdmissionPhysicalWomanRelativeDto.setGradeRangeMins(gradeRangeMins);
            earlyAdmissionPhysicalWomanRelativeDto.setGradeRangeMaxs(gradeRangeMaxs);

            result = sqlSession.update("earlyAdmissionPhysicalWomanRelativeMapper.updateEarlyAdmissionPhysicalWomanRelative", earlyAdmissionPhysicalWomanRelativeDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 여자 상대평가 점수 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 수시 모집 실기 여자 상대평가 점수 정보 삭제
    public int deleteEarlyAdmissionPhysicalWomanRelative(EarlyAdmissionPhysicalWomanRelativeDto earlyAdmissionPhysicalWomanRelativeDto) {
        int result = -1;

        try {
            result = sqlSession.delete("earlyAdmissionPhysicalWomanRelativeMapper.deleteEarlyAdmissionPhysicalWomanRelative", earlyAdmissionPhysicalWomanRelativeDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 여자 상대평가 점수 정보 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
