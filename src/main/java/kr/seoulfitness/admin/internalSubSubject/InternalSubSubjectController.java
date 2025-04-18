package kr.seoulfitness.admin.internalSubSubject;

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
@RequestMapping("/admin/internalSubSubjects")
public class InternalSubSubjectController {

    @Autowired
    private InternalSubSubjectService internalSubSubjectService;

    // 내신 세부 교과목 존재 여부 확인
    public boolean isInternalSubSubjectExists(int internalSubSubjectId) {
        return internalSubSubjectService.read(internalSubSubjectId) != null;
    }

    // 내신 세부 교과목 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        model.addAttribute("pageTitle", "내신 세부 교과목 관리");
        model.addAttribute("activePage", "internalSubSubjects");
        return "admin/internalSubSubject/create";
    }

    // 내신 세부 교과목 등록 처리
    @PostMapping("/create")
    public String createPost(InternalSubSubjectDto internalSubSubject, HttpSession session, RedirectAttributes redirectAttributes) {
        internalSubSubject.setCreatedBy((String) session.getAttribute("userId"));
        internalSubSubject.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = internalSubSubjectService.create(internalSubSubject);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "내신 세부 교과목 등록이 완료되었습니다.");
            return "redirect:/admin/internalSubSubjects";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "내신 세부 교과목 등록에 실패했습니다.");
        redirectAttributes.addFlashAttribute("internalSubSubject", internalSubSubject);
        return "redirect:/admin/internalSubSubjects/create";
    }

    // 내신 세부 교과목 목록
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

        // 내신 세부 교과목 목록 조회
        Map<String, Object> result = internalSubSubjectService.list(params);
        model.addAttribute("internalSubSubjects", result.get("internalSubSubjects"));
        model.addAttribute("pagination", result.get("pagination"));
        model.addAttribute("keyword", result.get("keyword"));
        model.addAttribute("pageTitle", "내신 세부 교과목 관리");
        model.addAttribute("activePage", "internalSubSubjects");

        return "admin/internalSubSubject/list";
    }

    // 내신 세부 교과목 상세
    @GetMapping("/{internalSubSubjectId}")
    public String read(@PathVariable int internalSubSubjectId, Model model) {
        // 내신 세부 교과목 존재 여부 확인
        if (!isInternalSubSubjectExists(internalSubSubjectId)) {
            return "redirect:/admin/internalSubSubjects";
        }

        InternalSubSubjectDto internalSubSubject = internalSubSubjectService.read(internalSubSubjectId);
        model.addAttribute("internalSubSubject", internalSubSubject);
        model.addAttribute("pageTitle", "내신 세부 교과목 관리");
        model.addAttribute("activePage", "internalSubSubjects");
        return "admin/internalSubSubject/read";
    }

    // 내신 세부 교과목 수정
    @GetMapping("/{internalSubSubjectId}/update")
    public String updateGet(@PathVariable int internalSubSubjectId, Model model) {   
        // 내신 세부 교과목 존재 여부 확인
        if (!isInternalSubSubjectExists(internalSubSubjectId)) {
            return "redirect:/admin/internalSubSubjects";
        }

        InternalSubSubjectDto internalSubSubject = internalSubSubjectService.read(internalSubSubjectId);
        model.addAttribute("internalSubSubject", internalSubSubject);
        model.addAttribute("pageTitle", "내신 세부 교과목 관리");
        model.addAttribute("activePage", "internalSubSubjects");
        return "admin/internalSubSubject/update";
    }

    // 내신 세부 교과목 수정 처리
    @PostMapping("/{internalSubSubjectId}/update")
    public String updatePost(@PathVariable int internalSubSubjectId, InternalSubSubjectDto internalSubSubject, HttpSession session, RedirectAttributes redirectAttributes) {
        // 내신 세부 교과목 존재 여부 확인
        if (!isInternalSubSubjectExists(internalSubSubjectId)) {
            return "redirect:/admin/internalSubSubjects";
        }

        internalSubSubject.setInternalSubSubjectId(internalSubSubjectId);
        internalSubSubject.setUpdatedBy((String) session.getAttribute("userId"));
        boolean result = internalSubSubjectService.update(internalSubSubject);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "내신 세부 교과목 수정이 완료되었습니다.");
            return "redirect:/admin/internalSubSubjects/" + internalSubSubjectId;
        }

        redirectAttributes.addFlashAttribute("errorMessage", "내신 세부 교과목 수정에 실패했습니다.");
        return "redirect:/admin/internalSubSubjects/" + internalSubSubjectId + "/update";
    }

    // 내신 세부 교과목 삭제
    @PostMapping("/{internalSubSubjectId}/delete")
    public String delete(@PathVariable int internalSubSubjectId, RedirectAttributes redirectAttributes) {
        // 내신 세부 교과목 존재 여부 확인
        if (!isInternalSubSubjectExists(internalSubSubjectId)) {
            return "redirect:/admin/internalSubSubjects";
        }

        boolean result = internalSubSubjectService.delete(internalSubSubjectId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "내신 세부 교과목 삭제가 완료되었습니다.");
            return "redirect:/admin/internalSubSubjects";
        }

        redirectAttributes.addFlashAttribute("errorMessage", "내신 세부 교과목 삭제에 실패했습니다.");
        return "redirect:/admin/internalSubSubjects/" + internalSubSubjectId;
    }
}
