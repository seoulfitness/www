package kr.seoulfitness.admin.physicalSubject;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class PhysicalSubjectDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(PhysicalSubjectDao.class);

    // 실기 교과목 등록
    public int insertPhysicalSubject(PhysicalSubjectDto physicalSubject) {
        int result = -1;

        try {
            result = sqlSession.insert("physicalSubjectMapper.insertPhysicalSubject", physicalSubject);
        } catch (DataAccessException e) {
            logger.error("실기 교과목 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 실기 교과목 목록
    public List<PhysicalSubjectDto> getPhysicalSubjects(Map<String, Object> params) {
        List<PhysicalSubjectDto> physicalSubjects = null;

        try {
            physicalSubjects = sqlSession.selectList("physicalSubjectMapper.getPhysicalSubjects", params);
        } catch (DataAccessException e) {
            logger.error("실기 교과목 목록 오류 : {}", e.getMessage(), e);
        }

        return physicalSubjects;
    }

    // 실기 교과목 상세보기
    public PhysicalSubjectDto getPhysicalSubject(int physicalSubjectId) {
        PhysicalSubjectDto physicalSubject = null;
        
        try {
            physicalSubject = sqlSession.selectOne("physicalSubjectMapper.getPhysicalSubject", physicalSubjectId);
        } catch (DataAccessException e) {
            logger.error("실기 교과목 상세 오류 : {}", e.getMessage(), e);
        }

        return physicalSubject;
    }

    // 실기 교과목 수정
    public int updatePhysicalSubject(PhysicalSubjectDto physicalSubject) {
        int result = -1;

        try {
            result = sqlSession.update("physicalSubjectMapper.updatePhysicalSubject", physicalSubject);
        } catch (DataAccessException e) {
            logger.error("실기 교과목 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 실기 교과목 삭제
    public int deletePhysicalSubject(int physicalSubjectId) {
        int result = -1;

        try {
            result = sqlSession.delete("physicalSubjectMapper.deletePhysicalSubject", physicalSubjectId);
        } catch (DataAccessException e) {
            logger.error("실기 교과목 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 실기 교과목 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("physicalSubjectMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 실기 교과목 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
