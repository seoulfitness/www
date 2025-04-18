package kr.seoulfitness.admin.internalSubject;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class InternalSubjectDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(InternalSubjectDao.class);

    // 내신 교과목 등록
    public int insertInternalSubject(InternalSubjectDto internalSubject) {
        int result = -1;

        try {
            result = sqlSession.insert("internalSubjectMapper.insertInternalSubject", internalSubject);
        } catch (DataAccessException e) {
            logger.error("내신 교과목 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 내신 교과목 목록
    public List<InternalSubjectDto> getInternalSubjects(Map<String, Object> params) {
        List<InternalSubjectDto> internalSubjects = null;

        try {
            internalSubjects = sqlSession.selectList("internalSubjectMapper.getInternalSubjects", params);
        } catch (DataAccessException e) {
            logger.error("내신 교과목 목록 오류 : {}", e.getMessage(), e);
        }

        return internalSubjects;
    }

    // 내신 교과목 상세보기
    public InternalSubjectDto getInternalSubject(int internalSubjectId) {
        InternalSubjectDto internalSubject = null;
        
        try {
            internalSubject = sqlSession.selectOne("internalSubjectMapper.getInternalSubject", internalSubjectId);
        } catch (DataAccessException e) {
            logger.error("내신 교과목 상세 오류 : {}", e.getMessage(), e);
        }

        return internalSubject;
    }

    // 내신 교과목 수정
    public int updateInternalSubject(InternalSubjectDto internalSubject) {
        int result = -1;

        try {
            result = sqlSession.update("internalSubjectMapper.updateInternalSubject", internalSubject);
        } catch (DataAccessException e) {
            logger.error("내신 교과목 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 내신 교과목 삭제
    public int deleteInternalSubject(int internalSubjectId) {
        int result = -1;

        try {
            result = sqlSession.delete("internalSubjectMapper.deleteInternalSubject", internalSubjectId);
        } catch (DataAccessException e) {
            logger.error("내신 교과목 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 내신 교과목 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = 0;

        try {
            totalCount = sqlSession.selectOne("internalSubjectMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 내신 교과목 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
