package kr.seoulfitness.admin.physicalSubject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class PhysicalSubjectService {
    
    @Autowired
    private PhysicalSubjectDao physicalSubjectDao;

    // 실기 교과목 등록
    public boolean create(PhysicalSubjectDto physicalSubject) {
        return physicalSubjectDao.insertPhysicalSubject(physicalSubject) > 0;
    }

    // 실기 교과목 목록
    public Map<String, Object> findAll(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = physicalSubjectDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<PhysicalSubjectDto> physicalSubjects = physicalSubjectDao.getPhysicalSubjects(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("physicalSubjects", physicalSubjects);
        result.put("pagination", pagination);

        return result;
    }

    // 실기 교과목 상세보기
    public PhysicalSubjectDto find(int physicalSubjectId) {
        return physicalSubjectDao.getPhysicalSubject(physicalSubjectId);
    }

    // 실기 교과목 수정
    public boolean update(PhysicalSubjectDto physicalSubject) {
        return physicalSubjectDao.updatePhysicalSubject(physicalSubject) > 0;
    }

    // 실기 교과목 삭제
    public boolean delete(int physicalSubjectId) {
        return physicalSubjectDao.deletePhysicalSubject(physicalSubjectId) > 0;
    }
}
