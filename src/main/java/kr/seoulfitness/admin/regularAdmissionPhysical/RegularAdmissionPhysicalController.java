package kr.seoulfitness.admin.regularAdmissionPhysical;

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
import kr.seoulfitness.admin.physicalSubject.PhysicalSubjectService;
@Controller
@RequestMapping("/admin/regularAdmissionPhysical")
public class RegularAdmissionPhysicalController {

    @Autowired
    private RegularAdmissionPhysicalService regularAdmissionPhysicalService;

    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private PhysicalSubjectService physicalSubjectService;

    // 정시 실기 점수 정보 존재 여부 확인
    public boolean isRegularAdmissionPhysicalExists(Map<String, Object> params) {
        return regularAdmissionPhysicalService.read(params) != null;
    }

    // 정시 실기 점수 정보 등록
    @GetMapping("/create")
    public String create(@RequestParam("admissionId") int admissionId, Model model) {
        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(admissionId);
        model.addAttribute("admission", admission);
        
        model.addAttribute("admissionId", admissionId);
        model.addAttribute("activePage", "admissions");

        // 실기 교과목 파라미터 생성
        Map<String, Object> physicalSubjectParams = new HashMap<>();
        physicalSubjectParams.put("currentPage", 1);
        physicalSubjectParams.put("listCountPerPage", 1000);
        physicalSubjectParams.put("pageCountPerPage", 1000);

        // 실기 교과목 목록 조회
        Map<String, Object> physicalSubjectResult = physicalSubjectService.list(physicalSubjectParams);
        model.addAttribute("physicalSubjects", physicalSubjectResult.get("physicalSubjects"));

        return "admin/regularAdmissionPhysical/create";
    }

    // 정시 실기 점수 정보 등록 처리
    @PostMapping("/create")
    public String create(
        @RequestParam("admissionId") int admissionId, 
        RegularAdmissionPhysicalDto regularAdmissionPhysical, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        regularAdmissionPhysical.setCreatedBy((String) session.getAttribute("userId"));
        regularAdmissionPhysical.setUpdatedBy((String) session.getAttribute("userId"));
        RegularAdmissionPhysicalDto createdRegularAdmissionPhysical = regularAdmissionPhysicalService.create(regularAdmissionPhysical);
        if (createdRegularAdmissionPhysical != null) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 입시 실기 정보 등록이 완료되었습니다.");
            return "redirect:/admin/admissions/" + admissionId + "#regularAdmissionPhysical";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "정시 입시 실기 정보 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("regularAdmissionPhysical", regularAdmissionPhysical);
        return "redirect:/admin/regularAdmissionPhysical/create" + "?admissionId=" + admissionId;
    }

    // 정시 실기 점수 정보 조회
    @GetMapping("/{regularAdmissionPhysicalId}")
    public String read(@PathVariable int regularAdmissionPhysicalId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionPhysicalId", regularAdmissionPhysicalId);
        RegularAdmissionPhysicalDto regularAdmissionPhysical = regularAdmissionPhysicalService.read(params);
        model.addAttribute("regularAdmissionPhysical", regularAdmissionPhysical);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(regularAdmissionPhysical.getAdmissionId());
        model.addAttribute("admission", admission);

        return "admin/regularAdmissionPhysical/read";
    }

    // 정시 실기 점수 정보 수정
    @GetMapping("/{regularAdmissionPhysicalId}/update")
    public String update(@PathVariable int regularAdmissionPhysicalId, Model model) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionPhysicalId", regularAdmissionPhysicalId);
        RegularAdmissionPhysicalDto regularAdmissionPhysical = regularAdmissionPhysicalService.read(params);
        model.addAttribute("regularAdmissionPhysical", regularAdmissionPhysical);
        model.addAttribute("activePage", "admissions");

        // 입시 요강 정보
        AdmissionDto admission = admissionService.read(regularAdmissionPhysical.getAdmissionId());
        model.addAttribute("admission", admission);

        // 실기 교과목 파라미터 생성
        Map<String, Object> physicalSubjectParams = new HashMap<>();
        physicalSubjectParams.put("currentPage", 1);
        physicalSubjectParams.put("listCountPerPage", 1000);
        physicalSubjectParams.put("pageCountPerPage", 1000);

        // 실기 교과목 목록 조회
        Map<String, Object> physicalSubjectResult = physicalSubjectService.list(physicalSubjectParams);
        model.addAttribute("physicalSubjects", physicalSubjectResult.get("physicalSubjects"));

        return "admin/regularAdmissionPhysical/update";
    }
    
    // 정시 실기 점수 정보 수정 처리
    @PostMapping("/{regularAdmissionPhysicalId}/update")
    public String update(
        @PathVariable int regularAdmissionPhysicalId,
        RegularAdmissionPhysicalDto regularAdmissionPhysical,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        // 정시 실기 점수 정보 존재 여부 확인
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionPhysicalId", regularAdmissionPhysicalId);
        if (!isRegularAdmissionPhysicalExists(params)) {
            return "redirect:/admin/regularAdmissionPhysical";
        }

        regularAdmissionPhysical.setUpdatedBy((String) session.getAttribute("userId"));
        if (regularAdmissionPhysicalService.update(regularAdmissionPhysical)) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 입시 실기 정보 수정이 완료되었습니다.");
            return "redirect:/admin/admissions/" + regularAdmissionPhysical.getAdmissionId() + "#regularAdmissionPhysical";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "정시 입시 실기 정보 수정에 실패했습니다.");
        redirectAttributes.addFlashAttribute("regularAdmissionPhysical", regularAdmissionPhysical);
        return "redirect:/admin/regularAdmissionPhysical/" + regularAdmissionPhysicalId + "/update";
    }
}
