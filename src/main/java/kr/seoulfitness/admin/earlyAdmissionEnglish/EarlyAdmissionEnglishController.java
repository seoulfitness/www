package kr.seoulfitness.admin.earlyAdmissionEnglish;

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
@RequestMapping("/admin/earlyAdmissionEnglish")
public class EarlyAdmissionEnglishController {

    @Autowired
    private EarlyAdmissionEnglishService earlyAdmissionEnglishService;

    @Autowired
    private AdmissionService admissionService;

    // 수시 영어 점수 정보 존재 여부 확인
    public boolean isEarlyAdmissionEnglishExists(Map<String, Object> params) {
        return earlyAdmissionEnglishService.read(params) != null;
    }

    // 수시 영어 점수 정보 등록
    @GetMapping("/create")
    public String create(@RequestParam("admissionId") int admissionId, Model model) {
        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(admissionId);
        model.addAttribute("admission", admission);
        
        model.addAttribute("admissionId", admissionId);
        model.addAttribute("activePage", "admissions");
        return "admin/earlyAdmissionEnglish/create";
    }

    // 수시 영어 점수 정보 등록 처리
    @PostMapping("/create")
    public String create(
        @RequestParam("admissionId") int admissionId, 
        EarlyAdmissionEnglishDto earlyAdmissionEnglish, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        earlyAdmissionEnglish.setCreatedBy((String) session.getAttribute("userId"));
        earlyAdmissionEnglish.setUpdatedBy((String) session.getAttribute("userId"));
        EarlyAdmissionEnglishDto createdEarlyAdmissionEnglish = earlyAdmissionEnglishService.create(earlyAdmissionEnglish);
        if (createdEarlyAdmissionEnglish != null) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 입시 영어 정보 등록이 완료되었습니다.");
            return "redirect:/admin/admissions/" + admissionId + "#earlyAdmissionEnglish";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수시 입시 영어 정보 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("earlyAdmissionEnglish", earlyAdmissionEnglish);
        return "redirect:/admin/earlyAdmissionEnglish/create" + "?admissionId=" + admissionId;
    }

    // 수시 영어 점수 정보 조회
    @GetMapping("/{earlyAdmissionEnglishId}")
    public String read(@PathVariable int earlyAdmissionEnglishId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionEnglishId", earlyAdmissionEnglishId);
        EarlyAdmissionEnglishDto earlyAdmissionEnglish = earlyAdmissionEnglishService.read(params);
        model.addAttribute("earlyAdmissionEnglish", earlyAdmissionEnglish);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(earlyAdmissionEnglish.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/earlyAdmissionEnglish/read";
    }

    // 수시 영어 점수 정보 수정
    @GetMapping("/{earlyAdmissionEnglishId}/update")
    public String update(@PathVariable int earlyAdmissionEnglishId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionEnglishId", earlyAdmissionEnglishId);
        EarlyAdmissionEnglishDto earlyAdmissionEnglish = earlyAdmissionEnglishService.read(params);
        model.addAttribute("earlyAdmissionEnglish", earlyAdmissionEnglish);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(earlyAdmissionEnglish.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/earlyAdmissionEnglish/update";
    }
    
    // 수시 영어 점수 정보 수정 처리
    @PostMapping("/{earlyAdmissionEnglishId}/update")
    public String update(
        @PathVariable int earlyAdmissionEnglishId,
        EarlyAdmissionEnglishDto earlyAdmissionEnglish,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        // 수시 영어 점수 정보 존재 여부 확인
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionEnglishId", earlyAdmissionEnglishId);
        if (!isEarlyAdmissionEnglishExists(params)) {
            return "redirect:/admin/earlyAdmissionEnglish";
        }

        earlyAdmissionEnglish.setUpdatedBy((String) session.getAttribute("userId"));
        if (earlyAdmissionEnglishService.update(earlyAdmissionEnglish)) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 입시 영어 정보 수정이 완료되었습니다.");
            return "redirect:/admin/admissions/" + earlyAdmissionEnglish.getAdmissionId() + "#earlyAdmissionEnglish";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "수시 입시 영어 정보 수정에 실패했습니다.");
        redirectAttributes.addFlashAttribute("earlyAdmissionEnglish", earlyAdmissionEnglish);
        return "redirect:/admin/earlyAdmissionEnglish/" + earlyAdmissionEnglishId + "/update";
    }
}
