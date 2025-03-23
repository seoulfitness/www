package kr.seoulfitness.admin.highSchool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class HighSchoolService {
    
    @Autowired
    private HighSchoolDao highSchoolDao;

    // 고등학교 등록
    public boolean create(HighSchoolDto highSchool) {        
        return highSchoolDao.insertHighSchool(highSchool) > 0;
    }

    // 고등학교 목록
    public Map<String, Object> findAll(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = highSchoolDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<HighSchoolDto> highSchools = highSchoolDao.getHighSchools(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("highSchools", highSchools);
        result.put("pagination", pagination);

        return result;
    }

    // 고등학교 상세보기
    public HighSchoolDto find(int highSchoolId) {
        return highSchoolDao.getHighSchool(highSchoolId);
    }

    // 고등학교 수정
    public boolean update(HighSchoolDto highSchool) {        
        return highSchoolDao.updateHighSchool(highSchool) > 0;
    }

    // 고등학교 삭제
    public boolean delete(int highSchoolId) {
        return highSchoolDao.deleteHighSchool(highSchoolId) > 0;
    }
}
