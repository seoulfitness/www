package kr.seoulfitness.auth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.seoulfitness.admin.branch.BranchService;
import kr.seoulfitness.admin.district.DistrictService;
import kr.seoulfitness.admin.highSchool.HighSchoolService;
import kr.seoulfitness.admin.province.ProvinceService;
import kr.seoulfitness.admin.user.UserDto;
import kr.seoulfitness.admin.user.UserService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @Autowired
    ProvinceService provinceService;

    @Autowired
    DistrictService districtService;

    @Autowired
    HighSchoolService highSchoolService;

    @Autowired
    BranchService branchService;

    // 일반 회원 가입(화면, GET)
    @GetMapping("/normal-register")
    public String normalRegisterGet(Model model) {
        // 시/도 목록
        Map<String, Object> provinceParams = new HashMap<>();
        provinceParams.put("currentPage", 1);
        provinceParams.put("listCountPerPage", 100);
        provinceParams.put("pageCountPerPage", 100);
        provinceParams.put("keyword", null);

        // 시/도 목록 조회
        Map<String, Object> provinceResult = provinceService.list(provinceParams);
        model.addAttribute("provinces", provinceResult.get("provinces"));

        model.addAttribute("pageTitle", "일반 회원 가입");
        model.addAttribute("activePage", "normalRegister");
        return ("auth/normalRegister");
    }
    
    // 일반 회원 가입(처리, POST)
    @PostMapping("/normal-register")
    public String normalRegisterPost(UserDto user, RedirectAttributes redirectAttributes) {
        userService.create(user);
        redirectAttributes.addFlashAttribute("successMessage", "회원가입이 완료되었습니다.");
        return ("redirect:/auth/login");
    }

    // 지점 회원 가입(화면, GET)
    @GetMapping("/branch-register")
    public String branchRegisterGet(Model model) {
        // 학원(지점) 목록
        Map<String, Object> branchParams = new HashMap<>();
        branchParams.put("currentPage", 1);
        branchParams.put("listCountPerPage", 100);
        branchParams.put("pageCountPerPage", 100);
        branchParams.put("keyword", null);

        // 학원(지점) 목록 조회
        Map<String, Object> branchResult = branchService.list(branchParams);
        model.addAttribute("branches", branchResult.get("branches"));

        // 시/도 목록
        Map<String, Object> provinceParams = new HashMap<>();
        provinceParams.put("currentPage", 1);
        provinceParams.put("listCountPerPage", 100);
        provinceParams.put("pageCountPerPage", 100);
        provinceParams.put("keyword", null);

        // 시/도 목록 조회
        Map<String, Object> provinceResult = provinceService.list(provinceParams);
        model.addAttribute("provinces", provinceResult.get("provinces"));

        model.addAttribute("pageTitle", "학원(지점) 회원 가입");
        model.addAttribute("activePage", "branchRegister");
        return ("auth/branchRegister");
    }
    
    // 지점 회원 가입(처리, POST)
    @PostMapping("/branch-register")
    public String branchRegisterPost(UserDto user) {
        userService.create(user);
        return ("redirect:/auth/login");
    }

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
            admin.setRole("ADMIN");
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
                case "ADMIN":
                    return ("redirect:/admin/branches");
                case "BRANCH_USER":
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
    @PostMapping("/check-user-email")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkEmailPost(@RequestParam String userEmail) {
        // 사용자 정보 조회
        Map<String, Object> params = new HashMap<>();
        params.put("userEmail", userEmail);
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
    @PostMapping("/check-user-phone")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkPhonePost(@RequestParam String userPhone) {
        // 사용자 정보 조회
        Map<String, Object> params = new HashMap<>();
        params.put("userPhone", userPhone);
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
    
    // 구/군 목록 조회
    @PostMapping("/get-districts")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getDistricts(@RequestParam int provinceId) {
        // 구/군 목록 조회
        Map<String, Object> params = new HashMap<>();
        params.put("provinceId", provinceId);
        params.put("currentPage", 1);
        params.put("listCountPerPage", 100);
        params.put("pageCountPerPage", 100);
        params.put("keyword", null);
        Map<String, Object> result = districtService.list(params);

        // 반환 정보
        Map<String, Object> response = new HashMap<>();
        response.put("districts", result.get("districts"));

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }

    // 고등학교 목록 조회
    @PostMapping("/get-highschools")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getHighSchools(@RequestParam int provinceId, @RequestParam int districtId) {
        // 고등학교 목록 조회
        Map<String, Object> params = new HashMap<>();   
        params.put("provinceId", provinceId);
        params.put("districtId", districtId);
        params.put("currentPage", 1);
        params.put("listCountPerPage", 1000);
        params.put("pageCountPerPage", 1000);
        params.put("keyword", null);
        Map<String, Object> result = highSchoolService.list(params);

        // 반환 정보
        Map<String, Object> response = new HashMap<>();
        response.put("highSchools", result.get("highSchools"));

        return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(response);
    }
}
