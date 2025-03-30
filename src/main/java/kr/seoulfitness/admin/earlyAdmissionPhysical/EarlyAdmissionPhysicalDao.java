package kr.seoulfitness.admin.earlyAdmissionPhysical;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class EarlyAdmissionPhysicalDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(EarlyAdmissionPhysicalDao.class);

    // 수시 모집 실기 정보 등록
    public EarlyAdmissionPhysicalDto insertEarlyAdmissionPhysical(EarlyAdmissionPhysicalDto earlyAdmissionPhysicalDto) {
        try {
            sqlSession.insert("earlyAdmissionPhysicalMapper.insertEarlyAdmissionPhysical", earlyAdmissionPhysicalDto);
            return earlyAdmissionPhysicalDto;
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 수시 모집 실기 정보 상세보기
    public EarlyAdmissionPhysicalDto getEarlyAdmissionPhysical(Map<String, Object> params) {
        EarlyAdmissionPhysicalDto earlyAdmissionPhysical = null;
        
        try {
            earlyAdmissionPhysical = sqlSession.selectOne("earlyAdmissionPhysicalMapper.getEarlyAdmissionPhysical", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionPhysical;
    }

    // 수시 모집 실기 정보 수정
    public int updateEarlyAdmissionPhysical(EarlyAdmissionPhysicalDto earlyAdmissionPhysicalDto) {
        int result = -1;

        try {
            result = sqlSession.update("earlyAdmissionPhysicalMapper.updateEarlyAdmissionPhysical", earlyAdmissionPhysicalDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
