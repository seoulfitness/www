package kr.seoulfitness.admin.regularAdmissionPhysicalManAbsolute;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/regularAdmissionPhysicalManAbsolute")
public class RegularAdmissionPhysicalManAbsoluteController {

    @Autowired
    private RegularAdmissionPhysicalManAbsoluteService regularAdmissionPhysicalManAbsoluteService;

    // 정시 실기 남자 절대평가 점수 정보 등록 처리
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> create(
        RegularAdmissionPhysicalManAbsoluteDto regularAdmissionPhysicalManAbsolute, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        Map<String, Object> result = new HashMap<>();

        regularAdmissionPhysicalManAbsolute.setCreatedBy((String) session.getAttribute("userId"));
        regularAdmissionPhysicalManAbsolute.setUpdatedBy((String) session.getAttribute("userId"));
        RegularAdmissionPhysicalManAbsoluteDto createdRegularAdmissionPhysicalManAbsolute = regularAdmissionPhysicalManAbsoluteService.create(regularAdmissionPhysicalManAbsolute);
        if (createdRegularAdmissionPhysicalManAbsolute != null) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 입시 실기 남자 절대평가 점수 정보 등록이 완료되었습니다.");
            result.put("success", true);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "정시 입시 실기 남자 절대평가 점수 정보 등록에 실패했습니다.");
            result.put("success", false);
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    // 정시 실기 남자 절대평가 점수 정보 조회
    @GetMapping("/{regularAdmissionPhysicalAbsoluteId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> read(@PathVariable int regularAdmissionPhysicalAbsoluteId) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionPhysicalAbsoluteId", regularAdmissionPhysicalAbsoluteId);
        RegularAdmissionPhysicalManAbsoluteDto regularAdmissionPhysicalManAbsolute = regularAdmissionPhysicalManAbsoluteService.read(params);

        // 정시 실기 남자 절대평가 점수 정보
        Map<String, Object> result = new HashMap<>();
        result.put("regularAdmissionPhysicalAbsolute", regularAdmissionPhysicalManAbsolute);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    // 정시 실기 남자 절대평가 점수 정보 수정 처리
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> update(
        RegularAdmissionPhysicalManAbsoluteDto regularAdmissionPhysicalManAbsolute,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        Map<String, Object> result = new HashMap<>();

        regularAdmissionPhysicalManAbsolute.setUpdatedBy((String) session.getAttribute("userId"));
        if (regularAdmissionPhysicalManAbsoluteService.update(regularAdmissionPhysicalManAbsolute)) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 입시 실기 남자 절대평가 점수 정보 수정이 완료되었습니다.");
            result.put("success", true);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "정시 입시 실기 남자 절대평가 점수 정보 수정에 실패했습니다.");
            result.put("success", false);
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
}
