package kr.seoulfitness.admin.internalSubSubject;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class InternalSubSubjectDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(InternalSubSubjectDao.class);

    // 내신 세부 교과목 등록
    public int insertInternalSubSubject(InternalSubSubjectDto internalSubSubject) {
        int result = -1;

        try {
            result = sqlSession.insert("internalSubSubjectMapper.insertInternalSubSubject", internalSubSubject);
        } catch (DataAccessException e) {
            logger.error("내신 세부 교과목 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 내신 세부 교과목 목록
    public List<InternalSubSubjectDto> getInternalSubSubjects(Map<String, Object> params) {
        List<InternalSubSubjectDto> internalSubSubjects = null;

        try {
            internalSubSubjects = sqlSession.selectList("internalSubSubjectMapper.getInternalSubSubjects", params);
        } catch (DataAccessException e) {
            logger.error("내신 세부 교과목 목록 오류 : {}", e.getMessage(), e);
        }

        return internalSubSubjects;
    }

    // 내신 세부 교과목 상세보기
    public InternalSubSubjectDto getInternalSubSubject(int internalSubSubjectId) {
        InternalSubSubjectDto internalSubSubject = null;
        
        try {
            internalSubSubject = sqlSession.selectOne("internalSubSubjectMapper.getInternalSubSubject", internalSubSubjectId);
        } catch (DataAccessException e) {
            logger.error("내신 세부 교과목 상세 오류 : {}", e.getMessage(), e);
        }

        return internalSubSubject;
    }

    // 내신 세부 교과목 수정
    public int updateInternalSubSubject(InternalSubSubjectDto internalSubSubject) {
        int result = -1;

        try {
            result = sqlSession.update("internalSubSubjectMapper.updateInternalSubSubject", internalSubSubject);
        } catch (DataAccessException e) {
            logger.error("내신 세부 교과목 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 내신 세부 교과목 삭제
    public int deleteInternalSubSubject(int internalSubSubjectId) {
        int result = -1;

        try {
            result = sqlSession.delete("internalSubSubjectMapper.deleteInternalSubSubject", internalSubSubjectId);
        } catch (DataAccessException e) {
            logger.error("내신 세부 교과목 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 내신 세부 교과목 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = 0;

        try {
            totalCount = sqlSession.selectOne("internalSubSubjectMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 내신 세부 교과목 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
