package kr.seoulfitness.admin.highSchool;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class HighSchoolDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(HighSchoolDao.class);

    // 고등학교 등록
    public int insertHighSchool(HighSchoolDto highSchool) {
        int result = -1;

        try {
            result = sqlSession.insert("highSchoolMapper.insertHighSchool", highSchool);
        } catch (DataAccessException e) {
            logger.error("고등학교 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 고등학교 목록
    public List<HighSchoolDto> getHighSchools(Map<String, Object> params) {
        List<HighSchoolDto> highSchools = null;

        try {
            highSchools = sqlSession.selectList("highSchoolMapper.getHighSchools", params);
        } catch (DataAccessException e) {
            logger.error("고등학교 목록 오류 : {}", e.getMessage(), e);
        }

        return highSchools;
    }

    // 고등학교 상세보기
    public HighSchoolDto getHighSchool(int highSchoolId) {
        HighSchoolDto highSchool = null;
        
        try {
            highSchool = sqlSession.selectOne("highSchoolMapper.getHighSchool", highSchoolId);
        } catch (DataAccessException e) {
            logger.error("고등학교 상세 오류 : {}", e.getMessage(), e);
        }

        return highSchool;
    }

    // 고등학교 수정
    public int updateHighSchool(HighSchoolDto highSchool) {
        int result = -1;

        try {
            result = sqlSession.update("highSchoolMapper.updateHighSchool", highSchool);
        } catch (DataAccessException e) {
            logger.error("고등학교 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 고등학교 삭제
    public int deleteHighSchool(int highSchoolId) {
        int result = -1;

        try {
            result = sqlSession.delete("highSchoolMapper.deleteHighSchool", highSchoolId);
        } catch (DataAccessException e) {
            logger.error("고등학교 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 고등학교 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("highSchoolMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 고등학교 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
