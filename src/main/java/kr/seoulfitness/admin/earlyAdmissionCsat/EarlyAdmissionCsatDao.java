package kr.seoulfitness.admin.earlyAdmissionCsat;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class EarlyAdmissionCsatDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(EarlyAdmissionCsatDao.class);

    // 수시 모집 수능 정보 등록
    public EarlyAdmissionCsatDto insertEarlyAdmissionCsat(EarlyAdmissionCsatDto earlyAdmissionCsatDto) {
        try {
            sqlSession.insert("earlyAdmissionCsatMapper.insertEarlyAdmissionCsat", earlyAdmissionCsatDto);
            return earlyAdmissionCsatDto;
        } catch (DataAccessException e) {
            logger.error("수시 모집 수능 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 수시 모집 수능 정보 상세보기
    public EarlyAdmissionCsatDto getEarlyAdmissionCsat(Map<String, Object> params) {
        EarlyAdmissionCsatDto earlyAdmissionCsat = null;
        
        try {
            earlyAdmissionCsat = sqlSession.selectOne("earlyAdmissionCsatMapper.getEarlyAdmissionCsat", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 수능 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionCsat;
    }

    // 수시 모집 수능 정보 수정
    public int updateEarlyAdmissionCsat(EarlyAdmissionCsatDto earlyAdmissionCsatDto) {
        int result = -1;

        try {
            result = sqlSession.update("earlyAdmissionCsatMapper.updateEarlyAdmissionCsat", earlyAdmissionCsatDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 수능 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
