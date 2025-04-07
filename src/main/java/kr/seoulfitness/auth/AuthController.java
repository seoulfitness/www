package kr.seoulfitness.auth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        UserDto eixstsAdmin = userService.read(params);
        if (eixstsAdmin == null) {
            UserDto admin = new UserDto();
            admin.setUserId("sung2ne");
            admin.setPassword("1234");
            admin.setUserName("정필성");
            admin.setUserEmail("admin@seoulfitness.kr");
            admin.setUserPhone("010-1234-5678");
            admin.setStatus("Y");
            admin.setRole("admin");
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
                case "admin":
                    return ("redirect:/admin/branches");
                case "branchManager":
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

    // 아이디 중복 체크
    @PostMapping("/check-user-id")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkUserIdPost(@RequestParam String userId) {
        // 사용자 정보 조회
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        UserDto user = userService.read(params);

        // 반환 정보
        Map<String, Object> response = new HashMap<>();

        // 사용자 아이디가 존재하는 경우
        if (user != null) {
            response.put("exists", true);
        } 
        // 사용자가 존재하지 않는 경우
        else {
            response.put("exists", false);
        }

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    // 이메일 중복 체크
    @PostMapping("/check-email")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkEmailPost(@RequestParam String email) {
        // 사용자 정보 조회
        Map<String, Object> params = new HashMap<>();
        params.put("userEmail", email);
        UserDto user = userService.read(params);

        // 반환 정보
        Map<String, Object> response = new HashMap<>();
        
        // 사용자가 존재하는 경우
        if (user != null) {
            response.put("exists", true);
        } 
        // 사용자 아이디가 존재하지 않는 경우
        else {
            response.put("exists", false);
        }

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    // 전화번호 중복 체크
    @PostMapping("/check-phone")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkPhonePost(@RequestParam String phone) {
        // 사용자 정보 조회
        Map<String, Object> params = new HashMap<>();
        params.put("userPhone", phone);
        UserDto user = userService.read(params);
        
        // 반환 정보
        Map<String, Object> response = new HashMap<>();
        
        // 사용자가 존재하는 경우
        if (user != null) {
            response.put("exists", true);
        } 
        // 사용자 아이디가 존재하지 않는 경우
        else {
            response.put("exists", false);
        }

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }
    
}
