package kr.seoulfitness.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    
    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    // 로그인(화면, GET)
    @GetMapping("/login")
    public String loginGet() {
        return ("auth/login");
    }

    // 로그인(처리, POST)
    @PostMapping("/login")
    public String loginPost(UserDto user, HttpSession session) {
        UserDto loggedInUser = authService.login(user);

        if (loggedInUser != null) {
            session.setAttribute("loggedInUser", loggedInUser);
            session.setAttribute("userId", loggedInUser.getUserId());
            session.setAttribute("userName", loggedInUser.getUserName());
            session.setAttribute("userEmail", loggedInUser.getUserEmail());
            session.setAttribute("userPhone", loggedInUser.getUserPhone());
            session.setAttribute("role", loggedInUser.getRole());
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
