package kr.seoulfitness.admin.earlyAdmissionHistory;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class EarlyAdmissionHistoryDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(EarlyAdmissionHistoryDao.class);

    // 수시 모집 한국사 정보 등록
    public EarlyAdmissionHistoryDto insertEarlyAdmissionHistory(EarlyAdmissionHistoryDto earlyAdmissionHistoryDto) {
        try {
            sqlSession.insert("earlyAdmissionHistoryMapper.insertEarlyAdmissionHistory", earlyAdmissionHistoryDto);
            return earlyAdmissionHistoryDto;
        } catch (DataAccessException e) {
            logger.error("수시 모집 한국사 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 수시 모집 한국사 정보 상세보기
    public EarlyAdmissionHistoryDto getEarlyAdmissionHistory(Map<String, Object> params) {
        EarlyAdmissionHistoryDto earlyAdmissionHistory = null;
        
        try {
            earlyAdmissionHistory = sqlSession.selectOne("earlyAdmissionHistoryMapper.getEarlyAdmissionHistory", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 한국사 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionHistory;
    }

    // 수시 모집 한국사 정보 수정
    public int updateEarlyAdmissionHistory(EarlyAdmissionHistoryDto earlyAdmissionHistoryDto) {
        int result = -1;

        try {
            result = sqlSession.update("earlyAdmissionHistoryMapper.updateEarlyAdmissionHistory", earlyAdmissionHistoryDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 한국사 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
