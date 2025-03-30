package kr.seoulfitness.admin.earlyAdmissionInternal;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarlyAdmissionInternalService {
    
    @Autowired
    private EarlyAdmissionInternalDao earlyAdmissionInternalDao;

    // 수시 모집 내신 등록
    public EarlyAdmissionInternalDto create(EarlyAdmissionInternalDto earlyAdmissionInternal) {        
        return earlyAdmissionInternalDao.insertEarlyAdmissionInternal(earlyAdmissionInternal);
    }

    // 수시 모집 내신 상세보기
    public EarlyAdmissionInternalDto read(Map<String, Object> params) {
        return earlyAdmissionInternalDao.getEarlyAdmissionInternal(params);
    }

    // 수시 모집 내신 수정
    public boolean update(EarlyAdmissionInternalDto earlyAdmissionInternal) {        
        return earlyAdmissionInternalDao.updateEarlyAdmissionInternal(earlyAdmissionInternal) > 0;
    }
    
}
