package kr.seoulfitness.admin.branchManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class BranchManagerService {
    
    @Autowired
    private BranchManagerDao branchManagerDao;

    // 지점 관리자 등록
    public boolean create(BranchManagerDto branchManager) {
        return branchManagerDao.insertBranchManager(branchManager) > 0;
    }

    // 지점 관리자 목록
    public Map<String, Object> list(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = branchManagerDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<BranchManagerDto> branchManagers = branchManagerDao.getBranchManagers(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("branchManagers", branchManagers);
        result.put("pagination", pagination);

        return result;
    }

    // 지점 관리자 상세보기
    public BranchManagerDto read(int branchManagerId) {
        return branchManagerDao.getBranchManager(branchManagerId);
    }

    // 지점 관리자 수정
    public boolean update(BranchManagerDto branchManager) {
        return branchManagerDao.updateBranchManager(branchManager) > 0;
    }

    // 지점 관리자 삭제
    public boolean delete(Map<String, Object> params) {
        return branchManagerDao.deleteBranchManager(params) > 0;
    }
}
