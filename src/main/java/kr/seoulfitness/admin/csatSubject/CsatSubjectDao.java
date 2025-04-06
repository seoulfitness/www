package kr.seoulfitness.admin.csatSubject;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class CsatSubjectDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(CsatSubjectDao.class);

    // 학과 등록
    public int insertCsatSubject(CsatSubjectDto csatSubject) {
        int result = -1;

        try {
            result = sqlSession.insert("csatSubjectMapper.insertCsatSubject", csatSubject);
        } catch (DataAccessException e) {
            logger.error("수능 교과목 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 수능 교과목 목록
    public List<CsatSubjectDto> getCsatSubjects(Map<String, Object> params) {
        List<CsatSubjectDto> csatSubjects = null;

        try {
            csatSubjects = sqlSession.selectList("csatSubjectMapper.getCsatSubjects", params);
        } catch (DataAccessException e) {
            logger.error("수능 교과목 목록 오류 : {}", e.getMessage(), e);
        }

        return csatSubjects;
    }

    // 수능 교과목 상세보기
    public CsatSubjectDto getCsatSubject(int csatSubjectId) {
        CsatSubjectDto csatSubject = null;
        
        try {
            csatSubject = sqlSession.selectOne("csatSubjectMapper.getCsatSubject", csatSubjectId);
        } catch (DataAccessException e) {
            logger.error("수능 교과목 상세 오류 : {}", e.getMessage(), e);
        }

        return csatSubject;
    }

    // 수능 교과목 수정
    public int updateCsatSubject(CsatSubjectDto csatSubject) {
        int result = -1;

        try {
            result = sqlSession.update("csatSubjectMapper.updateCsatSubject", csatSubject);
        } catch (DataAccessException e) {
            logger.error("수능 교과목 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 수능 교과목 삭제
    public int deleteCsatSubject(int csatSubjectId) {
        int result = -1;

        try {
            result = sqlSession.delete("csatSubjectMapper.deleteCsatSubject", csatSubjectId);
        } catch (DataAccessException e) {
            logger.error("수능 교과목 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 수능 교과목 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = 0;

        try {
            totalCount = sqlSession.selectOne("csatSubjectMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 수능 교과목 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
