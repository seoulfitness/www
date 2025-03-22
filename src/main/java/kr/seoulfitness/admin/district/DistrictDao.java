package kr.seoulfitness.admin.district;

import java.util.HashMap;
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
    public int create(DistrictDto district) {
        int result = -1;

        try {
            result = sqlSession.insert("districtMapper.create", district);
        } catch (DataAccessException e) {
            logger.error("구/군 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 구/군 목록
    public List<DistrictDto> list(int offset, int listCountPerPage, String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("listCountPerPage", listCountPerPage);
        params.put("keyword", keyword);

        List<DistrictDto> districts = null;

        try {
            districts = sqlSession.selectList("districtMapper.list", params);
        } catch (DataAccessException e) {
            logger.error("구/군 목록 오류 : {}", e.getMessage(), e);
        }

        return districts;
    }

    // 구/군 상세보기
    public DistrictDto read(int districtId) {
        DistrictDto district = null;
        
        try {
            district = sqlSession.selectOne("districtMapper.read", districtId);
        } catch (DataAccessException e) {
            logger.error("구/군 상세 오류 : {}", e.getMessage(), e);
        }

        return district;
    }

    // 구/군 수정
    public int update(DistrictDto district) {
        int result = -1;

        try {
            result = sqlSession.update("districtMapper.update", district);
        } catch (DataAccessException e) {
            logger.error("구/군 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 구/군 삭제
    public int delete(int districtId) {
        int result = -1;

        try {
            result = sqlSession.delete("districtMapper.delete", districtId);
        } catch (DataAccessException e) {
            logger.error("구/군 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 구/군 수
    public int totalCount(String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);

        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("districtMapper.totalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 구/군 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
