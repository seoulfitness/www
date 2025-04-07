package kr.seoulfitness.admin.regularAdmissionEnglish;

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

import kr.seoulfitness.admin.admission.AdmissionDto;
import kr.seoulfitness.admin.admission.AdmissionService;
@Controller
@RequestMapping("/admin/regularAdmissionEnglish")
public class RegularAdmissionEnglishController {

    @Autowired
    private RegularAdmissionEnglishService regularAdmissionEnglishService;

    @Autowired
    private AdmissionService admissionService;

    // 정시 영어 점수 정보 존재 여부 확인
    public boolean isRegularAdmissionEnglishExists(Map<String, Object> params) {
        return regularAdmissionEnglishService.read(params) != null;
    }

    // 정시 영어 점수 정보 등록
    @GetMapping("/create")
    public String createGet(@RequestParam("admissionId") int admissionId, Model model) {
        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(admissionId);
        model.addAttribute("admission", admission);
        
        model.addAttribute("admissionId", admissionId);
        model.addAttribute("activePage", "admissions");
        return "admin/regularAdmissionEnglish/create";
    }

    // 정시 영어 점수 정보 등록 처리
    @PostMapping("/create")
    public String createPost(
        @RequestParam("admissionId") int admissionId, 
        RegularAdmissionEnglishDto regularAdmissionEnglish, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        regularAdmissionEnglish.setCreatedBy((String) session.getAttribute("userId"));
        regularAdmissionEnglish.setUpdatedBy((String) session.getAttribute("userId"));
        RegularAdmissionEnglishDto createdRegularAdmissionEnglish = regularAdmissionEnglishService.create(regularAdmissionEnglish);
        if (createdRegularAdmissionEnglish != null) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 입시 영어 정보 등록이 완료되었습니다.");
            return "redirect:/admin/admissions/" + admissionId + "#regularAdmissionEnglish";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "정시 입시 영어 정보 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("regularAdmissionEnglish", regularAdmissionEnglish);
        return "redirect:/admin/regularAdmissionEnglish/create" + "?admissionId=" + admissionId;
    }

    // 정시 영어 점수 정보 조회
    @GetMapping("/{regularAdmissionEnglishId}")
    public String read(@PathVariable int regularAdmissionEnglishId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionEnglishId", regularAdmissionEnglishId);
        RegularAdmissionEnglishDto regularAdmissionEnglish = regularAdmissionEnglishService.read(params);
        model.addAttribute("regularAdmissionEnglish", regularAdmissionEnglish);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(regularAdmissionEnglish.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/regularAdmissionEnglish/read";
    }

    // 정시 영어 점수 정보 수정
    @GetMapping("/{regularAdmissionEnglishId}/update")
    public String updateGet(@PathVariable int regularAdmissionEnglishId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionEnglishId", regularAdmissionEnglishId);
        RegularAdmissionEnglishDto regularAdmissionEnglish = regularAdmissionEnglishService.read(params);
        model.addAttribute("regularAdmissionEnglish", regularAdmissionEnglish);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(regularAdmissionEnglish.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/regularAdmissionEnglish/update";
    }
    
    // 정시 영어 점수 정보 수정 처리
    @PostMapping("/{regularAdmissionEnglishId}/update")
    public String updatePost(
        @PathVariable int regularAdmissionEnglishId,
        RegularAdmissionEnglishDto regularAdmissionEnglish,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        // 정시 영어 점수 정보 존재 여부 확인
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionEnglishId", regularAdmissionEnglishId);
        if (!isRegularAdmissionEnglishExists(params)) {
            return "redirect:/admin/regularAdmissionEnglish";
        }

        regularAdmissionEnglish.setUpdatedBy((String) session.getAttribute("userId"));
        if (regularAdmissionEnglishService.update(regularAdmissionEnglish)) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 입시 영어 정보 수정이 완료되었습니다.");
            return "redirect:/admin/admissions/" + regularAdmissionEnglish.getAdmissionId() + "#regularAdmissionEnglish";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "정시 입시 영어 정보 수정에 실패했습니다.");
        redirectAttributes.addFlashAttribute("regularAdmissionEnglish", regularAdmissionEnglish);
        return "redirect:/admin/regularAdmissionEnglish/" + regularAdmissionEnglishId + "/update";
    }
}
