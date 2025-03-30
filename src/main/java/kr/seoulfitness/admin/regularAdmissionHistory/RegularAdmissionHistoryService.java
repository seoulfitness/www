package kr.seoulfitness.admin.regularAdmissionHistory;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularAdmissionHistoryService {
    
    @Autowired
    private RegularAdmissionHistoryDao regularAdmissionHistoryDao;

    // 정시 모집 한국사 등록
    public RegularAdmissionHistoryDto create(RegularAdmissionHistoryDto regularAdmissionHistory) {        
        return regularAdmissionHistoryDao.insertRegularAdmissionHistory(regularAdmissionHistory);
    }

    // 정시 모집 한국사 상세보기
    public RegularAdmissionHistoryDto read(Map<String, Object> params) {
        return regularAdmissionHistoryDao.getRegularAdmissionHistory(params);
    }

    // 정시 모집 한국사 수정
    public boolean update(RegularAdmissionHistoryDto regularAdmissionHistory) {        
        return regularAdmissionHistoryDao.updateRegularAdmissionHistory(regularAdmissionHistory) > 0;
    }
    
}
