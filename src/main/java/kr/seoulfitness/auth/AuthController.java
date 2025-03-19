package kr.seoulfitness.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.seoulfitness.user.UserDto;
import kr.seoulfitness.user.UserService;
@Controller
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    // 로그인(화면, GET)
    @GetMapping("/login")
    public String loginGet() {
        // 관리자 존재 여부 확인
        UserDto existsAdmin = new UserDto();
        existsAdmin.setUserId("root");
        existsAdmin = userService.read(existsAdmin);

        // 관리자 등록
        if (existsAdmin == null) {
            // 관리자 정보
            UserDto newAdmin = new UserDto();
            newAdmin.setUserId("root");
            newAdmin.setPassword("1234");
            newAdmin.setName("정필성");
            newAdmin.setEmail("ibetter.kr@gmail.com");
            newAdmin.setPhone("010-5911-8108");
            newAdmin.setRole("root");
            newAdmin.setStatus("Y");

            // 관리자 등록
            boolean result = userService.create(newAdmin);
            if (result) {
                logger.debug("관리자 등록 성공");
            } else {
                logger.debug("관리자 등록 실패");
            }
        }

        return ("auth/login");
    }

    // 로그인(처리, POST)
    @PostMapping("/login")
    public String loginPost(UserDto user, HttpSession session) {
        UserDto loggedInUser = authService.login(user);

        if (loggedInUser != null) {
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("id", loggedInUser.getId());
            return ("redirect:/dashboard");
        }

        return ("redirect:/auth/login");
    }
    
    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ("redirect:/auth/login");
    }
}
