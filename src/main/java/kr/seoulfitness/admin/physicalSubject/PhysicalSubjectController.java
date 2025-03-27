package kr.seoulfitness.admin.physicalSubject;

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

@Controller
@RequestMapping("/admin/physicalSubjects")
public class PhysicalSubjectController {

    @Autowired
    private PhysicalSubjectService physicalSubjectService;

    // 실기 교과목 존재 여부 확인
    public boolean isPhysicalSubjectExists(int physicalSubjectId) {
        return physicalSubjectService.read(physicalSubjectId) != null;
    }

    // 실기 교과목 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "실기 교과목");
        model.addAttribute("activePage", "physicalSubjects");
        return "admin/physicalSubject/create";
    }

    // 실기 교과목 등록 처리
    @PostMapping("/create")
    public String createPost(PhysicalSubjectDto physicalSubject, HttpSession session, RedirectAttributes redirectAttributes) {
        physicalSubject.setCreatedBy((String) session.getAttribute("userId"));
        physicalSubject.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = physicalSubjectService.create(physicalSubject);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "실기 교과목 등록이 완료되었습니다.");
            return "redirect:/admin/physicalSubjects";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "실기 교과목 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("physicalSubject", physicalSubject);
        return "redirect:/admin/physicalSubjects/create";
    }

    // 실기 교과목 목록
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

        // 실기 교과목 목록 조회
        Map<String, Object> result = physicalSubjectService.list(params);
        model.addAttribute("physicalSubjects", result.get("physicalSubjects"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "실기 교과목");
        model.addAttribute("activePage", "physicalSubjects");

        return "admin/physicalSubject/list";
    }

    // 실기 교과목 상세
    @GetMapping("/{physicalSubjectId}")
    public String read(@PathVariable int physicalSubjectId, Model model) {
        // 실기 교과목 존재 여부 확인
        if (!isPhysicalSubjectExists(physicalSubjectId)) {
            return "redirect:/admin/physicalSubjects";
        }

        PhysicalSubjectDto physicalSubject = physicalSubjectService.read(physicalSubjectId);
        model.addAttribute("physicalSubject", physicalSubject);
        model.addAttribute("pageTitle", "실기 교과목");
        model.addAttribute("activePage", "physicalSubjects");
        return "admin/physicalSubject/read";
    }

    // 실기 교과목 수정
    @GetMapping("/{physicalSubjectId}/update")
    public String editForm(@PathVariable int physicalSubjectId, Model model) {   
        // 실기 교과목 존재 여부 확인
        if (!isPhysicalSubjectExists(physicalSubjectId)) {
            return "redirect:/admin/physicalSubjects";
        }

        PhysicalSubjectDto physicalSubject = physicalSubjectService.read(physicalSubjectId);
        model.addAttribute("physicalSubject", physicalSubject);
        model.addAttribute("pageTitle", "실기 교과목");
        model.addAttribute("activePage", "physicalSubjects");
        return "admin/physicalSubject/update";
    }

    // 실기 교과목 수정 처리
    @PostMapping("/{physicalSubjectId}/update")
    public String update(@PathVariable int physicalSubjectId, PhysicalSubjectDto physicalSubject, HttpSession session, RedirectAttributes redirectAttributes) {
        // 실기 교과목 존재 여부 확인
        if (!isPhysicalSubjectExists(physicalSubjectId)) {
            return "redirect:/admin/physicalSubjects";
        }

        physicalSubject.setPhysicalSubjectId(physicalSubjectId);
        physicalSubject.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = physicalSubjectService.update(physicalSubject);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "실기 교과목 수정이 완료되었습니다.");
            return "redirect:/admin/physicalSubjects/" + physicalSubjectId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "실기 교과목 수정에 실패했습니다.");
        return "redirect:/admin/physicalSubjects/" + physicalSubjectId + "/update";
    }

    // 실기 교과목 삭제
    @PostMapping("/{physicalSubjectId}/delete")
    public String delete(@PathVariable int physicalSubjectId, RedirectAttributes redirectAttributes) {
        // 실기 교과목 존재 여부 확인
        if (!isPhysicalSubjectExists(physicalSubjectId)) {
            return "redirect:/admin/physicalSubjects";
        }

        boolean result = physicalSubjectService.delete(physicalSubjectId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "실기 교과목 삭제가 완료되었습니다.");
            return "redirect:/admin/physicalSubjects";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "실기 교과목 삭제에 실패했습니다.");
        return "redirect:/admin/physicalSubjects/" + physicalSubjectId;
    }
}
