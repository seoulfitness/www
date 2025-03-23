package kr.seoulfitness.admin.district;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class DistrictDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(DistrictDao.class);

    // 구/군 등록
    public int insertDistrict(DistrictDto district) {
        int result = -1;

        try {
            result = sqlSession.insert("districtMapper.insertDistrict", district);
        } catch (DataAccessException e) {
            logger.error("구/군 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 구/군 목록
    public List<DistrictDto> getDistricts(Map<String, Object> params) {
        List<DistrictDto> districts = null;

        try {
            districts = sqlSession.selectList("districtMapper.getDistricts", params);
        } catch (DataAccessException e) {
            logger.error("구/군 목록 오류 : {}", e.getMessage(), e);
        }

        return districts;
    }

    // 구/군 상세보기
    public DistrictDto getDistrict(int districtId) {
        DistrictDto district = null;
        
        try {
            district = sqlSession.selectOne("districtMapper.getDistrict", districtId);
        } catch (DataAccessException e) {
            logger.error("구/군 상세 오류 : {}", e.getMessage(), e);
        }

        return district;
    }

    // 구/군 수정
    public int updateDistrict(DistrictDto district) {
        int result = -1;

        try {
            result = sqlSession.update("districtMapper.updateDistrict", district);
        } catch (DataAccessException e) {
            logger.error("구/군 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 구/군 삭제
    public int deleteDistrict(int districtId) {
        int result = -1;

        try {
            result = sqlSession.delete("districtMapper.deleteDistrict", districtId);
        } catch (DataAccessException e) {
            logger.error("구/군 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 구/군 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("districtMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 구/군 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
