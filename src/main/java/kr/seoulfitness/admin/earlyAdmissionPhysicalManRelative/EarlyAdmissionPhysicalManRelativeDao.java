package kr.seoulfitness.admin.earlyAdmissionPhysicalManRelative;

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
public class EarlyAdmissionPhysicalManRelativeDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(EarlyAdmissionPhysicalManRelativeDao.class);

    // 수시 모집 실기 남자 상대평가 점수 정보 등록
    public EarlyAdmissionPhysicalManRelativeDto insertEarlyAdmissionPhysicalManRelative(EarlyAdmissionPhysicalManRelativeDto earlyAdmissionPhysicalManRelativeDto) {
        try {
            sqlSession.insert("earlyAdmissionPhysicalManRelativeMapper.insertEarlyAdmissionPhysicalManRelative", earlyAdmissionPhysicalManRelativeDto);
            return earlyAdmissionPhysicalManRelativeDto;
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 상대평가 점수 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 수시 모집 실기 남자 상대평가 점수 목록
    public List<EarlyAdmissionPhysicalManRelativeDto> getEarlyAdmissionPhysicalManRelativeList(Map<String, Object> params) {
        List<EarlyAdmissionPhysicalManRelativeDto> earlyAdmissionPhysicalManRelativeList = null;

        try {
            earlyAdmissionPhysicalManRelativeList = sqlSession.selectList("earlyAdmissionPhysicalManRelativeMapper.getEarlyAdmissionPhysicalManRelativeList", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 상대평가 점수 목록 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionPhysicalManRelativeList;
    }

    // 수시 모집 실기 남자 상대평가 점수 정보 상세
    public EarlyAdmissionPhysicalManRelativeDto getEarlyAdmissionPhysicalManRelative(Map<String, Object> params) {
        EarlyAdmissionPhysicalManRelativeDto earlyAdmissionPhysicalManRelative = null;
        
        try {
            earlyAdmissionPhysicalManRelative = sqlSession.selectOne("earlyAdmissionPhysicalManRelativeMapper.getEarlyAdmissionPhysicalManRelative", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 상대평가 점수 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionPhysicalManRelative;
    }

    // 수시 모집 실기 남자 상대평가 점수 정보 수정
    public int updateEarlyAdmissionPhysicalManRelative(EarlyAdmissionPhysicalManRelativeDto earlyAdmissionPhysicalManRelativeDto) {
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
                    Method method = earlyAdmissionPhysicalManRelativeDto.getClass().getMethod(methodName);
                    useGrades.add((String) method.invoke(earlyAdmissionPhysicalManRelativeDto));

                    // 점수 처리
                    methodName = "getGrade" + i + "Score";
                    method = earlyAdmissionPhysicalManRelativeDto.getClass().getMethod(methodName);
                    gradeScores.add((Double) method.invoke(earlyAdmissionPhysicalManRelativeDto));
                    
                    // 기록 최소값 처리
                    methodName = "getGrade" + i + "RangeMin";
                    method = earlyAdmissionPhysicalManRelativeDto.getClass().getMethod(methodName);
                    gradeRangeMins.add((Double) method.invoke(earlyAdmissionPhysicalManRelativeDto));

                    // 기록 최대값 처리
                    methodName = "getGrade" + i + "RangeMax";
                    method = earlyAdmissionPhysicalManRelativeDto.getClass().getMethod(methodName);
                    gradeRangeMaxs.add((Double) method.invoke(earlyAdmissionPhysicalManRelativeDto));
                } catch (Exception e) {
                    logger.error("수시 모집 실기 남자 상대평가 점수 정보 수정 파라미터 처리 중 오류 발생: {}", e.getMessage(), e);
                }
            }

            earlyAdmissionPhysicalManRelativeDto.setUseGrades(useGrades);
            earlyAdmissionPhysicalManRelativeDto.setGradeScores(gradeScores);
            earlyAdmissionPhysicalManRelativeDto.setGradeRangeMins(gradeRangeMins);
            earlyAdmissionPhysicalManRelativeDto.setGradeRangeMaxs(gradeRangeMaxs);

            result = sqlSession.update("earlyAdmissionPhysicalManRelativeMapper.updateEarlyAdmissionPhysicalManRelative", earlyAdmissionPhysicalManRelativeDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 상대평가 점수 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 수시 모집 실기 남자 상대평가 점수 정보 삭제
    public int deleteEarlyAdmissionPhysicalManRelative(EarlyAdmissionPhysicalManRelativeDto earlyAdmissionPhysicalManRelativeDto) {
        int result = -1;

        try {
            result = sqlSession.delete("earlyAdmissionPhysicalManRelativeMapper.deleteEarlyAdmissionPhysicalManRelative", earlyAdmissionPhysicalManRelativeDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 상대평가 점수 정보 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
