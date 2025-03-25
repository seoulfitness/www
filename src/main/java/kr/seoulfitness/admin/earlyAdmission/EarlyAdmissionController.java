package kr.seoulfitness.admin.earlyAdmission;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/early-admissions")
public class EarlyAdmissionController {

    @Autowired
    private EarlyAdmissionService earlyAdmissionService;

    // 입시(수시) 요강 정보
    @GetMapping("/api/{earlyAdmissionId}")
    public ResponseEntity<Map<String, Object>> earlyAdmissionGet(@PathVariable("earlyAdmissionId") int earlyAdmissionId) {
        // 입시(수시) 요강 정보 조회
        Map<String, Object> earlyAdmissionParams = new HashMap<>();
        earlyAdmissionParams.put("earlyAdmissionId", earlyAdmissionId);
        EarlyAdmissionDto earlyAdmission = earlyAdmissionService.find(earlyAdmissionParams);

        // JSON 형식으로 데이터 반환
        Map<String, Object> response = new HashMap<>();
        response.put("earlyAdmission", earlyAdmission);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
