package kr.seoulfitness.admin.regularAdmissionEnglish;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class RegularAdmissionEnglishDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(RegularAdmissionEnglishDao.class);

    // 정시 모집 영어 정보 등록
    public RegularAdmissionEnglishDto insertRegularAdmissionEnglish(RegularAdmissionEnglishDto regularAdmissionEnglishDto) {
        try {
            sqlSession.insert("regularAdmissionEnglishMapper.insertRegularAdmissionEnglish", regularAdmissionEnglishDto);
            return regularAdmissionEnglishDto;
        } catch (DataAccessException e) {
            logger.error("정시 모집 영어 정보 등록 오류 : {}", e.getMessage(), e);
            return null;
        }
    }

    // 정시 모집 영어 정보 상세보기
    public RegularAdmissionEnglishDto getRegularAdmissionEnglish(Map<String, Object> params) {
        RegularAdmissionEnglishDto regularAdmissionEnglish = null;
        
        try {
            regularAdmissionEnglish = sqlSession.selectOne("regularAdmissionEnglishMapper.getRegularAdmissionEnglish", params);
        } catch (DataAccessException e) {
            logger.error("정시 모집 영어 정보 상세 오류 : {}", e.getMessage(), e);
        }

        return regularAdmissionEnglish;
    }

    // 정시 모집 영어 정보 수정
    public int updateRegularAdmissionEnglish(RegularAdmissionEnglishDto regularAdmissionEnglishDto) {
        int result = -1;

        try {
            result = sqlSession.update("regularAdmissionEnglishMapper.updateRegularAdmissionEnglish", regularAdmissionEnglishDto);
        } catch (DataAccessException e) {
            logger.error("정시 모집 영어 정보 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
}
