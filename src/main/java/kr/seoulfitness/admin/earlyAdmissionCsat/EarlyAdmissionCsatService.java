package kr.seoulfitness.admin.earlyAdmissionCsat;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarlyAdmissionCsatService {
    
    @Autowired
    private EarlyAdmissionCsatDao earlyAdmissionCsatDao;

    // 수시 모집 등록
    public EarlyAdmissionCsatDto create(EarlyAdmissionCsatDto earlyAdmissionCsat) {        
        return earlyAdmissionCsatDao.insertEarlyAdmissionCsat(earlyAdmissionCsat);
    }

    // 수시 모집 상세보기
    public EarlyAdmissionCsatDto read(Map<String, Object> params) {
        return earlyAdmissionCsatDao.getEarlyAdmissionCsat(params);
    }

    // 수시 모집 수정
    public boolean update(EarlyAdmissionCsatDto earlyAdmissionCsat) {        
        return earlyAdmissionCsatDao.updateEarlyAdmissionCsat(earlyAdmissionCsat) > 0;
    }
    
}
