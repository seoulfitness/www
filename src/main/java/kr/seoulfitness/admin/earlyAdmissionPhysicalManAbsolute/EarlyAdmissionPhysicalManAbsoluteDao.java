package kr.seoulfitness.admin.earlyAdmissionPhysicalManAbsolute;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class EarlyAdmissionPhysicalManAbsoluteDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(EarlyAdmissionPhysicalManAbsoluteDao.class);

    // 수시 모집 실기 남자 절대평가 점수 정보 등록
    public EarlyAdmissionPhysicalManAbsoluteDto insertEarlyAdmissionPhysicalManAbsolute(EarlyAdmissionPhysicalManAbsoluteDto earlyAdmissionPhysicalManAbsoluteDto) {
        try {
            sqlSession.insert("earlyAdmissionPhysicalManAbsoluteMapper.insertEarlyAdmissionPhysicalManAbsolute", earlyAdmissionPhysicalManAbsoluteDto);
            return earlyAdmissionPhysicalManAbsoluteDto;
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 절대평가 점수 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 수시 모집 실기 남자 절대평가 점수 목록
    public List<EarlyAdmissionPhysicalManAbsoluteDto> getEarlyAdmissionPhysicalManAbsoluteList(Map<String, Object> params) {
        List<EarlyAdmissionPhysicalManAbsoluteDto> earlyAdmissionPhysicalManAbsoluteList = null;

        try {
            earlyAdmissionPhysicalManAbsoluteList = sqlSession.selectList("earlyAdmissionPhysicalManAbsoluteMapper.getEarlyAdmissionPhysicalManAbsoluteList", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 절대평가 점수 목록 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionPhysicalManAbsoluteList;
    }

    // 수시 모집 실기 남자 절대평가 점수 정보 상세
    public EarlyAdmissionPhysicalManAbsoluteDto getEarlyAdmissionPhysicalManAbsolute(Map<String, Object> params) {
        EarlyAdmissionPhysicalManAbsoluteDto earlyAdmissionPhysicalManAbsolute = null;
        
        try {
            earlyAdmissionPhysicalManAbsolute = sqlSession.selectOne("earlyAdmissionPhysicalManAbsoluteMapper.getEarlyAdmissionPhysicalManAbsolute", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 절대평가 점수 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return earlyAdmissionPhysicalManAbsolute;
    }

    // 수시 모집 실기 남자 절대평가 점수 정보 수정
    public int updateEarlyAdmissionPhysicalManAbsolute(EarlyAdmissionPhysicalManAbsoluteDto earlyAdmissionPhysicalManAbsoluteDto) {
        int result = -1;

        try {
            result = sqlSession.update("earlyAdmissionPhysicalManAbsoluteMapper.updateEarlyAdmissionPhysicalManAbsolute", earlyAdmissionPhysicalManAbsoluteDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 절대평가 점수 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 수시 모집 실기 남자 절대평가 점수 정보 삭제
    public int deleteEarlyAdmissionPhysicalManAbsolute(EarlyAdmissionPhysicalManAbsoluteDto earlyAdmissionPhysicalManAbsoluteDto) {
        int result = -1;

        try {
            result = sqlSession.delete("earlyAdmissionPhysicalManAbsoluteMapper.deleteEarlyAdmissionPhysicalManAbsolute", earlyAdmissionPhysicalManAbsoluteDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 실기 남자 절대평가 점수 정보 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
