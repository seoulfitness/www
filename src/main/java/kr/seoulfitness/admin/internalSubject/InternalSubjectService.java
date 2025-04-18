package kr.seoulfitness.admin.internalSubject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class InternalSubjectService {
    
    @Autowired
    private InternalSubjectDao internalSubjectDao;

    // 내신 교과목 등록
    public boolean create(InternalSubjectDto internalSubject) {
        return internalSubjectDao.create(internalSubject) > 0;
    }

    // 내신 교과목 목록
    public Map<String, Object> list(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = internalSubjectDao.totalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<InternalSubjectDto> internalSubjects = internalSubjectDao.list(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("internalSubjects", internalSubjects);
        result.put("pagination", pagination);

        return result;
    }

    // 내신 교과목 상세보기
    public InternalSubjectDto read(InternalSubjectDto internalSubject) {
        return internalSubjectDao.read(internalSubject);
    }

    // 내신 교과목 수정
    public boolean update(InternalSubjectDto internalSubject) {
        return internalSubjectDao.update(internalSubject) > 0;
    }

    // 내신 교과목 삭제
    public boolean delete(int internalSubjectId) {
        return internalSubjectDao.delete(internalSubjectId) > 0;
    }
}
