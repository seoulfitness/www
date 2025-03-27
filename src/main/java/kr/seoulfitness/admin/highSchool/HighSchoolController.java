package kr.seoulfitness.admin.highSchool;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.seoulfitness.admin.province.ProvinceService;

@Controller
@RequestMapping("/admin/highSchools")
public class HighSchoolController {

    @Autowired
    private HighSchoolService highSchoolService;

    @Autowired
    private ProvinceService provinceService;

    // 고등학교 존재 여부 확인
    public boolean isHighschoolExists(int highSchoolId) {
        return highSchoolService.read(highSchoolId) != null;
    }

    // 고등학교 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        // 시/도 목록
        Map<String, Object> provinceParams = new HashMap<>();
        provinceParams.put("currentPage", 1);
        provinceParams.put("listCountPerPage", 100);
        provinceParams.put("pageCountPerPage", 100);
        provinceParams.put("keyword", null);

        // 시/도 목록 조회
        Map<String, Object> provinceResult = provinceService.list(provinceParams);
        model.addAttribute("provinces", provinceResult.get("provinces"));

        model.addAttribute("pageTitle", "고등학교 관리");
        model.addAttribute("activePage", "highSchools");
        return "admin/highSchool/create";
    }

    // 고등학교 등록 처리
    @PostMapping("/create")
    public String createPost(HighSchoolDto highSchool, HttpSession session, RedirectAttributes redirectAttributes) {
        // 고등학교 등록
        highSchool.setCreatedBy((String) session.getAttribute("userId"));
        highSchool.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = highSchoolService.create(highSchool);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "고등학교 등록이 완료되었습니다.");
            return "redirect:/admin/highSchools";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "고등학교 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("highSchool", highSchool);
        return "redirect:/admin/highSchools/create";
    }

    // 고등학교 목록
    @GetMapping("")
    public String list(
        @RequestParam(value = "page", defaultValue = "1") int currentPage, 
        @RequestParam(required = false) String keyword,
        Model model
    ) {
        // 파라미터 생성
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("listCountPerPage", 10);
        params.put("pageCountPerPage", 5);
        params.put("keyword", keyword);

        // 고등학교 목록 조회
        Map<String, Object> result = highSchoolService.list(params);

        // 목록 페이지 설정
        model.addAttribute("highSchools", result.get("highSchools"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "고등학교 관리");
        model.addAttribute("activePage", "highSchools");

        return "admin/highSchool/list";
    }

    // 고등학교 상세
    @GetMapping("/{highSchoolId}")
    public String read(@PathVariable int highSchoolId, Model model) {
        // 고등학교 존재 여부 확인
        if (!isHighschoolExists(highSchoolId)) {
            return "redirect:/admin/highSchools";
        }

        // 고등학교 상세
        HighSchoolDto highSchool = highSchoolService.read(highSchoolId);
        model.addAttribute("highSchool", highSchool);
        model.addAttribute("pageTitle", "고등학교 관리");
        model.addAttribute("activePage", "highSchools");
        return "admin/highSchool/read";
    }

    // 고등학교 수정
    @GetMapping("/{highSchoolId}/update")
    public String editForm(@PathVariable int highSchoolId, Model model) {   
        // 고등학교 존재 여부 확인
        if (!isHighschoolExists(highSchoolId)) {
            return "redirect:/admin/highSchools";
        }

        // 시/도 목록
        Map<String, Object> provinceParams = new HashMap<>();
        provinceParams.put("currentPage", 1);
        provinceParams.put("listCountPerPage", 100);
        provinceParams.put("pageCountPerPage", 100);
        provinceParams.put("keyword", null);

        Map<String, Object> provinceResult = provinceService.list(provinceParams);
        model.addAttribute("provinces", provinceResult.get("provinces"));

        // 고등학교 정보
        HighSchoolDto highSchool = highSchoolService.read(highSchoolId);
        model.addAttribute("highSchool", highSchool);
        model.addAttribute("pageTitle", "고등학교 관리");
        model.addAttribute("activePage", "highSchools");
        return "admin/highSchool/update";
    }

    // 고등학교 수정 처리
    @PostMapping("/{highSchoolId}/update")
    public String update(@PathVariable int highSchoolId, HighSchoolDto highSchool, HttpSession session, RedirectAttributes redirectAttributes) {
        // 고등학교 존재 여부 확인
        if (!isHighschoolExists(highSchoolId)) {
            return "redirect:/admin/highSchools";
        }

        // 고등학교 수정
        highSchool.setHighSchoolId(highSchoolId);
        highSchool.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = highSchoolService.update(highSchool);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "고등학교 수정이 완료되었습니다.");
            return "redirect:/admin/highSchools/" + highSchoolId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "고등학교 수정에 실패했습니다.");
        return "redirect:/admin/highSchools/" + highSchoolId + "/update";
    }

    // 고등학교 삭제
    @PostMapping("/{highSchoolId}/delete")
    public String delete(@PathVariable int highSchoolId, RedirectAttributes redirectAttributes) {
        // 고등학교 존재 여부 확인
        if (!isHighschoolExists(highSchoolId)) {
            return "redirect:/admin/highSchools";
        }

        // 고등학교 삭제
        boolean result = highSchoolService.delete(highSchoolId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "고등학교 삭제가 완료되었습니다.");
            return "redirect:/admin/highSchools";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "고등학교 삭제에 실패했습니다.");
        return "redirect:/admin/highSchools/" + highSchoolId;
    }
}
