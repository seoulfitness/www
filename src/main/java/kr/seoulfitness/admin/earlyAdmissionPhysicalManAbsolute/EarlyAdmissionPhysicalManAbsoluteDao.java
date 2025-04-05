package kr.seoulfitness.admin.earlyAdmissionPhysicalManAbsolute;

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
public class EarlyAdmissionPhysicalManAbsoluteDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(EarlyAdmissionPhysicalManAbsoluteDao.class);

    // 수시 모집 실기 남자 절대평가 점수 정보 등록
    public EarlyAdmissionPhysicalManAbsoluteDto insertEarlyAdmissionPhysicalManAbsolute(EarlyAdmissionPhysicalManAbsoluteDto earlyAdmissionPhysicalManAbsoluteDto) {
        try {
            sqlSession.insert("earlyAdmissionPhysicalManAbsoluteMapper.insertEarlyAdmissionPhysicalManAbsolute", earlyAdmissionPhysicalManAbsoluteDto);
            return earlyAdmissionPhysicalManAbsoluteDto;
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 절대평가 점수 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 수시 모집 실기 남자 절대평가 점수 목록
    public List<EarlyAdmissionPhysicalManAbsoluteDto> getEarlyAdmissionPhysicalManAbsoluteList(Map<String, Object> params) {
        List<EarlyAdmissionPhysicalManAbsoluteDto> earlyAdmissionPhysicalManAbsoluteList = null;

        try {
            earlyAdmissionPhysicalManAbsoluteList = sqlSession.selectList("earlyAdmissionPhysicalManAbsoluteMapper.getEarlyAdmissionPhysicalManAbsoluteList", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 절대평가 점수 목록 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionPhysicalManAbsoluteList;
    }

    // 수시 모집 실기 남자 절대평가 점수 정보 상세
    public EarlyAdmissionPhysicalManAbsoluteDto getEarlyAdmissionPhysicalManAbsolute(Map<String, Object> params) {
        EarlyAdmissionPhysicalManAbsoluteDto earlyAdmissionPhysicalManAbsolute = null;
        
        try {
            earlyAdmissionPhysicalManAbsolute = sqlSession.selectOne("earlyAdmissionPhysicalManAbsoluteMapper.getEarlyAdmissionPhysicalManAbsolute", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 절대평가 점수 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionPhysicalManAbsolute;
    }

    // 수시 모집 실기 남자 절대평가 점수 정보 수정
    public int updateEarlyAdmissionPhysicalManAbsolute(EarlyAdmissionPhysicalManAbsoluteDto earlyAdmissionPhysicalManAbsoluteDto) {
        int result = -1;

        try {
            List<String> useGrades = new ArrayList<>();
            List<Double> gradeScores = new ArrayList<>();
            List<Double> gradeRecordMins = new ArrayList<>();
            List<Double> gradeRecordMaxs = new ArrayList<>();

            for (int i = 1; i <= 40; i++) {
                try {
                    // 등급 사용 여부 처리
                    String methodName = "getUseGrade" + i;
                    Method method = earlyAdmissionPhysicalManAbsoluteDto.getClass().getMethod(methodName);
                    useGrades.add((String) method.invoke(earlyAdmissionPhysicalManAbsoluteDto));

                    // 점수 처리
                    methodName = "getGrade" + i + "Score";
                    method = earlyAdmissionPhysicalManAbsoluteDto.getClass().getMethod(methodName);
                    gradeScores.add((Double) method.invoke(earlyAdmissionPhysicalManAbsoluteDto));
                    
                    // 기록 최소값 처리
                    methodName = "getGrade" + i + "RecordMin";
                    method = earlyAdmissionPhysicalManAbsoluteDto.getClass().getMethod(methodName);
                    gradeRecordMins.add((Double) method.invoke(earlyAdmissionPhysicalManAbsoluteDto));

                    // 기록 최대값 처리
                    methodName = "getGrade" + i + "RecordMax";
                    method = earlyAdmissionPhysicalManAbsoluteDto.getClass().getMethod(methodName);
                    gradeRecordMaxs.add((Double) method.invoke(earlyAdmissionPhysicalManAbsoluteDto));
                } catch (Exception e) {
                    logger.error("수시 모집 실기 남자 절대평가 점수 정보 수정 파라미터 처리 중 오류 발생: {}", e.getMessage(), e);
                }
            }

            earlyAdmissionPhysicalManAbsoluteDto.setUseGrades(useGrades);
            earlyAdmissionPhysicalManAbsoluteDto.setGradeScores(gradeScores);
            earlyAdmissionPhysicalManAbsoluteDto.setGradeRecordMins(gradeRecordMins);
            earlyAdmissionPhysicalManAbsoluteDto.setGradeRecordMaxs(gradeRecordMaxs);

            result = sqlSession.update("earlyAdmissionPhysicalManAbsoluteMapper.updateEarlyAdmissionPhysicalManAbsolute", earlyAdmissionPhysicalManAbsoluteDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 절대평가 점수 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 수시 모집 실기 남자 절대평가 점수 정보 삭제
    public int deleteEarlyAdmissionPhysicalManAbsolute(EarlyAdmissionPhysicalManAbsoluteDto earlyAdmissionPhysicalManAbsoluteDto) {
        int result = -1;

        try {
            result = sqlSession.delete("earlyAdmissionPhysicalManAbsoluteMapper.deleteEarlyAdmissionPhysicalManAbsolute", earlyAdmissionPhysicalManAbsoluteDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 절대평가 점수 정보 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
