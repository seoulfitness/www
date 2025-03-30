package kr.seoulfitness.admin.regularAdmissionInternal;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class RegularAdmissionInternalDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(RegularAdmissionInternalDao.class);

    // 정시 모집 내부 정보 등록
    public RegularAdmissionInternalDto insertRegularAdmissionInternal(RegularAdmissionInternalDto regularAdmissionInternalDto) {
        try {
            sqlSession.insert("regularAdmissionInternalMapper.insertRegularAdmissionInternal", regularAdmissionInternalDto);
            return regularAdmissionInternalDto;
        } catch (DataAccessException e) {
            logger.error("정시 모집 내부 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 정시 모집 내부 정보 상세보기
    public RegularAdmissionInternalDto getRegularAdmissionInternal(Map<String, Object> params) {
        RegularAdmissionInternalDto regularAdmissionInternal = null;
        
        try {
            regularAdmissionInternal = sqlSession.selectOne("regularAdmissionInternalMapper.getRegularAdmissionInternal", params);
        } catch (DataAccessException e) {
            logger.error("정시 모집 내부 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return regularAdmissionInternal;
    }

    // 정시 모집 내부 정보 수정
    public int updateRegularAdmissionInternal(RegularAdmissionInternalDto regularAdmissionInternalDto) {
        int result = -1;

        try {
            result = sqlSession.update("regularAdmissionInternalMapper.updateRegularAdmissionInternal", regularAdmissionInternalDto);
        } catch (DataAccessException e) {
            logger.error("정시 모집 내부 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
