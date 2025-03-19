package kr.seoulfitness.branch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class BranchService {
    
    @Autowired
    private BranchDao branchDao;

    // 지점 등록
    public boolean create(BranchDto branch) {        
        return branchDao.create(branch) > 0;
    }

    // 지점 목록
    public Map<String, Object> list(int currentPage, int listCountPerPage, int pageCountPerPage, String keyword) {
        // 전체 게시글 수 조회
        int totalCount = branchDao.totalCount(keyword);

        // 페이지네이션 정보 생성
        Pagination pagination = new Pagination(currentPage, listCountPerPage, pageCountPerPage, totalCount);

        // 페이징된 게시글 목록 조회
        List<BranchDto> branches = branchDao.list(pagination.offset(), listCountPerPage, keyword);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("branches", branches);
        result.put("pagination", pagination);
        result.put("keyword", keyword);

        return result;
    }

    // 지점 상세보기
    public BranchDto read(int branchId) {
        return branchDao.read(branchId);
    }

    // 지점 수정
    public boolean update(BranchDto branch) {        
        return branchDao.update(branch) > 0;
    }

    // 지점 삭제
    public boolean delete(int branchId) {
        return branchDao.delete(branchId) > 0;
    }
}
