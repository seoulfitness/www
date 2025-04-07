package kr.seoulfitness.admin.user;

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
    public int insertUser(UserDto user) {
        int result = -1;

        try {
            result = sqlSession.insert("userMapper.insertUser", user);
        } catch (DataAccessException e) {
            logger.error("사용자 등록 오류 : {}", e.getMessage(), e);
        }

        return result;   
    }

    // 사용자 목록
    public List<UserDto> getUsers(Map<String, Object> params) {
        List<UserDto> users = null;

        try {
            users = sqlSession.selectList("userMapper.getUsers", params);
        } catch (DataAccessException e) {
            logger.error("사용자 목록 오류 : {}", e.getMessage(), e);
        }

        return users;
    }

    // 사용자 상세보기
    public UserDto getUser(Map<String, Object> params) {
        UserDto result = null;
        
        try {
            result = sqlSession.selectOne("userMapper.getUser", params);
        } catch (DataAccessException e) {
            logger.error("사용자 상세 오류 : {}", e.getMessage(), e);
        }

        return result;
    }
    
    // 사용자 수정
    public int updateUser(UserDto user) {
        int result = -1;

        try {
            result = sqlSession.update("userMapper.updateUser", user);
        } catch (DataAccessException e) {
            logger.error("사용자 수정 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 사용자 삭제
    public int deleteUser(String userId) {
        int result = -1;

        try {
            result = sqlSession.delete("userMapper.deleteUser", userId);
        } catch (DataAccessException e) {
            logger.error("사용자 삭제 오류 : {}", e.getMessage(), e);
        }

        return result;
    }

    // 전체 사용자 수
    public int getTotalCount(Map<String, Object> params) {
        int totalCount = 0;
        
        try {
            totalCount = sqlSession.selectOne("userMapper.getTotalCount", params);
        } catch (DataAccessException e) {
            logger.error("전체 사용자 수 오류 : {}", e.getMessage(), e);
        }
        
        return totalCount;
    }
}
