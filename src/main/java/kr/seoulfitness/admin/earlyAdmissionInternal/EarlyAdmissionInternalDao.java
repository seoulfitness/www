package kr.seoulfitness.admin.earlyAdmissionInternal;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class EarlyAdmissionInternalDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(EarlyAdmissionInternalDao.class);

    // 수시 모집 내부 정보 등록
    public EarlyAdmissionInternalDto insertEarlyAdmissionInternal(EarlyAdmissionInternalDto earlyAdmissionInternalDto) {
        try {
            sqlSession.insert("earlyAdmissionInternalMapper.insertEarlyAdmissionInternal", earlyAdmissionInternalDto);
            return earlyAdmissionInternalDto;
        } catch (DataAccessException e) {
            logger.error("수시 모집 내부 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 수시 모집 내부 정보 상세보기
    public EarlyAdmissionInternalDto getEarlyAdmissionInternal(Map<String, Object> params) {
        EarlyAdmissionInternalDto earlyAdmissionInternal = null;
        
        try {
            earlyAdmissionInternal = sqlSession.selectOne("earlyAdmissionInternalMapper.getEarlyAdmissionInternal", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 내부 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionInternal;
    }

    // 수시 모집 내부 정보 수정
    public int updateEarlyAdmissionInternal(EarlyAdmissionInternalDto earlyAdmissionInternalDto) {
        int result = -1;

        try {
            result = sqlSession.update("earlyAdmissionInternalMapper.updateEarlyAdmissionInternal", earlyAdmissionInternalDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 내부 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
