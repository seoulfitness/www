package kr.seoulfitness.admin.admission;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class AdmissionDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(AdmissionDao.class);

    // 입시 요강 등록
    public int insertAdmission(AdmissionDto admission) {
        int result = -1;

        try {
            result = sqlSession.insert("admissionMapper.insertAdmission", admission);
        } catch (DataAccessException e) {
            logger.error("입시 요강 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 입시 요강 목록
    public List<AdmissionDto> getAdmissions(Map<String, Object> params) {
        List<AdmissionDto> admissions = null;

        try {
            admissions = sqlSession.selectList("admissionMapper.getAdmissions", params);
        } catch (DataAccessException e) {
            logger.error("입시 요강 목록 오류 : {}", e.getMessage(), e);
        }

        return admissions;
    }

    // 입시 요강 상세보기
    public AdmissionDto getAdmission(int admissionId) {
        AdmissionDto admission = null;
        
        try {
            admission = sqlSession.selectOne("admissionMapper.getAdmission", admissionId);
        } catch (DataAccessException e) {
            logger.error("입시 요강 상세 오류 : {}", e.getMessage(), e);
        }

        return admission;
    }

    // 입시 요강 수정
    public int updateAdmission(AdmissionDto admission) {
        int result = -1;

        try {
            result = sqlSession.update("admissionMapper.updateAdmission", admission);
        } catch (DataAccessException e) {
            logger.error("입시 요강 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 입시 요강 삭제
    public int deleteAdmission(int admissionId) {
        int result = -1;

        try {
            result = sqlSession.delete("admissionMapper.deleteAdmission", admissionId);
        } catch (DataAccessException e) {
            logger.error("입시 요강 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 입시 요강 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = 0;

        try {
            totalCount = sqlSession.selectOne("admissionMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 입시 요강 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
