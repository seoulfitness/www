package kr.seoulfitness.admin.regularAdmissionCsat;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class RegularAdmissionCsatDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(RegularAdmissionCsatDao.class);

    // 정시 모집 수능 정보 등록
    public RegularAdmissionCsatDto insertRegularAdmissionCsat(RegularAdmissionCsatDto regularAdmissionCsatDto) {
        try {
            sqlSession.insert("regularAdmissionCsatMapper.insertRegularAdmissionCsat", regularAdmissionCsatDto);
            return regularAdmissionCsatDto;
        } catch (DataAccessException e) {
            logger.error("정시 모집 수능 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 정시 모집 수능 정보 상세보기
    public RegularAdmissionCsatDto getRegularAdmissionCsat(Map<String, Object> params) {
        RegularAdmissionCsatDto regularAdmissionCsat = null;
        
        try {
            regularAdmissionCsat = sqlSession.selectOne("regularAdmissionCsatMapper.getRegularAdmissionCsat", params);
        } catch (DataAccessException e) {
            logger.error("정시 모집 수능 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return regularAdmissionCsat;
    }

    // 정시 모집 수능 정보 수정
    public int updateRegularAdmissionCsat(RegularAdmissionCsatDto regularAdmissionCsatDto) {
        int result = -1;

        try {
            result = sqlSession.update("regularAdmissionCsatMapper.updateRegularAdmissionCsat", regularAdmissionCsatDto);
        } catch (DataAccessException e) {
            logger.error("정시 모집 수능 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
