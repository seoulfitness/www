package kr.seoulfitness.auth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.seoulfitness.admin.user.UserDto;
import kr.seoulfitness.admin.user.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    // 로그인(화면, GET)
    @GetMapping("/login")
    public String loginGet() {
        // 관리자 정보 확인 후 없으면 입력
        Map<String, Object> params = new HashMap<>();
        params.put("userId", "sung2ne");
        UserDto eixstsAdmin = userService.find(params);
        if (eixstsAdmin == null) {
            UserDto admin = new UserDto();
            admin.setUserId("sung2ne");
            admin.setPassword("1234");
            admin.setUserName("정필성");
            admin.setUserEmail("admin@seoulfitness.kr");
            admin.setUserPhone("010-1234-5678");
            admin.setStatus("Y");
            admin.setRole("관리자");
            admin.setCreatedBy("sung2ne");
            admin.setUpdatedBy("sung2ne");
            userService.create(admin);
        }
        
        // 로그인 화면
        return ("auth/login");
    }

    // 로그인(처리, POST)
    @PostMapping("/login")
    public String loginPost(UserDto user, HttpSession session) {
        UserDto loggedInUser = authService.login(user);

        if (loggedInUser != null) {
            session.setAttribute("userId", loggedInUser.getUserId());
            session.setAttribute("userName", loggedInUser.getUserName());
            session.setAttribute("userEmail", loggedInUser.getUserEmail());
            session.setAttribute("userPhone", loggedInUser.getUserPhone());
            session.setAttribute("role", loggedInUser.getRole());

            switch (loggedInUser.getRole()) {
                case "관리자":
                    return ("redirect:/admin/branches");
                case "지점 관리자":
                    return ("redirect:/branchManager/schools");
                default:
                    return ("redirect:/auth/login");
            }
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
