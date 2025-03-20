package kr.seoulfitness.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.seoulfitness.admin.user.UserDao;
import kr.seoulfitness.admin.user.UserDto;

@Service
public class AuthService {
    
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 로그인
    public UserDto login(UserDto user) {
        UserDto existsUser = userDao.read(user);

        if (existsUser != null && passwordEncoder.matches(user.getPassword(), existsUser.getPassword())) {
            return existsUser;
        }

        return null;
    }
}
