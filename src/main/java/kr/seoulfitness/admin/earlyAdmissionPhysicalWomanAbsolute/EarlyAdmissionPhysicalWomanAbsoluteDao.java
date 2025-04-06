package kr.seoulfitness.admin.earlyAdmissionPhysicalWomanAbsolute;

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
public class EarlyAdmissionPhysicalWomanAbsoluteDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(EarlyAdmissionPhysicalWomanAbsoluteDao.class);

    // 수시 모집 실기 여자 절대평가 점수 정보 등록
    public EarlyAdmissionPhysicalWomanAbsoluteDto insertEarlyAdmissionPhysicalWomanAbsolute(EarlyAdmissionPhysicalWomanAbsoluteDto earlyAdmissionPhysicalWomanAbsoluteDto) {
        try {
            sqlSession.insert("earlyAdmissionPhysicalWomanAbsoluteMapper.insertEarlyAdmissionPhysicalWomanAbsolute", earlyAdmissionPhysicalWomanAbsoluteDto);
            return earlyAdmissionPhysicalWomanAbsoluteDto;
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 여자 절대평가 점수 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 수시 모집 실기 여자 절대평가 점수 목록
    public List<EarlyAdmissionPhysicalWomanAbsoluteDto> getEarlyAdmissionPhysicalWomanAbsoluteList(Map<String, Object> params) {
        List<EarlyAdmissionPhysicalWomanAbsoluteDto> earlyAdmissionPhysicalWomanAbsoluteList = null;

        try {
            earlyAdmissionPhysicalWomanAbsoluteList = sqlSession.selectList("earlyAdmissionPhysicalWomanAbsoluteMapper.getEarlyAdmissionPhysicalWomanAbsoluteList", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 여자 절대평가 점수 목록 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionPhysicalWomanAbsoluteList;
    }

    // 수시 모집 실기 여자 절대평가 점수 정보 상세
    public EarlyAdmissionPhysicalWomanAbsoluteDto getEarlyAdmissionPhysicalWomanAbsolute(Map<String, Object> params) {
        EarlyAdmissionPhysicalWomanAbsoluteDto earlyAdmissionPhysicalWomanAbsolute = null;
        
        try {
            earlyAdmissionPhysicalWomanAbsolute = sqlSession.selectOne("earlyAdmissionPhysicalWomanAbsoluteMapper.getEarlyAdmissionPhysicalWomanAbsolute", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 여자 절대평가 점수 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionPhysicalWomanAbsolute;
    }

    // 수시 모집 실기 여자 절대평가 점수 정보 수정
    public int updateEarlyAdmissionPhysicalWomanAbsolute(EarlyAdmissionPhysicalWomanAbsoluteDto earlyAdmissionPhysicalWomanAbsoluteDto) {
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
                    Method method = earlyAdmissionPhysicalWomanAbsoluteDto.getClass().getMethod(methodName);
                    useGrades.add((String) method.invoke(earlyAdmissionPhysicalWomanAbsoluteDto));

                    // 점수 처리
                    methodName = "getGrade" + i + "Score";
                    method = earlyAdmissionPhysicalWomanAbsoluteDto.getClass().getMethod(methodName);
                    gradeScores.add((Double) method.invoke(earlyAdmissionPhysicalWomanAbsoluteDto));
                    
                    // 기록 최소값 처리
                    methodName = "getGrade" + i + "RecordMin";
                    method = earlyAdmissionPhysicalWomanAbsoluteDto.getClass().getMethod(methodName);
                    gradeRecordMins.add((Double) method.invoke(earlyAdmissionPhysicalWomanAbsoluteDto));

                    // 기록 최대값 처리
                    methodName = "getGrade" + i + "RecordMax";
                    method = earlyAdmissionPhysicalWomanAbsoluteDto.getClass().getMethod(methodName);
                    gradeRecordMaxs.add((Double) method.invoke(earlyAdmissionPhysicalWomanAbsoluteDto));
                } catch (Exception e) {
                    logger.error("수시 모집 실기 여자 절대평가 점수 정보 수정 파라미터 처리 중 오류 발생: {}", e.getMessage(), e);
                }
            }

            earlyAdmissionPhysicalWomanAbsoluteDto.setUseGrades(useGrades);
            earlyAdmissionPhysicalWomanAbsoluteDto.setGradeScores(gradeScores);
            earlyAdmissionPhysicalWomanAbsoluteDto.setGradeRecordMins(gradeRecordMins);
            earlyAdmissionPhysicalWomanAbsoluteDto.setGradeRecordMaxs(gradeRecordMaxs);

            result = sqlSession.update("earlyAdmissionPhysicalWomanAbsoluteMapper.updateEarlyAdmissionPhysicalWomanAbsolute", earlyAdmissionPhysicalWomanAbsoluteDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 여자 절대평가 점수 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 수시 모집 실기 여자 절대평가 점수 정보 삭제
    public int deleteEarlyAdmissionPhysicalWomanAbsolute(EarlyAdmissionPhysicalWomanAbsoluteDto earlyAdmissionPhysicalWomanAbsoluteDto) {
        int result = -1;

        try {
            result = sqlSession.delete("earlyAdmissionPhysicalWomanAbsoluteMapper.deleteEarlyAdmissionPhysicalWomanAbsolute", earlyAdmissionPhysicalWomanAbsoluteDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 여자 절대평가 점수 정보 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
