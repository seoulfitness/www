package kr.seoulfitness.admin.internalSubject;

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
@RequestMapping("/admin/internalSubjects")
public class InternalSubjectController {

    @Autowired
    private InternalSubjectService internalSubjectService;

    // 내신 교과목 존재 여부 확인
    public boolean isInternalSubjectExists(int internalSubjectId) {
        return internalSubjectService.read(internalSubjectId) != null;
    }

    // 내신 교과목 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "내신 교과목 관리");
        model.addAttribute("activePage", "internalSubjects");
        return "admin/internalSubject/create";
    }

    // 내신 교과목 등록 처리
    @PostMapping("/create")
    public String createPost(InternalSubjectDto internalSubject, HttpSession session, RedirectAttributes redirectAttributes) {
        internalSubject.setCreatedBy((String) session.getAttribute("userId"));
        internalSubject.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = internalSubjectService.create(internalSubject);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "내신 교과목 등록이 완료되었습니다.");
            return "redirect:/admin/internalSubjects";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "내신 교과목 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("internalSubject", internalSubject);
        return "redirect:/admin/internalSubjects/create";
    }

    // 내신 교과목 목록
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

        // 내신 교과목 목록 조회
        Map<String, Object> result = internalSubjectService.list(params);
        model.addAttribute("internalSubjects", result.get("internalSubjects"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "내신 교과목 관리");
        model.addAttribute("activePage", "internalSubjects");

        return "admin/internalSubject/list";
    }

    // 내신 교과목 상세
    @GetMapping("/{internalSubjectId}")
    public String read(@PathVariable int internalSubjectId, Model model) {
        // 내신 교과목 존재 여부 확인
        if (!isInternalSubjectExists(internalSubjectId)) {
            return "redirect:/admin/internalSubjects";
        }

        InternalSubjectDto internalSubject = internalSubjectService.read(internalSubjectId);
        model.addAttribute("internalSubject", internalSubject);
        model.addAttribute("pageTitle", "내신 교과목 관리");
        model.addAttribute("activePage", "internalSubjects");
        return "admin/internalSubject/read";
    }

    // 내신 교과목 수정
    @GetMapping("/{internalSubjectId}/update")
    public String updateGet(@PathVariable int internalSubjectId, Model model) {   
        // 내신 교과목 존재 여부 확인
        if (!isInternalSubjectExists(internalSubjectId)) {
            return "redirect:/admin/internalSubjects";
        }

        InternalSubjectDto internalSubject = internalSubjectService.read(internalSubjectId);
        model.addAttribute("internalSubject", internalSubject);
        model.addAttribute("pageTitle", "내신 교과목 관리");
        model.addAttribute("activePage", "internalSubjects");
        return "admin/internalSubject/update";
    }

    // 내신 교과목 수정 처리
    @PostMapping("/{internalSubjectId}/update")
    public String updatePost(@PathVariable int internalSubjectId, InternalSubjectDto internalSubject, HttpSession session, RedirectAttributes redirectAttributes) {
        // 내신 교과목 존재 여부 확인
        if (!isInternalSubjectExists(internalSubjectId)) {
            return "redirect:/admin/internalSubjects";
        }

        internalSubject.setInternalSubjectId(internalSubjectId);
        internalSubject.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = internalSubjectService.update(internalSubject);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "내신 교과목 수정이 완료되었습니다.");
            return "redirect:/admin/internalSubjects/" + internalSubjectId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "내신 교과목 수정에 실패했습니다.");
        return "redirect:/admin/internalSubjects/" + internalSubjectId + "/update";
    }

    // 내신 교과목 삭제
    @PostMapping("/{internalSubjectId}/delete")
    public String delete(@PathVariable int internalSubjectId, RedirectAttributes redirectAttributes) {
        // 내신 교과목 존재 여부 확인
        if (!isInternalSubjectExists(internalSubjectId)) {
            return "redirect:/admin/internalSubjects";
        }

        boolean result = internalSubjectService.delete(internalSubjectId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "내신 교과목 삭제가 완료되었습니다.");
            return "redirect:/admin/internalSubjects";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "내신 교과목 삭제에 실패했습니다.");
        return "redirect:/admin/internalSubjects/" + internalSubjectId;
    }
}
