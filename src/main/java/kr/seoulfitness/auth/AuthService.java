package kr.seoulfitness.auth;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.seoulfitness.admin.user.UserDao;
import kr.seoulfitness.admin.user.UserDto;

@Service
public class AuthService {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 로그인
    public UserDto login(UserDto user) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", user.getUserId());
        UserDto existsUser = userDao.getUser(params);
        
        if (existsUser == null) {
            logger.warn("로그인 실패: 사용자를 찾을 수 없습니다. userId: {}", user.getUserId());
            return null;
        }

        if (!passwordEncoder.matches(user.getPassword(), existsUser.getPassword())) {
            logger.warn("로그인 실패: 비밀번호가 일치하지 않습니다. userId: {}", user.getUserId());
            return null;
        }

        logger.info("로그인 성공. userId: {}, userName: {}, role: {}", 
            existsUser.getUserId(), existsUser.getUserName(), existsUser.getRole());
        return existsUser;
    }
}
