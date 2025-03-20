package kr.seoulfitness.admin.user;

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
public class UserDao {    
    @Autowired
    private SqlSession sqlSession;

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    // 사용자 등록
    public int create(UserDto user) {
        int result = -1;

        try {
            result = sqlSession.insert("userMapper.create", user);
        } catch (DataAccessException e) {
            logger.error("사용자 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 사용자 목록
    public List<UserDto> list(int offset, int listCountPerPage, String keyword, String role) {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("listCountPerPage", listCountPerPage);
        params.put("keyword", keyword);
        params.put("role", role);

        List<UserDto> users = null;

        try {
            users = sqlSession.selectList("userMapper.list", params);
        } catch (DataAccessException e) {
            logger.error("사용자 목록 오류 : {}", e.getMessage(), e);
        }

        return users;
    }

    // 사용자 상세보기
    public UserDto read(UserDto user) {
        try {
            user = sqlSession.selectOne("userMapper.read", user);
        } catch (DataAccessException e) {
            logger.error("사용자 상세 오류 : {}", e.getMessage(), e);
        }

        return user;
    }

    // 사용자 수정
    public int update(UserDto user) {
        int result = -1;

        try {
            result = sqlSession.update("userMapper.update", user);
        } catch (DataAccessException e) {
            logger.error("사용자 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 사용자 삭제
    public int delete(int id) {
        int result = -1;

        try {
            result = sqlSession.delete("userMapper.delete", id);
        } catch (DataAccessException e) {
            logger.error("사용자 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 사용자 수
    public int totalCount(String keyword, String role) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("role", role);

        int totalCount = -1;

        try {
            totalCount = sqlSession.selectOne("userMapper.totalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 사용자 수 오류 : {}", e.getMessage(), e);
        }
        return totalCount;
    }
}
