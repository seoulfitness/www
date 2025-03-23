package kr.seoulfitness.admin.branch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class BranchService {
    
    @Autowired
    private BranchDao branchDao;

    // 지점 등록
    public boolean create(BranchDto branch) {        
        return branchDao.insertBranch(branch) > 0;
    }

    // 지점 목록
    public Map<String, Object> findAll(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = branchDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<BranchDto> branches = branchDao.getBranches(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("branches", branches);
        result.put("pagination", pagination);

        return result;
    }

    // 지점 상세보기
    public BranchDto find(int branchId) {
        return branchDao.getBranch(branchId);
    }

    // 지점 수정
    public boolean update(BranchDto branch) {        
        return branchDao.updateBranch(branch) > 0;
    }

    // 지점 삭제
    public boolean delete(int branchId) {
        return branchDao.deleteBranch(branchId) > 0;
    }
}
