package kr.seoulfitness.admin.internalRecordPeriod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class InternalRecordPeriodService {

    @Autowired
    private InternalRecordPeriodDao internalRecordPeriodDao;

    // 내신 기록 등록 기간 등록
    public boolean create(InternalRecordPeriodDto internalRecordPeriodDto) {
        return internalRecordPeriodDao.insertInternalRecordPeriod(internalRecordPeriodDto) > 0;
    }

    // 내신 기록 등록 기간 목록 조회
    public Map<String, Object> list(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = internalRecordPeriodDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<InternalRecordPeriodDto> internalRecordPeriods = internalRecordPeriodDao.getInternalRecordPeriods(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("internalRecordPeriods", internalRecordPeriods);
        result.put("pagination", pagination);

        return result;
    }

    // 내신 기록 등록 기간 상세 조회
    public InternalRecordPeriodDto read(int internalRecordPeriodId) {
        return internalRecordPeriodDao.getInternalRecordPeriod(internalRecordPeriodId);
    }

    // 내신 기록 등록 기간 수정
    public boolean update(InternalRecordPeriodDto internalRecordPeriodDto) {
        return internalRecordPeriodDao.updateInternalRecordPeriod(internalRecordPeriodDto) > 0;
    }   

    // 내신 기록 등록 기간 삭제
    public boolean delete(int internalRecordPeriodId) {
        return internalRecordPeriodDao.deleteInternalRecordPeriod(internalRecordPeriodId) > 0;
    }
}
