package kr.seoulfitness.admin.regularAdmissionCsat;

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
@RequestMapping("/admin/regularAdmissionCsat")
public class RegularAdmissionCsatController {

    @Autowired
    private RegularAdmissionCsatService regularAdmissionCsatService;

    @Autowired
    private AdmissionService admissionService;

    // 정시 입시 정보 존재 여부 확인
    public boolean isRegularAdmissionCsatExists(Map<String, Object> params) {
        return regularAdmissionCsatService.read(params) != null;
    }

    // 정시 입시 정보 등록
    @GetMapping("/create")
    public String create(@RequestParam("admissionId") int admissionId, Model model) {
        model.addAttribute("admissionId", admissionId);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(admissionId);
        model.addAttribute("admission", admission);

        return "admin/regularAdmissionCsat/create";
    }

    // 정시 입시 정보 등록 처리
    @PostMapping("/create")
    public String create(
        @RequestParam("admissionId") int admissionId, 
        RegularAdmissionCsatDto regularAdmissionCsat, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        regularAdmissionCsat.setCreatedBy((String) session.getAttribute("userId"));
        regularAdmissionCsat.setUpdatedBy((String) session.getAttribute("userId"));
        RegularAdmissionCsatDto createdRegularAdmissionCsat = regularAdmissionCsatService.create(regularAdmissionCsat);
        if (createdRegularAdmissionCsat != null) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 입시 수능 정보 등록이 완료되었습니다.");
            return "redirect:/admin/admissions/" + admissionId + "#regularAdmissionCsat";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "정시 입시 수능 정보 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("regularAdmissionCsat", regularAdmissionCsat);
        return "redirect:/admin/regularAdmissionCsat/create" + "?admissionId=" + admissionId;
    }

    // 정시 입시 정보 조회
    @GetMapping("/{regularAdmissionId}")
    public String read(@PathVariable int regularAdmissionId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionId", regularAdmissionId);
        RegularAdmissionCsatDto regularAdmissionCsat = regularAdmissionCsatService.read(params);
        model.addAttribute("regularAdmissionCsat", regularAdmissionCsat);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(regularAdmissionCsat.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/regularAdmissionCsat/read";
    }

    // 정시 입시 정보 수정
    @GetMapping("/{regularAdmissionId}/update")
    public String update(@PathVariable int regularAdmissionId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionId", regularAdmissionId);
        RegularAdmissionCsatDto regularAdmissionCsat = regularAdmissionCsatService.read(params);
        model.addAttribute("regularAdmissionCsat", regularAdmissionCsat);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(regularAdmissionCsat.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/regularAdmissionCsat/update";
    }
    
    // 정시 입시 정보 수정 처리
    @PostMapping("/{regularAdmissionId}/update")
    public String update(
        @PathVariable int regularAdmissionId,
        RegularAdmissionCsatDto regularAdmissionCsat,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        // 정시 입시 정보 존재 여부 확인
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionId", regularAdmissionId);
        if (!isRegularAdmissionCsatExists(params)) {
            return "redirect:/admin/admissions";
        }

        regularAdmissionCsat.setUpdatedBy((String) session.getAttribute("userId"));
        if (regularAdmissionCsatService.update(regularAdmissionCsat)) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 입시 수능 정보 수정이 완료되었습니다.");
            return "redirect:/admin/admissions/" + regularAdmissionCsat.getAdmissionId() + "#regularAdmissionCsat";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "정시 입시 수능 정보 수정에 실패했습니다.");
        redirectAttributes.addFlashAttribute("regularAdmissionCsat", regularAdmissionCsat);
        return "redirect:/admin/regularAdmissionCsat/" + regularAdmissionId + "/update";
    }
}
