package kr.seoulfitness.admin.csatSubject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class CsatSubjectService {
    
    @Autowired
    private CsatSubjectDao csatSubjectDao;

    // 학과 등록
    public boolean create(CsatSubjectDto csatSubject) {
        return csatSubjectDao.insertCsatSubject(csatSubject) > 0;
    }

    // 학과 목록
    public Map<String, Object> list(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = csatSubjectDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<CsatSubjectDto> csatSubjects = csatSubjectDao.getCsatSubjects(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("csatSubjects", csatSubjects);
        result.put("pagination", pagination);

        return result;
    }

    // 학과 상세보기
    public CsatSubjectDto read(int csatSubjectId) {
        return csatSubjectDao.getCsatSubject(csatSubjectId);
    }

    // 학과 수정
    public boolean update(CsatSubjectDto csatSubject) {
        return csatSubjectDao.updateCsatSubject(csatSubject) > 0;
    }

    // 학과 삭제
    public boolean delete(int csatSubjectId) {
        return csatSubjectDao.deleteCsatSubject(csatSubjectId) > 0;
    }
}
