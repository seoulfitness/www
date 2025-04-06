package kr.seoulfitness.admin.regularAdmissionPhysicalWomanAbsolute;

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
public class RegularAdmissionPhysicalWomanAbsoluteDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(RegularAdmissionPhysicalWomanAbsoluteDao.class);

    // 정시 모집 실기 여자 절대평가 점수 정보 등록
    public RegularAdmissionPhysicalWomanAbsoluteDto insertRegularAdmissionPhysicalWomanAbsolute(RegularAdmissionPhysicalWomanAbsoluteDto regularAdmissionPhysicalWomanAbsoluteDto) {
        try {
            sqlSession.insert("regularAdmissionPhysicalWomanAbsoluteMapper.insertRegularAdmissionPhysicalWomanAbsolute", regularAdmissionPhysicalWomanAbsoluteDto);
            return regularAdmissionPhysicalWomanAbsoluteDto;
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 여자 절대평가 점수 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 정시 모집 실기 여자 절대평가 점수 목록
    public List<RegularAdmissionPhysicalWomanAbsoluteDto> getRegularAdmissionPhysicalWomanAbsoluteList(Map<String, Object> params) {
        List<RegularAdmissionPhysicalWomanAbsoluteDto> regularAdmissionPhysicalWomanAbsoluteList = null;

        try {
            regularAdmissionPhysicalWomanAbsoluteList = sqlSession.selectList("regularAdmissionPhysicalWomanAbsoluteMapper.getRegularAdmissionPhysicalWomanAbsoluteList", params);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 여자 절대평가 점수 목록 오류 : {}", e.getMessage(), e);
        }

        return regularAdmissionPhysicalWomanAbsoluteList;
    }

    // 정시 모집 실기 여자 절대평가 점수 정보 상세
    public RegularAdmissionPhysicalWomanAbsoluteDto getRegularAdmissionPhysicalWomanAbsolute(Map<String, Object> params) {
        RegularAdmissionPhysicalWomanAbsoluteDto regularAdmissionPhysicalWomanAbsolute = null;
        
        try {
            regularAdmissionPhysicalWomanAbsolute = sqlSession.selectOne("regularAdmissionPhysicalWomanAbsoluteMapper.getRegularAdmissionPhysicalWomanAbsolute", params);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 여자 절대평가 점수 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return regularAdmissionPhysicalWomanAbsolute;
    }

    // 정시 모집 실기 여자 절대평가 점수 정보 수정
    public int updateRegularAdmissionPhysicalWomanAbsolute(RegularAdmissionPhysicalWomanAbsoluteDto regularAdmissionPhysicalWomanAbsoluteDto) {
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
                    Method method = regularAdmissionPhysicalWomanAbsoluteDto.getClass().getMethod(methodName);
                    useGrades.add((String) method.invoke(regularAdmissionPhysicalWomanAbsoluteDto));

                    // 점수 처리
                    methodName = "getGrade" + i + "Score";
                    method = regularAdmissionPhysicalWomanAbsoluteDto.getClass().getMethod(methodName);
                    gradeScores.add((Double) method.invoke(regularAdmissionPhysicalWomanAbsoluteDto));
                    
                    // 기록 최소값 처리
                    methodName = "getGrade" + i + "RecordMin";
                    method = regularAdmissionPhysicalWomanAbsoluteDto.getClass().getMethod(methodName);
                    gradeRecordMins.add((Double) method.invoke(regularAdmissionPhysicalWomanAbsoluteDto));

                    // 기록 최대값 처리
                    methodName = "getGrade" + i + "RecordMax";
                    method = regularAdmissionPhysicalWomanAbsoluteDto.getClass().getMethod(methodName);
                    gradeRecordMaxs.add((Double) method.invoke(regularAdmissionPhysicalWomanAbsoluteDto));
                } catch (Exception e) {
                    logger.error("정시 모집 실기 여자 절대평가 점수 정보 수정 파라미터 처리 중 오류 발생: {}", e.getMessage(), e);
                }
            }

            regularAdmissionPhysicalWomanAbsoluteDto.setUseGrades(useGrades);
            regularAdmissionPhysicalWomanAbsoluteDto.setGradeScores(gradeScores);
            regularAdmissionPhysicalWomanAbsoluteDto.setGradeRecordMins(gradeRecordMins);
            regularAdmissionPhysicalWomanAbsoluteDto.setGradeRecordMaxs(gradeRecordMaxs);

            result = sqlSession.update("regularAdmissionPhysicalWomanAbsoluteMapper.updateRegularAdmissionPhysicalWomanAbsolute", regularAdmissionPhysicalWomanAbsoluteDto);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 여자 절대평가 점수 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 정시 모집 실기 여자 절대평가 점수 정보 삭제
    public int deleteRegularAdmissionPhysicalWomanAbsolute(RegularAdmissionPhysicalWomanAbsoluteDto regularAdmissionPhysicalWomanAbsoluteDto) {
        int result = -1;

        try {
            result = sqlSession.delete("regularAdmissionPhysicalWomanAbsoluteMapper.deleteRegularAdmissionPhysicalWomanAbsolute", regularAdmissionPhysicalWomanAbsoluteDto);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 여자 절대평가 점수 정보 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
