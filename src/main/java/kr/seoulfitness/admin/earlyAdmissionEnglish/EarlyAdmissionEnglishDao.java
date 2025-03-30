package kr.seoulfitness.admin.earlyAdmissionEnglish;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class EarlyAdmissionEnglishDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(EarlyAdmissionEnglishDao.class);

    // 수시 모집 영어 정보 등록
    public EarlyAdmissionEnglishDto insertEarlyAdmissionEnglish(EarlyAdmissionEnglishDto earlyAdmissionEnglishDto) {
        try {
            sqlSession.insert("earlyAdmissionEnglishMapper.insertEarlyAdmissionEnglish", earlyAdmissionEnglishDto);
            return earlyAdmissionEnglishDto;
        } catch (DataAccessException e) {
            logger.error("수시 모집 영어 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 수시 모집 수능 정보 상세보기
    public EarlyAdmissionEnglishDto getEarlyAdmissionEnglish(Map<String, Object> params) {
        EarlyAdmissionEnglishDto earlyAdmissionEnglish = null;
        
        try {
            earlyAdmissionEnglish = sqlSession.selectOne("earlyAdmissionEnglishMapper.getEarlyAdmissionEnglish", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 영어 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionEnglish;
    }

    // 수시 모집 영어 정보 수정
    public int updateEarlyAdmissionEnglish(EarlyAdmissionEnglishDto earlyAdmissionEnglishDto) {
        int result = -1;

        try {
            result = sqlSession.update("earlyAdmissionEnglishMapper.updateEarlyAdmissionEnglish", earlyAdmissionEnglishDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 영어 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
