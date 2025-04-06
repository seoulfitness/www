package kr.seoulfitness.admin.regularAdmissionPhysicalManRelative;

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
public class RegularAdmissionPhysicalManRelativeDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(RegularAdmissionPhysicalManRelativeDao.class);

    // 정시 모집 실기 남자 상대평가 점수 정보 등록
    public RegularAdmissionPhysicalManRelativeDto insertRegularAdmissionPhysicalManRelative(RegularAdmissionPhysicalManRelativeDto regularAdmissionPhysicalManRelativeDto) {
        try {
            sqlSession.insert("regularAdmissionPhysicalManRelativeMapper.insertRegularAdmissionPhysicalManRelative", regularAdmissionPhysicalManRelativeDto);
            return regularAdmissionPhysicalManRelativeDto;
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 남자 상대평가 점수 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 정시 모집 실기 남자 상대평가 점수 목록
    public List<RegularAdmissionPhysicalManRelativeDto> getRegularAdmissionPhysicalManRelativeList(Map<String, Object> params) {
        List<RegularAdmissionPhysicalManRelativeDto> regularAdmissionPhysicalManRelativeList = null;

        try {
            regularAdmissionPhysicalManRelativeList = sqlSession.selectList("regularAdmissionPhysicalManRelativeMapper.getRegularAdmissionPhysicalManRelativeList", params);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 남자 상대평가 점수 목록 오류 : {}", e.getMessage(), e);
        }

        return regularAdmissionPhysicalManRelativeList;
    }

    // 정시 모집 실기 남자 상대평가 점수 정보 상세
    public RegularAdmissionPhysicalManRelativeDto getRegularAdmissionPhysicalManRelative(Map<String, Object> params) {
        RegularAdmissionPhysicalManRelativeDto regularAdmissionPhysicalManRelative = null;
        
        try {
            regularAdmissionPhysicalManRelative = sqlSession.selectOne("regularAdmissionPhysicalManRelativeMapper.getRegularAdmissionPhysicalManRelative", params);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 남자 상대평가 점수 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return regularAdmissionPhysicalManRelative;
    }

    // 정시 모집 실기 남자 상대평가 점수 정보 수정
    public int updateRegularAdmissionPhysicalManRelative(RegularAdmissionPhysicalManRelativeDto regularAdmissionPhysicalManRelativeDto) {
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
                    Method method = regularAdmissionPhysicalManRelativeDto.getClass().getMethod(methodName);
                    useGrades.add((String) method.invoke(regularAdmissionPhysicalManRelativeDto));

                    // 점수 처리
                    methodName = "getGrade" + i + "Score";
                    method = regularAdmissionPhysicalManRelativeDto.getClass().getMethod(methodName);
                    gradeScores.add((Double) method.invoke(regularAdmissionPhysicalManRelativeDto));
                    
                    // 기록 최소값 처리
                    methodName = "getGrade" + i + "RangeMin";
                    method = regularAdmissionPhysicalManRelativeDto.getClass().getMethod(methodName);
                    gradeRangeMins.add((Double) method.invoke(regularAdmissionPhysicalManRelativeDto));

                    // 기록 최대값 처리
                    methodName = "getGrade" + i + "RangeMax";
                    method = regularAdmissionPhysicalManRelativeDto.getClass().getMethod(methodName);
                    gradeRangeMaxs.add((Double) method.invoke(regularAdmissionPhysicalManRelativeDto));
                } catch (Exception e) {
                    logger.error("정시 모집 실기 남자 상대평가 점수 정보 수정 파라미터 처리 중 오류 발생: {}", e.getMessage(), e);
                }
            }

            regularAdmissionPhysicalManRelativeDto.setUseGrades(useGrades);
            regularAdmissionPhysicalManRelativeDto.setGradeScores(gradeScores);
            regularAdmissionPhysicalManRelativeDto.setGradeRangeMins(gradeRangeMins);
            regularAdmissionPhysicalManRelativeDto.setGradeRangeMaxs(gradeRangeMaxs);

            result = sqlSession.update("regularAdmissionPhysicalManRelativeMapper.updateRegularAdmissionPhysicalManRelative", regularAdmissionPhysicalManRelativeDto);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 남자 상대평가 점수 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 정시 모집 실기 남자 상대평가 점수 정보 삭제
    public int deleteRegularAdmissionPhysicalManRelative(RegularAdmissionPhysicalManRelativeDto regularAdmissionPhysicalManRelativeDto) {
        int result = -1;

        try {
            result = sqlSession.delete("regularAdmissionPhysicalManRelativeMapper.deleteRegularAdmissionPhysicalManRelative", regularAdmissionPhysicalManRelativeDto);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 남자 상대평가 점수 정보 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
