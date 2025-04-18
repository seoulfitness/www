package kr.seoulfitness.admin.internalSubSubject;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.seoulfitness.admin.internalSubject.InternalSubjectService;

@Controller
@RequestMapping("/admin/internalSubSubjects")
public class InternalSubSubjectController {

    @Autowired
    private InternalSubSubjectService internalSubSubjectService;

    @Autowired
    private InternalSubjectService internalSubjectService;

    // 내신 세부 교과목 등록
    @GetMapping("/create")
    public String createGet(Model model) {
        // 내신 교과목 목록 조회 파라미터
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", 1);
        params.put("listCountPerPage", 100);
        params.put("pageCountPerPage", 10);
        params.put("keyword", "");

        // 내신 교과목 목록 조회
        Map<String, Object> result = internalSubjectService.list(params);
        model.addAttribute("internalSubjects", result.get("internalSubjects"));

        // 페이지 정보
        model.addAttribute("pageTitle", "내신 세부 교과목 관리");
        model.addAttribute("activePage", "internalSubSubjects");
        return "admin/internalSubSubject/create";
    }

    // 내신 세부 교과목 등록 처리
    @PostMapping("/create")
    public String createPost(InternalSubSubjectDto internalSubSubject, HttpSession session, RedirectAttributes redirectAttributes) {
        // 세션에서 사용자 정보 가져오기
        internalSubSubject.setCreatedBy((String) session.getAttribute("userId"));
        internalSubSubject.setUpdatedBy((String) session.getAttribute("userId"));

        // 내신 세부 교과목 존재 여부 확인
        InternalSubSubjectDto existsInternalSubSubject = internalSubSubjectService.read(internalSubSubject);
        if (existsInternalSubSubject != null) {
            redirectAttributes.addFlashAttribute("internalSubSubject", internalSubSubject);
            redirectAttributes.addFlashAttribute("errorMessage", "내신 세부 교과목이 이미 존재합니다.");
            return "redirect:/admin/internalSubSubjects/create";
        }
        // 내신 세부 교과목 등록
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
        // 내신 세부 교과목 존재 여부 확인(internalSubSubjectId 로 확인)
        InternalSubSubjectDto existsInternalSubSubject = new InternalSubSubjectDto();
        existsInternalSubSubject.setInternalSubSubjectId(internalSubSubjectId);
        existsInternalSubSubject = internalSubSubjectService.read(existsInternalSubSubject);
        if (existsInternalSubSubject == null) {
            return "redirect:/admin/internalSubSubjects";
        }

        // 페이지 정보
        model.addAttribute("internalSubSubject", existsInternalSubSubject);
        model.addAttribute("pageTitle", "내신 세부 교과목 관리");
        model.addAttribute("activePage", "internalSubSubjects");
        return "admin/internalSubSubject/read";
    }

    // 내신 세부 교과목 수정
    @GetMapping("/{internalSubSubjectId}/update")
    public String updateGet(@PathVariable int internalSubSubjectId, Model model) {   
        // 내신 세부 교과목 존재 여부 확인(internalSubSubjectId 로 확인)
        InternalSubSubjectDto existsInternalSubSubject = new InternalSubSubjectDto();
        existsInternalSubSubject.setInternalSubSubjectId(internalSubSubjectId);
        existsInternalSubSubject = internalSubSubjectService.read(existsInternalSubSubject);
        if (existsInternalSubSubject == null) {
            return "redirect:/admin/internalSubSubjects";
        }

        // 페이지 정보
        model.addAttribute("internalSubSubject", existsInternalSubSubject);
        model.addAttribute("pageTitle", "내신 세부 교과목 관리");
        model.addAttribute("activePage", "internalSubSubjects");
        return "admin/internalSubSubject/update";
    }

    // 내신 세부 교과목 수정 처리
    @PostMapping("/{internalSubSubjectId}/update")
    public String updatePost(@PathVariable int internalSubSubjectId, InternalSubSubjectDto internalSubSubject, HttpSession session, RedirectAttributes redirectAttributes) {
        // 내신 세부 교과목 정보
        InternalSubSubjectDto existsInternalSubSubject = new InternalSubSubjectDto();
        existsInternalSubSubject.setInternalSubSubjectId(internalSubSubjectId);
        existsInternalSubSubject = internalSubSubjectService.read(existsInternalSubSubject);

        // 내신 세부 교과목 존재 여부 확인
        if (existsInternalSubSubject.getInternalSubSubjectId() == internalSubSubject.getInternalSubSubjectId() && existsInternalSubSubject.getInternalSubSubjectName().equals(internalSubSubject.getInternalSubSubjectName())) {
            redirectAttributes.addFlashAttribute("errorMessage", "동일한 내신 세부 교과목 정보가 존재합니다. 수정 후 저장해주세요.");
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
        // 내신 세부 교과목 존재 여부 확인(internalSubSubjectId 로 확인)
        InternalSubSubjectDto existsInternalSubSubject = new InternalSubSubjectDto();
        existsInternalSubSubject.setInternalSubSubjectId(internalSubSubjectId);
        existsInternalSubSubject = internalSubSubjectService.read(existsInternalSubSubject);
        if (existsInternalSubSubject == null) {
            return "redirect:/admin/internalSubSubjects";
        }

        // 내신 세부 교과목 삭제
        boolean result = internalSubSubjectService.delete(internalSubSubjectId);
        if (result) {
            redirectAttributes.addFlashAttribute("successMessage", "내신 세부 교과목 삭제가 완료되었습니다.");
            return "redirect:/admin/internalSubSubjects"; // 내신 세부 교과목 목록으로 리다이렉트
        }

        // 내신 세부 교과목 삭제 실패
        redirectAttributes.addFlashAttribute("errorMessage", "내신 세부 교과목 삭제에 실패했습니다.");
        return "redirect:/admin/internalSubSubjects/" + internalSubSubjectId; // 내신 세부 교과목 상세로 리다이렉트
    }

    // 내신 세부 교과목 이름 중복 확인
    @PostMapping("/existsInternalSubSubjectName")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> existsInternalSubSubjectName(
        @RequestParam String internalSubSubjectName,
        @RequestParam int internalSubjectId
    ) {
        // 내신 세부 교과목 존재 여부 확인
        InternalSubSubjectDto internalSubSubject = new InternalSubSubjectDto();
        internalSubSubject.setInternalSubSubjectName(internalSubSubjectName);
        internalSubSubject.setInternalSubjectId(internalSubjectId);
        InternalSubSubjectDto existsInternalSubSubject = internalSubSubjectService.read(internalSubSubject);

        Map<String, Object> response = new HashMap<>();

        // 내신 세부 교과목이 존재하는 경우
        if (existsInternalSubSubject != null) {
            response.put("exists", true);
        } 
        // 내신 세부 교과목이 존재하지 않는 경우
        else {
            response.put("exists", false);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }
}
