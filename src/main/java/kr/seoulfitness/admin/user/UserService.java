package kr.seoulfitness.admin.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class UserService {
    
    @Autowired
    private UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    // 사용자 등록
    public boolean create(UserDto user) {
        // 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.insertUser(user) > 0;
    }

    // 사용자 목록
    public Map<String, Object> list(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = userDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<UserDto> users = userDao.getUsers(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("users", users);
        result.put("pagination", pagination);

        return result;
    }

    // 사용자 상세보기
    public UserDto read(Map<String, Object> params) {
        return userDao.getUser(params);
    }

    // 사용자 수정
    public boolean update(UserDto user) {
        // 비밀번호가 있으면 암호화
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }

        // 사용자 수정
        return userDao.updateUser(user) > 0;
    }

    // 사용자 삭제
    public boolean delete(String userId) {
        return userDao.deleteUser(userId) > 0;
    }
}
