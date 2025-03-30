package kr.seoulfitness.admin.regularAdmissionPhysical;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class RegularAdmissionPhysicalDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(RegularAdmissionPhysicalDao.class);

    // 정시 모집 실기 정보 등록
    public RegularAdmissionPhysicalDto insertRegularAdmissionPhysical(RegularAdmissionPhysicalDto regularAdmissionPhysicalDto) {
        try {
            sqlSession.insert("regularAdmissionPhysicalMapper.insertRegularAdmissionPhysical", regularAdmissionPhysicalDto);
            return regularAdmissionPhysicalDto;
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 정시 모집 실기 정보 상세보기
    public RegularAdmissionPhysicalDto getRegularAdmissionPhysical(Map<String, Object> params) {
        RegularAdmissionPhysicalDto regularAdmissionPhysical = null;
        
        try {
            regularAdmissionPhysical = sqlSession.selectOne("regularAdmissionPhysicalMapper.getRegularAdmissionPhysical", params);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return regularAdmissionPhysical;
    }

    // 정시 모집 실기 정보 수정
    public int updateRegularAdmissionPhysical(RegularAdmissionPhysicalDto regularAdmissionPhysicalDto) {
        int result = -1;

        try {
            result = sqlSession.update("regularAdmissionPhysicalMapper.updateRegularAdmissionPhysical", regularAdmissionPhysicalDto);
        } catch (DataAccessException e) {
            logger.error("정시 모집 실기 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
