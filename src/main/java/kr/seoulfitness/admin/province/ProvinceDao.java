package kr.seoulfitness.admin.province;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class ProvinceDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(ProvinceDao.class);

    // 시/도 등록
    public int insertProvince(ProvinceDto province) {
        int result = -1;

        try {
            result = sqlSession.insert("provinceMapper.insertProvince", province);
        } catch (DataAccessException e) {
            logger.error("시/도 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 시/도 목록
    public List<ProvinceDto> getProvinces(Map<String, Object> params) {
        List<ProvinceDto> provinces = null;

        try {
            provinces = sqlSession.selectList("provinceMapper.getProvinces", params);
        } catch (DataAccessException e) {
            logger.error("시/도 목록 오류 : {}", e.getMessage(), e);
        }

        return provinces;
    }

    // 시/도 상세보기
    public ProvinceDto getProvince(int provinceId) {
        ProvinceDto province = null;
        
        try {
            province = sqlSession.selectOne("provinceMapper.getProvince", provinceId);
        } catch (DataAccessException e) {
            logger.error("시/도 상세 오류 : {}", e.getMessage(), e);
        }

        return province;
    }

    // 시/도 수정
    public int updateProvince(ProvinceDto province) {
        int result = -1;

        try {
            result = sqlSession.update("provinceMapper.updateProvince", province);
        } catch (DataAccessException e) {
            logger.error("시/도 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 시/도 삭제
    public int deleteProvince(int provinceId) {
        int result = -1;

        try {
            result = sqlSession.delete("provinceMapper.deleteProvince", provinceId);
        } catch (DataAccessException e) {
            logger.error("시/도 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 시/도 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("provinceMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 시/도 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
