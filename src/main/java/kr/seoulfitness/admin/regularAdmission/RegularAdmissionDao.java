package kr.seoulfitness.admin.regularAdmission;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class RegularAdmissionDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(RegularAdmissionDao.class);

    // 수시 모집 등록
    public RegularAdmissionDto insertRegularAdmission(RegularAdmissionDto regularAdmissionDto) {
        try {
            sqlSession.insert("regularAdmissionMapper.insertRegularAdmission", regularAdmissionDto);
            return regularAdmissionDto;
        } catch (DataAccessException e) {
            logger.error("수시 모집 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 수시 모집 상세보기
    public RegularAdmissionDto getRegularAdmission(Map<String, Object> params) {
        RegularAdmissionDto regularAdmission = null;
        
        try {
            regularAdmission = sqlSession.selectOne("regularAdmissionMapper.getRegularAdmission", params);
        } catch (DataAccessException e) {
            logger.error("수시 모집 상세 오류 : {}", e.getMessage(), e);
        }

        return regularAdmission;
    }

    // 수시 모집 수정
    public int updateRegularAdmission(RegularAdmissionDto regularAdmissionDto) {
        int result = -1;

        try {
            result = sqlSession.update("regularAdmissionMapper.updateRegularAdmission", regularAdmissionDto);
        } catch (DataAccessException e) {
            logger.error("수시 모집 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
