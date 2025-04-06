package kr.seoulfitness.admin.regularAdmissionPhysicalWomanRelative;

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
public class RegularAdmissionPhysicalWomanRelativeDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(RegularAdmissionPhysicalWomanRelativeDao.class);

    // 정시 모집 실기 여자 상대평가 점수 정보 등록
    public RegularAdmissionPhysicalWomanRelativeDto insertRegularAdmissionPhysicalWomanRelative(RegularAdmissionPhysicalWomanRelativeDto regularAdmissionPhysicalWomanRelativeDto) {
        try {
            sqlSession.insert("regularAdmissionPhysicalWomanRelativeMapper.insertRegularAdmissionPhysicalWomanRelative", regularAdmissionPhysicalWomanRelativeDto);
            return regularAdmissionPhysicalWomanRelativeDto;
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 여자 상대평가 점수 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 정시 모집 실기 여자 상대평가 점수 목록
    public List<RegularAdmissionPhysicalWomanRelativeDto> getRegularAdmissionPhysicalWomanRelativeList(Map<String, Object> params) {
        List<RegularAdmissionPhysicalWomanRelativeDto> regularAdmissionPhysicalWomanRelativeList = null;

        try {
            regularAdmissionPhysicalWomanRelativeList = sqlSession.selectList("regularAdmissionPhysicalWomanRelativeMapper.getRegularAdmissionPhysicalWomanRelativeList", params);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 여자 상대평가 점수 목록 오류 : {}", e.getMessage(), e);
        }

        return regularAdmissionPhysicalWomanRelativeList;
    }

    // 정시 모집 실기 여자 상대평가 점수 정보 상세
    public RegularAdmissionPhysicalWomanRelativeDto getRegularAdmissionPhysicalWomanRelative(Map<String, Object> params) {
        RegularAdmissionPhysicalWomanRelativeDto regularAdmissionPhysicalWomanRelative = null;
        
        try {
            regularAdmissionPhysicalWomanRelative = sqlSession.selectOne("regularAdmissionPhysicalWomanRelativeMapper.getRegularAdmissionPhysicalWomanRelative", params);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 여자 상대평가 점수 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return regularAdmissionPhysicalWomanRelative;
    }

    // 정시 모집 실기 여자 상대평가 점수 정보 수정
    public int updateRegularAdmissionPhysicalWomanRelative(RegularAdmissionPhysicalWomanRelativeDto regularAdmissionPhysicalWomanRelativeDto) {
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
                    Method method = regularAdmissionPhysicalWomanRelativeDto.getClass().getMethod(methodName);
                    useGrades.add((String) method.invoke(regularAdmissionPhysicalWomanRelativeDto));

                    // 점수 처리
                    methodName = "getGrade" + i + "Score";
                    method = regularAdmissionPhysicalWomanRelativeDto.getClass().getMethod(methodName);
                    gradeScores.add((Double) method.invoke(regularAdmissionPhysicalWomanRelativeDto));
                    
                    // 기록 최소값 처리
                    methodName = "getGrade" + i + "RangeMin";
                    method = regularAdmissionPhysicalWomanRelativeDto.getClass().getMethod(methodName);
                    gradeRangeMins.add((Double) method.invoke(regularAdmissionPhysicalWomanRelativeDto));

                    // 기록 최대값 처리
                    methodName = "getGrade" + i + "RangeMax";
                    method = regularAdmissionPhysicalWomanRelativeDto.getClass().getMethod(methodName);
                    gradeRangeMaxs.add((Double) method.invoke(regularAdmissionPhysicalWomanRelativeDto));
                } catch (Exception e) {
                    logger.error("정시 모집 실기 여자 상대평가 점수 정보 수정 파라미터 처리 중 오류 발생: {}", e.getMessage(), e);
                }
            }

            regularAdmissionPhysicalWomanRelativeDto.setUseGrades(useGrades);
            regularAdmissionPhysicalWomanRelativeDto.setGradeScores(gradeScores);
            regularAdmissionPhysicalWomanRelativeDto.setGradeRangeMins(gradeRangeMins);
            regularAdmissionPhysicalWomanRelativeDto.setGradeRangeMaxs(gradeRangeMaxs);

            result = sqlSession.update("regularAdmissionPhysicalWomanRelativeMapper.updateRegularAdmissionPhysicalWomanRelative", regularAdmissionPhysicalWomanRelativeDto);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 여자 상대평가 점수 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 정시 모집 실기 여자 상대평가 점수 정보 삭제
    public int deleteRegularAdmissionPhysicalWomanRelative(RegularAdmissionPhysicalWomanRelativeDto regularAdmissionPhysicalWomanRelativeDto) {
        int result = -1;

        try {
            result = sqlSession.delete("regularAdmissionPhysicalWomanRelativeMapper.deleteRegularAdmissionPhysicalWomanRelative", regularAdmissionPhysicalWomanRelativeDto);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 여자 상대평가 점수 정보 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
