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
    public int create(InternalSubjectDto internalSubject) {
        int result = -1;

        try {
            result = sqlSession.insert("internalSubjectMapper.create", internalSubject);
        } catch (DataAccessException e) {
            logger.error("내신 교과목 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 내신 교과목 목록
    public List<InternalSubjectDto> list(Map<String, Object> params) {
        List<InternalSubjectDto> internalSubjects = null;

        try {
            internalSubjects = sqlSession.selectList("internalSubjectMapper.list", params);
        } catch (DataAccessException e) {
            logger.error("내신 교과목 목록 오류 : {}", e.getMessage(), e);
        }

        return internalSubjects;
    }

    // 내신 교과목 상세보기
    public InternalSubjectDto read(InternalSubjectDto internalSubject) {
        InternalSubjectDto result = null;

        try {
            result = sqlSession.selectOne("internalSubjectMapper.read", internalSubject);
        } catch (DataAccessException e) {
            logger.error("내신 교과목 상세 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 내신 교과목 수정
    public int update(InternalSubjectDto internalSubject) {
        int result = -1;

        try {
            result = sqlSession.update("internalSubjectMapper.update", internalSubject);
        } catch (DataAccessException e) {
            logger.error("내신 교과목 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 내신 교과목 삭제
    public int delete(int internalSubjectId) {
        int result = -1;

        try {
            result = sqlSession.delete("internalSubjectMapper.delete", internalSubjectId);
        } catch (DataAccessException e) {
            logger.error("내신 교과목 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 내신 교과목 수
    public int totalCount(Map<String, Object> params) {
        int totalCount = 0;

        try {
            totalCount = sqlSession.selectOne("internalSubjectMapper.totalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 내신 교과목 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
