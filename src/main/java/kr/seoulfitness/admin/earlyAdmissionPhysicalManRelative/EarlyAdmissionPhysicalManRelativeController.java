package kr.seoulfitness.admin.earlyAdmissionPhysicalManRelative;

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
@RequestMapping("/admin/earlyAdmissionPhysicalManRelative")
public class EarlyAdmissionPhysicalManRelativeController {

    @Autowired
    private EarlyAdmissionPhysicalManRelativeService earlyAdmissionPhysicalManRelativeService;

    // 수시 실기 남자 상대평가 점수 정보 등록 처리
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> create(
        EarlyAdmissionPhysicalManRelativeDto earlyAdmissionPhysicalManRelative, 
        HttpSession session, 
        RedirectAttributes redirectAttributes
    ) {
        Map<String, Object> result = new HashMap<>();

        earlyAdmissionPhysicalManRelative.setCreatedBy((String) session.getAttribute("userId"));
        earlyAdmissionPhysicalManRelative.setUpdatedBy((String) session.getAttribute("userId"));
        EarlyAdmissionPhysicalManRelativeDto createdEarlyAdmissionPhysicalManRelative = earlyAdmissionPhysicalManRelativeService.create(earlyAdmissionPhysicalManRelative);
        if (createdEarlyAdmissionPhysicalManRelative != null) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 입시 실기 남자 상대평가 점수 정보 등록이 완료되었습니다.");
            result.put("success", true);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "수시 입시 실기 남자 상대평가 점수 정보 등록에 실패했습니다.");
            result.put("success", false);
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    // 수시 실기 남자 상대평가 점수 정보 조회
    @GetMapping("/{earlyAdmissionPhysicalRelativeId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> read(@PathVariable int earlyAdmissionPhysicalRelativeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("earlyAdmissionPhysicalRelativeId", earlyAdmissionPhysicalRelativeId);
        EarlyAdmissionPhysicalManRelativeDto earlyAdmissionPhysicalManRelative = earlyAdmissionPhysicalManRelativeService.read(params);

        // 수시 실기 남자 상대평가 점수 정보
        Map<String, Object> result = new HashMap<>();
        result.put("earlyAdmissionPhysicalRelative", earlyAdmissionPhysicalManRelative);

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    // 수시 실기 남자 상대평가 점수 정보 수정 처리
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> update(
        EarlyAdmissionPhysicalManRelativeDto earlyAdmissionPhysicalManRelative,
        HttpSession session,
        RedirectAttributes redirectAttributes
    ) {
        Map<String, Object> result = new HashMap<>();

        earlyAdmissionPhysicalManRelative.setUpdatedBy((String) session.getAttribute("userId"));
        if (earlyAdmissionPhysicalManRelativeService.update(earlyAdmissionPhysicalManRelative)) {
            redirectAttributes.addFlashAttribute("successMessage", "수시 입시 실기 남자 상대평가 점수 정보 수정이 완료되었습니다.");
            result.put("success", true);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "수시 입시 실기 남자 상대평가 점수 정보 수정에 실패했습니다.");
            result.put("success", false);
        }

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }
}
