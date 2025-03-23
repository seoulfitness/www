package kr.seoulfitness.admin.criteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class CriteriaService {
    
    @Autowired
    private CriteriaDao criteriaDao;

    // 입시 요강 등록
    public boolean create(CriteriaDto criteria) {
        return criteriaDao.insertCriteria(criteria) > 0;
    }

    // 입시 요강 목록
    public Map<String, Object> findAll(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = criteriaDao.getTotalCount(params);
        params.put("totalCount", totalCount);

        // 페이지네이션 정보 생성
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<CriteriaDto> criterias = criteriaDao.getCriterias(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("criterias", criterias);
        result.put("pagination", pagination);

        return result;
    }

    // 입시 요강 상세보기
    public CriteriaDto find(int criteriaId) {
        return criteriaDao.getCriteria(criteriaId);
    }

    // 입시 요강 수정
    public boolean update(CriteriaDto criteria) {
        return criteriaDao.updateCriteria(criteria) > 0;
    }

    // 입시 요강 삭제
    public boolean delete(int criteriaId) {
        return criteriaDao.deleteCriteria(criteriaId) > 0;
    }
}
