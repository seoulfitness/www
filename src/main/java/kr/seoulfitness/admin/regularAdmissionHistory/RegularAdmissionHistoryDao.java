package kr.seoulfitness.admin.regularAdmissionHistory;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class RegularAdmissionHistoryDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(RegularAdmissionHistoryDao.class);

    // 정시 모집 한국사 정보 등록
    public RegularAdmissionHistoryDto insertRegularAdmissionHistory(RegularAdmissionHistoryDto regularAdmissionHistoryDto) {
        try {
            sqlSession.insert("regularAdmissionHistoryMapper.insertRegularAdmissionHistory", regularAdmissionHistoryDto);
            return regularAdmissionHistoryDto;
        } catch (DataAccessException e) {
            logger.error("정시 모집 한국사 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 정시 모집 한국사 정보 상세보기
    public RegularAdmissionHistoryDto getRegularAdmissionHistory(Map<String, Object> params) {
        RegularAdmissionHistoryDto regularAdmissionHistory = null;
        
        try {
            regularAdmissionHistory = sqlSession.selectOne("regularAdmissionHistoryMapper.getRegularAdmissionHistory", params);
        } catch (DataAccessException e) {
            logger.error("정시 모집 한국사 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return regularAdmissionHistory;
    }

    // 정시 모집 한국사 정보 수정
    public int updateRegularAdmissionHistory(RegularAdmissionHistoryDto regularAdmissionHistoryDto) {
        int result = -1;

        try {
            result = sqlSession.update("regularAdmissionHistoryMapper.updateRegularAdmissionHistory", regularAdmissionHistoryDto);
        } catch (DataAccessException e) {
            logger.error("정시 모집 한국사 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
