package kr.seoulfitness.admin.internalSubSubject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class InternalSubSubjectService {
    
    @Autowired
    private InternalSubSubjectDao internalSubSubjectDao;

    // 내신 세부 교과목 등록
    public boolean create(InternalSubSubjectDto internalSubSubject) {
        return internalSubSubjectDao.insertInternalSubSubject(internalSubSubject) > 0;
    }

    // 내신 세부 교과목 목록
    public Map<String, Object> list(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = internalSubSubjectDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<InternalSubSubjectDto> internalSubSubjects = internalSubSubjectDao.getInternalSubSubjects(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("internalSubSubjects", internalSubSubjects);
        result.put("pagination", pagination);

        return result;
    }

    // 내신 세부 교과목 상세보기
    public InternalSubSubjectDto read(int internalSubSubjectId) {
        return internalSubSubjectDao.getInternalSubSubject(internalSubSubjectId);
    }

    // 내신 세부 교과목 수정
    public boolean update(InternalSubSubjectDto internalSubSubject) {
        return internalSubSubjectDao.updateInternalSubSubject(internalSubSubject) > 0;
    }

    // 내신 세부 교과목 삭제
    public boolean delete(int internalSubSubjectId) {
        return internalSubSubjectDao.deleteInternalSubSubject(internalSubSubjectId) > 0;
    }
}
