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
    public int create(InternalSubSubjectDto internalSubSubject) {
        int result = -1;

        try {
            result = sqlSession.insert("internalSubSubjectMapper.create", internalSubSubject);
        } catch (DataAccessException e) {
            logger.error("내신 세부 교과목 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 내신 세부 교과목 목록
    public List<InternalSubSubjectDto> list(Map<String, Object> params) {
        List<InternalSubSubjectDto> internalSubSubjects = null;

        try {
            internalSubSubjects = sqlSession.selectList("internalSubSubjectMapper.list", params);
        } catch (DataAccessException e) {
            logger.error("내신 세부 교과목 목록 오류 : {}", e.getMessage(), e);
        }

        return internalSubSubjects;
    }

    // 내신 세부 교과목 상세보기
    public InternalSubSubjectDto read(InternalSubSubjectDto internalSubSubject) {
        InternalSubSubjectDto result = null;
        
        try {
            result = sqlSession.selectOne("internalSubSubjectMapper.read", internalSubSubject);
        } catch (DataAccessException e) {
            logger.error("내신 세부 교과목 상세 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 내신 세부 교과목 수정
    public int update(InternalSubSubjectDto internalSubSubject) {
        int result = -1;

        try {
            result = sqlSession.update("internalSubSubjectMapper.update", internalSubSubject);
        } catch (DataAccessException e) {
            logger.error("내신 세부 교과목 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 내신 세부 교과목 삭제
    public int delete(int internalSubSubjectId) {
        int result = -1;

        try {
            result = sqlSession.delete("internalSubSubjectMapper.delete", internalSubSubjectId);
        } catch (DataAccessException e) {
            logger.error("내신 세부 교과목 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 내신 세부 교과목 수
    public int totalCount(Map<String, Object> params) {
        int totalCount = 0;

        try {
            totalCount = sqlSession.selectOne("internalSubSubjectMapper.totalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 내신 세부 교과목 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
