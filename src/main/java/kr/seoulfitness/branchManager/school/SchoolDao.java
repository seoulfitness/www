package kr.seoulfitness.branchManager.school;

import java.util.HashMap;
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
    public int create(SchoolDto school) {
        int result = -1;

        try {
            result = sqlSession.insert("schoolMapper.create", school);
        } catch (DataAccessException e) {
            logger.error("학교 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 학교 목록
    public List<SchoolDto> list(int offset, int listCountPerPage, String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("listCountPerPage", listCountPerPage);
        params.put("keyword", keyword);

        List<SchoolDto> schools = null;

        try {
            schools = sqlSession.selectList("schoolMapper.list", params);
        } catch (DataAccessException e) {
            logger.error("학교 목록 오류 : {}", e.getMessage(), e);
        }

        return schools;
    }

    // 학교 상세보기
    public SchoolDto read(int schoolId) {
        SchoolDto school = null;
        
        try {
            school = sqlSession.selectOne("schoolMapper.read", schoolId);
        } catch (DataAccessException e) {
            logger.error("학교 상세 오류 : {}", e.getMessage(), e);
        }

        return school;
    }

    // 학교 수정
    public int update(SchoolDto school) {
        int result = -1;

        try {
            result = sqlSession.update("schoolMapper.update", school);
        } catch (DataAccessException e) {
            logger.error("학교 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 학교 삭제
    public int delete(int schoolId) {
        int result = -1;

        try {
            result = sqlSession.delete("schoolMapper.delete", schoolId);
        } catch (DataAccessException e) {
            logger.error("학교 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 학교 수
    public int totalCount(String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);

        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("schoolMapper.totalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 학교 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
