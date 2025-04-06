package kr.seoulfitness.admin.regularAdmissionPhysicalManAbsolute;

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
public class RegularAdmissionPhysicalManAbsoluteDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(RegularAdmissionPhysicalManAbsoluteDao.class);

    // 정시 모집 실기 남자 절대평가 점수 정보 등록
    public RegularAdmissionPhysicalManAbsoluteDto insertRegularAdmissionPhysicalManAbsolute(RegularAdmissionPhysicalManAbsoluteDto regularAdmissionPhysicalManAbsoluteDto) {
        try {
            sqlSession.insert("regularAdmissionPhysicalManAbsoluteMapper.insertRegularAdmissionPhysicalManAbsolute", regularAdmissionPhysicalManAbsoluteDto);
            return regularAdmissionPhysicalManAbsoluteDto;
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 남자 절대평가 점수 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 정시 모집 실기 남자 절대평가 점수 목록
    public List<RegularAdmissionPhysicalManAbsoluteDto> getRegularAdmissionPhysicalManAbsoluteList(Map<String, Object> params) {
        List<RegularAdmissionPhysicalManAbsoluteDto> regularAdmissionPhysicalManAbsoluteList = null;

        try {
            regularAdmissionPhysicalManAbsoluteList = sqlSession.selectList("regularAdmissionPhysicalManAbsoluteMapper.getRegularAdmissionPhysicalManAbsoluteList", params);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 남자 절대평가 점수 목록 오류 : {}", e.getMessage(), e);
        }

        return regularAdmissionPhysicalManAbsoluteList;
    }

    // 정시 모집 실기 남자 절대평가 점수 정보 상세
    public RegularAdmissionPhysicalManAbsoluteDto getRegularAdmissionPhysicalManAbsolute(Map<String, Object> params) {
        RegularAdmissionPhysicalManAbsoluteDto regularAdmissionPhysicalManAbsolute = null;
        
        try {
            regularAdmissionPhysicalManAbsolute = sqlSession.selectOne("regularAdmissionPhysicalManAbsoluteMapper.getRegularAdmissionPhysicalManAbsolute", params);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 남자 절대평가 점수 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return regularAdmissionPhysicalManAbsolute;
    }

    // 정시 모집 실기 남자 절대평가 점수 정보 수정
    public int updateRegularAdmissionPhysicalManAbsolute(RegularAdmissionPhysicalManAbsoluteDto regularAdmissionPhysicalManAbsoluteDto) {
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
                    Method method = regularAdmissionPhysicalManAbsoluteDto.getClass().getMethod(methodName);
                    useGrades.add((String) method.invoke(regularAdmissionPhysicalManAbsoluteDto));

                    // 점수 처리
                    methodName = "getGrade" + i + "Score";
                    method = regularAdmissionPhysicalManAbsoluteDto.getClass().getMethod(methodName);
                    gradeScores.add((Double) method.invoke(regularAdmissionPhysicalManAbsoluteDto));
                    
                    // 기록 최소값 처리
                    methodName = "getGrade" + i + "RecordMin";
                    method = regularAdmissionPhysicalManAbsoluteDto.getClass().getMethod(methodName);
                    gradeRecordMins.add((Double) method.invoke(regularAdmissionPhysicalManAbsoluteDto));

                    // 기록 최대값 처리
                    methodName = "getGrade" + i + "RecordMax";
                    method = regularAdmissionPhysicalManAbsoluteDto.getClass().getMethod(methodName);
                    gradeRecordMaxs.add((Double) method.invoke(regularAdmissionPhysicalManAbsoluteDto));
                } catch (Exception e) {
                    logger.error("정시 모집 실기 남자 절대평가 점수 정보 수정 파라미터 처리 중 오류 발생: {}", e.getMessage(), e);
                }
            }

            regularAdmissionPhysicalManAbsoluteDto.setUseGrades(useGrades);
            regularAdmissionPhysicalManAbsoluteDto.setGradeScores(gradeScores);
            regularAdmissionPhysicalManAbsoluteDto.setGradeRecordMins(gradeRecordMins);
            regularAdmissionPhysicalManAbsoluteDto.setGradeRecordMaxs(gradeRecordMaxs);

            result = sqlSession.update("regularAdmissionPhysicalManAbsoluteMapper.updateRegularAdmissionPhysicalManAbsolute", regularAdmissionPhysicalManAbsoluteDto);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 남자 절대평가 점수 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 정시 모집 실기 남자 절대평가 점수 정보 삭제
    public int deleteRegularAdmissionPhysicalManAbsolute(RegularAdmissionPhysicalManAbsoluteDto regularAdmissionPhysicalManAbsoluteDto) {
        int result = -1;

        try {
            result = sqlSession.delete("regularAdmissionPhysicalManAbsoluteMapper.deleteRegularAdmissionPhysicalManAbsolute", regularAdmissionPhysicalManAbsoluteDto);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 남자 절대평가 점수 정보 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
