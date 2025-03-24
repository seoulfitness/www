package kr.seoulfitness.admin.earlyAdmission;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class EarlyAdmissionDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(EarlyAdmissionDao.class);

    /**
     * 수시 모집 등록
     * @param earlyAdmissionDto 수시 모집 정보
     * @return 등록된 행 수
     */
    public int insertEarlyAdmission(EarlyAdmissionDto earlyAdmissionDto) {
        int result = -1;

        try {
            result = sqlSession.insert("earlyAdmissionMapper.insertEarlyAdmission", earlyAdmissionDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    /**
     * 수시 모집 목록 조회
     * @param params 검색 파라미터
     * @return 수시 모집 목록
     */
    public List<EarlyAdmissionDto> getEarlyAdmissions(Map<String, Object> params) {
        List<EarlyAdmissionDto> earlyAdmissions = null;

        try {
            earlyAdmissions = sqlSession.selectList("earlyAdmissionMapper.getEarlyAdmissions", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 목록 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissions;
    }

    /**
     * 수시 모집 상세보기
     * @param earlyAdmissionId 수시 모집 아이디
     * @return 수시 모집 정보
     */
    public EarlyAdmissionDto getEarlyAdmission(Map<String, Object> params) {
        EarlyAdmissionDto earlyAdmission = null;
        
        try {
            earlyAdmission = sqlSession.selectOne("earlyAdmissionMapper.getEarlyAdmission", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 상세 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmission;
    }

    /**
     * 수시 모집 수정
     * @param earlyAdmissionDto 수시 모집 정보
     * @return 수정된 행 수
     */
    public int updateEarlyAdmission(EarlyAdmissionDto earlyAdmissionDto) {
        int result = -1;

        try {
            result = sqlSession.update("earlyAdmissionMapper.updateEarlyAdmission", earlyAdmissionDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    /**
     * 수시 모집 삭제
     * @param earlyAdmissionId 수시 모집 아이디
     * @return 삭제된 행 수
     */
    public int deleteEarlyAdmission(int earlyAdmissionId) {
        int result = -1;

        try {
            result = sqlSession.delete("earlyAdmissionMapper.deleteEarlyAdmission", earlyAdmissionId);
        } catch (DataAccessException e) {
            logger.error("수시 모집 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    /**
     * 수시 모집 수 조회
     * @param params 검색 파라미터
     * @return 수시 모집 수
     */
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("earlyAdmissionMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 수시 모집 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
