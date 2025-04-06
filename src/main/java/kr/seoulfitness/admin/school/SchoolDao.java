package kr.seoulfitness.admin.school;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class SchoolDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(SchoolDao.class);

    // 학교 등록
    public int insertSchool(SchoolDto school) {
        int result = -1;

        try {
            result = sqlSession.insert("schoolMapper.insertSchool", school);
        } catch (DataAccessException e) {
            logger.error("학교 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 학교 목록
    public List<SchoolDto> getSchools(Map<String, Object> params) {
        List<SchoolDto> schools = null;

        try {
            schools = sqlSession.selectList("schoolMapper.getSchools", params);
        } catch (DataAccessException e) {
            logger.error("학교 목록 오류 : {}", e.getMessage(), e);
        }

        return schools;
    }

    // 학교 상세보기
    public SchoolDto getSchool(int schoolId) {
        SchoolDto school = null;
        
        try {
            school = sqlSession.selectOne("schoolMapper.getSchool", schoolId);
        } catch (DataAccessException e) {
            logger.error("학교 상세 오류 : {}", e.getMessage(), e);
        }

        return school;
    }

    // 학교 수정
    public int updateSchool(SchoolDto school) {
        int result = -1;

        try {
            result = sqlSession.update("schoolMapper.updateSchool", school);
        } catch (DataAccessException e) {
            logger.error("학교 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 학교 삭제
    public int deleteSchool(int schoolId) {
        int result = -1;

        try {
            result = sqlSession.delete("schoolMapper.deleteSchool", schoolId);
        } catch (DataAccessException e) {
            logger.error("학교 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 학교 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = 0;

        try {
            totalCount = sqlSession.selectOne("schoolMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 학교 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
