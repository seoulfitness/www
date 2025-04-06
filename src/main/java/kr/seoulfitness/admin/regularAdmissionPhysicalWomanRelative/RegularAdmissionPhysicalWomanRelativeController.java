package kr.seoulfitness.admin.regularAdmissionPhysicalWomanRelative;

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
@RequestMapping("/admin/regularAdmissionPhysicalWomanRelative")
public class RegularAdmissionPhysicalWomanRelativeController {

    @Autowired
    private RegularAdmissionPhysicalWomanRelativeService regularAdmissionPhysicalWomanRelativeService;

    // 정시 실기 여자 상대평가 점수 정보 등록 처리
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> create(
        RegularAdmissionPhysicalWomanRelativeDto regularAdmissionPhysicalWomanRelative, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        Map<String, Object> result = new HashMap<>();

        regularAdmissionPhysicalWomanRelative.setCreatedBy((String) session.getAttribute("userId"));
        regularAdmissionPhysicalWomanRelative.setUpdatedBy((String) session.getAttribute("userId"));
        RegularAdmissionPhysicalWomanRelativeDto createdRegularAdmissionPhysicalWomanRelative = regularAdmissionPhysicalWomanRelativeService.create(regularAdmissionPhysicalWomanRelative);
        if (createdRegularAdmissionPhysicalWomanRelative != null) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 입시 실기 여자 상대평가 점수 정보 등록이 완료되었습니다.");
            result.put("success", true);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "정시 입시 실기 여자 상대평가 점수 정보 등록에 실패했습니다.");
            result.put("success", false);
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    // 정시 실기 여자 상대평가 점수 정보 조회
    @GetMapping("/{regularAdmissionPhysicalRelativeId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> read(@PathVariable int regularAdmissionPhysicalRelativeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("regularAdmissionPhysicalRelativeId", regularAdmissionPhysicalRelativeId);
        RegularAdmissionPhysicalWomanRelativeDto regularAdmissionPhysicalWomanRelative = regularAdmissionPhysicalWomanRelativeService.read(params);

        // 정시 실기 여자 상대평가 점수 정보
        Map<String, Object> result = new HashMap<>();
        result.put("regularAdmissionPhysicalRelative", regularAdmissionPhysicalWomanRelative);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    // 정시 실기 여자 상대평가 점수 정보 수정 처리
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> update(
        RegularAdmissionPhysicalWomanRelativeDto regularAdmissionPhysicalWomanRelative,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        Map<String, Object> result = new HashMap<>();

        regularAdmissionPhysicalWomanRelative.setUpdatedBy((String) session.getAttribute("userId"));
        if (regularAdmissionPhysicalWomanRelativeService.update(regularAdmissionPhysicalWomanRelative)) {
            redirectAttributes.addFlashAttribute("successMessage", "정시 입시 실기 여자 상대평가 점수 정보 수정이 완료되었습니다.");
            result.put("success", true);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "정시 입시 실기 여자 상대평가 점수 정보 수정에 실패했습니다.");
            result.put("success", false);
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
}
