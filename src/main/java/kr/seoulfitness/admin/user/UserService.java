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
        return userDao.create(user) > 0;
    }

    // 사용자 목록
    public Map<String, Object> list(int currentPage, int listCountPerPage, int pageCountPerPage, String keyword, String role) {
        // 전체 게시글 수 조회
        int totalCount = userDao.totalCount(keyword, role);

        // 페이지네이션 정보 생성
        Pagination pagination = new Pagination(currentPage, listCountPerPage, pageCountPerPage, totalCount);

        // 페이징된 게시글 목록 조회
        List<UserDto> users = userDao.list(pagination.offset(), listCountPerPage, keyword, role);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("users", users);
        result.put("pagination", pagination);
        result.put("keyword", keyword);

        return result;
    }

    // 사용자 상세보기
    public UserDto read(UserDto user) {
        return userDao.read(user);
    }

    // 사용자 수정
    public int update(UserDto user) {
        return userDao.update(user);
    }

    // 사용자 삭제
    public int delete(int id) {
        return userDao.delete(id);
    }
}
