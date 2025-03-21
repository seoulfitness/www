package kr.seoulfitness.admin.province;

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
public class ProvinceDao {
    @Autowired
    private SqlSession sqlSession;
    
    private static final Logger logger = LoggerFactory.getLogger(ProvinceDao.class);

    // 시/도 등록
    public int create(ProvinceDto province) {
        int result = -1;

        try {
            result = sqlSession.insert("provinceMapper.create", province);
        } catch (DataAccessException e) {
            logger.error("시/도 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 시/도 목록
    public List<ProvinceDto> list(int offset, int listCountPerPage, String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("listCountPerPage", listCountPerPage);
        params.put("keyword", keyword);

        List<ProvinceDto> provinces = null;

        try {
            provinces = sqlSession.selectList("provinceMapper.list", params);
        } catch (DataAccessException e) {
            logger.error("시/도 목록 오류 : {}", e.getMessage(), e);
        }

        return provinces;
    }

    // 시/도 상세보기
    public ProvinceDto read(int provinceId) {
        ProvinceDto province = null;
        
        try {
            province = sqlSession.selectOne("provinceMapper.read", provinceId);
        } catch (DataAccessException e) {
            logger.error("시/도 상세 오류 : {}", e.getMessage(), e);
        }

        return province;
    }

    // 시/도 수정
    public int update(ProvinceDto province) {
        int result = -1;

        try {
            result = sqlSession.update("provinceMapper.update", province);
        } catch (DataAccessException e) {
            logger.error("시/도 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 시/도 삭제
    public int delete(int provinceId) {
        int result = -1;

        try {
            result = sqlSession.delete("provinceMapper.delete", provinceId);
        } catch (DataAccessException e) {
            logger.error("시/도 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 시/도 수
    public int totalCount(String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);

        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("provinceMapper.totalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 시/도 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
