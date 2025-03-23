package kr.seoulfitness.admin.admission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class AdmissionService {
    
    @Autowired
    private AdmissionDao admissionDao;

    // 입시 요강 등록
    public boolean create(AdmissionDto admission) {
        return admissionDao.insertAdmission(admission) > 0;
    }

    // 입시 요강 목록
    public Map<String, Object> findAll(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = admissionDao.getTotalCount(params);
        params.put("totalCount", totalCount);

        // 페이지네이션 정보 생성
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<AdmissionDto> admissions = admissionDao.getAdmissions(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("admissions", admissions);
        result.put("pagination", pagination);

        return result;
    }

    // 입시 요강 상세보기
    public AdmissionDto find(int admissionId) {
        return admissionDao.getAdmission(admissionId);
    }

    // 입시 요강 수정
    public boolean update(AdmissionDto admission) {
        return admissionDao.updateAdmission(admission) > 0;
    }

    // 입시 요강 삭제
    public boolean delete(int admissionId) {
        return admissionDao.deleteAdmission(admissionId) > 0;
    }
}
