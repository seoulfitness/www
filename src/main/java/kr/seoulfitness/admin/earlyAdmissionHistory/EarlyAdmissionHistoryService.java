package kr.seoulfitness.admin.earlyAdmissionHistory;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarlyAdmissionHistoryService {
    
    @Autowired
    private EarlyAdmissionHistoryDao earlyAdmissionHistoryDao;

    // 수시 모집 한국사 등록
    public EarlyAdmissionHistoryDto create(EarlyAdmissionHistoryDto earlyAdmissionHistory) {        
        return earlyAdmissionHistoryDao.insertEarlyAdmissionHistory(earlyAdmissionHistory);
    }

    // 수시 모집 한국사 상세보기
    public EarlyAdmissionHistoryDto read(Map<String, Object> params) {
        return earlyAdmissionHistoryDao.getEarlyAdmissionHistory(params);
    }

    // 수시 모집 한국사 수정
    public boolean update(EarlyAdmissionHistoryDto earlyAdmissionHistory) {        
        return earlyAdmissionHistoryDao.updateEarlyAdmissionHistory(earlyAdmissionHistory) > 0;
    }
    
}
