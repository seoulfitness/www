package kr.seoulfitness.admin.earlyAdmission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class EarlyAdmissionService {
    
    @Autowired
    private EarlyAdmissionDao earlyAdmissionDao;

    /**
     * 수시 모집 등록
     * @param earlyAdmission 수시 모집 정보
     * @return 등록 성공 여부
     */
    public boolean create(EarlyAdmissionDto earlyAdmission) {        
        return earlyAdmissionDao.insertEarlyAdmission(earlyAdmission) > 0;
    }

    /**
     * 수시 모집 목록 조회
     * @param params 검색 파라미터
     * @return 수시 모집 목록과 페이지네이션 정보
     */
    public Map<String, Object> findAll(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = earlyAdmissionDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<EarlyAdmissionDto> earlyAdmissions = earlyAdmissionDao.getEarlyAdmissions(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("earlyAdmissions", earlyAdmissions);
        result.put("pagination", pagination);

        return result;
    }

    /**
     * 수시 모집 상세보기
     * @param earlyAdmissionId 수시 모집 아이디
     * @return 수시 모집 정보
     */
    public EarlyAdmissionDto find(Map<String, Object> params) {
        return earlyAdmissionDao.getEarlyAdmission(params);
    }

    /**
     * 수시 모집 수정
     * @param earlyAdmission 수시 모집 정보
     * @return 수정 성공 여부
     */
    public boolean update(EarlyAdmissionDto earlyAdmission) {        
        return earlyAdmissionDao.updateEarlyAdmission(earlyAdmission) > 0;
    }

    /**
     * 수시 모집 삭제
     * @param earlyAdmissionId 수시 모집 아이디
     * @return 삭제 성공 여부
     */
    public boolean delete(int earlyAdmissionId) {
        return earlyAdmissionDao.deleteEarlyAdmission(earlyAdmissionId) > 0;
    }
}
