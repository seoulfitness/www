package kr.seoulfitness.admin.csatRecordPeriod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class CsatRecordPeriodService {

    @Autowired
    private CsatRecordPeriodDao csatRecordPeriodDao;

    // 수능 기록 등록 기간 등록
    public boolean create(CsatRecordPeriodDto csatRecordPeriodDto) {
        return csatRecordPeriodDao.insertCsatRecordPeriod(csatRecordPeriodDto) > 0;
    }

    // 수능 기록 등록 기간 목록 조회
    public Map<String, Object> list(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = csatRecordPeriodDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<CsatRecordPeriodDto> csatRecordPeriods = csatRecordPeriodDao.getCsatRecordPeriods(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("csatRecordPeriods", csatRecordPeriods);
        result.put("pagination", pagination);

        return result;
    }

    // 수능 기록 등록 기간 상세 조회
    public CsatRecordPeriodDto read(int csatRecordPeriodId) {
        return csatRecordPeriodDao.getCsatRecordPeriod(csatRecordPeriodId);
    }

    // 수능 기록 등록 기간 수정
    public boolean update(CsatRecordPeriodDto csatRecordPeriodDto) {
        return csatRecordPeriodDao.updateCsatRecordPeriod(csatRecordPeriodDto) > 0;
    }   

    // 수능 기록 등록 기간 삭제
    public boolean delete(int csatRecordPeriodId) {
        return csatRecordPeriodDao.deleteCsatRecordPeriod(csatRecordPeriodId) > 0;
    }
}
