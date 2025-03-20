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
        return branchManagerDao.create(branchManager) > 0;
    }

    // 지점 관리자 목록
    public Map<String, Object> list(int currentPage, int listCountPerPage, int pageCountPerPage, String keyword, int branchId) {
        // 전체 게시글 수 조회
        int totalCount = branchManagerDao.totalCount(keyword, branchId);

        // 페이지네이션 정보 생성
        Pagination pagination = new Pagination(currentPage, listCountPerPage, pageCountPerPage, totalCount);

        // 페이징된 게시글 목록 조회
        List<BranchManagerDto> branchManagers = branchManagerDao.list(pagination.offset(), listCountPerPage, keyword, branchId);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("branchManagers", branchManagers);
        result.put("pagination", pagination);
        result.put("keyword", keyword);

        return result;
    }

    // 지점 관리자 상세보기
    public BranchManagerDto read(int branchManagerId) {
        return branchManagerDao.read(branchManagerId);
    }

    // 지점 관리자 수정
    public boolean update(BranchManagerDto branchManager) {
        return branchManagerDao.update(branchManager) > 0;
    }

    // 지점 관리자 삭제
    public boolean delete(int branchManagerId, int branchId) {
        return branchManagerDao.delete(branchManagerId, branchId) > 0;
    }
}
