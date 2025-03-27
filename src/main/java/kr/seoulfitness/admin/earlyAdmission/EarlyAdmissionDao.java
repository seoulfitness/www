package kr.seoulfitness.admin.earlyAdmission;

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

    // 수시 모집 등록
    public EarlyAdmissionDto insertEarlyAdmission(EarlyAdmissionDto earlyAdmissionDto) {
        try {
            sqlSession.insert("earlyAdmissionMapper.insertEarlyAdmission", earlyAdmissionDto);
            return earlyAdmissionDto;
        } catch (DataAccessException e) {
            logger.error("수시 모집 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 수시 모집 상세보기
    public EarlyAdmissionDto getEarlyAdmission(Map<String, Object> params) {
        EarlyAdmissionDto earlyAdmission = null;
        
        try {
            earlyAdmission = sqlSession.selectOne("earlyAdmissionMapper.getEarlyAdmission", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 상세 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmission;
    }

    // 수시 모집 수정
    public int updateEarlyAdmission(EarlyAdmissionDto earlyAdmissionDto) {
        int result = -1;

        try {
            result = sqlSession.update("earlyAdmissionMapper.updateEarlyAdmission", earlyAdmissionDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
