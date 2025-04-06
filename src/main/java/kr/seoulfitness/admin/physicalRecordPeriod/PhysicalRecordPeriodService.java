package kr.seoulfitness.admin.physicalRecordPeriod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.seoulfitness.libs.Pagination;

@Service
public class PhysicalRecordPeriodService {

    @Autowired
    private PhysicalRecordPeriodDao physicalRecordPeriodDao;

    // 실기 기록 등록 기간 등록
    public boolean create(PhysicalRecordPeriodDto physicalRecordPeriodDto) {
        return physicalRecordPeriodDao.insertPhysicalRecordPeriod(physicalRecordPeriodDto) > 0;
    }

    // 실기 기록 등록 기간 목록 조회
    public Map<String, Object> list(Map<String, Object> params) {
        // 전체 게시글 수 조회
        int totalCount = physicalRecordPeriodDao.getTotalCount(params);

        // 페이지네이션 정보 생성
        params.put("totalCount", totalCount);
        Pagination pagination = new Pagination(params);

        // 페이징된 게시글 목록 조회
        params.put("offset", pagination.offset());
        List<PhysicalRecordPeriodDto> physicalRecordPeriods = physicalRecordPeriodDao.getPhysicalRecordPeriods(params);

        // 결과 맵 생성
        Map<String, Object> result = new HashMap<>();
        result.put("physicalRecordPeriods", physicalRecordPeriods);
        result.put("pagination", pagination);

        return result;
    }

    // 실기 기록 등록 기간 상세 조회
    public PhysicalRecordPeriodDto read(int physicalRecordPeriodId) {
        return physicalRecordPeriodDao.getPhysicalRecordPeriod(physicalRecordPeriodId);
    }

    // 실기 기록 등록 기간 수정
    public boolean update(PhysicalRecordPeriodDto physicalRecordPeriodDto) {
        return physicalRecordPeriodDao.updatePhysicalRecordPeriod(physicalRecordPeriodDto) > 0;
    }   

    // 실기 기록 등록 기간 삭제
    public boolean delete(int physicalRecordPeriodId) {
        return physicalRecordPeriodDao.deletePhysicalRecordPeriod(physicalRecordPeriodId) > 0;
    }
}
