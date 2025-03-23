package kr.seoulfitness.admin.criteria;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class CriteriaDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(CriteriaDao.class);

    // 입시 요강 등록
    public int insertCriteria(CriteriaDto criteria) {
        int result = -1;

        try {
            result = sqlSession.insert("criteriaMapper.insertCriteria", criteria);
        } catch (DataAccessException e) {
            logger.error("입시 요강 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 입시 요강 목록
    public List<CriteriaDto> getCriterias(Map<String, Object> params) {
        List<CriteriaDto> criterias = null;

        try {
            criterias = sqlSession.selectList("criteriaMapper.getCriterias", params);
        } catch (DataAccessException e) {
            logger.error("입시 요강 목록 오류 : {}", e.getMessage(), e);
        }

        return criterias;
    }

    // 입시 요강 상세보기
    public CriteriaDto getCriteria(int criteriaId) {
        CriteriaDto criteria = null;
        
        try {
            criteria = sqlSession.selectOne("criteriaMapper.getCriteria", criteriaId);
        } catch (DataAccessException e) {
            logger.error("입시 요강 상세 오류 : {}", e.getMessage(), e);
        }

        return criteria;
    }

    // 입시 요강 수정
    public int updateCriteria(CriteriaDto criteria) {
        int result = -1;

        try {
            result = sqlSession.update("criteriaMapper.updateCriteria", criteria);
        } catch (DataAccessException e) {
            logger.error("입시 요강 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 입시 요강 삭제
    public int deleteCriteria(int criteriaId) {
        int result = -1;

        try {
            result = sqlSession.delete("criteriaMapper.deleteCriteria", criteriaId);
        } catch (DataAccessException e) {
            logger.error("입시 요강 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 입시 요강 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("criteriaMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 입시 요강 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
