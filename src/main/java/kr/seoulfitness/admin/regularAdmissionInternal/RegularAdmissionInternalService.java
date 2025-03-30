package kr.seoulfitness.admin.regularAdmissionInternal;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularAdmissionInternalService {
    
    @Autowired
    private RegularAdmissionInternalDao regularAdmissionInternalDao;

    // 정시 모집 내부 등록
    public RegularAdmissionInternalDto create(RegularAdmissionInternalDto regularAdmissionInternal) {        
        return regularAdmissionInternalDao.insertRegularAdmissionInternal(regularAdmissionInternal);
    }

    // 정시 모집 내부 상세보기
    public RegularAdmissionInternalDto read(Map<String, Object> params) {
        return regularAdmissionInternalDao.getRegularAdmissionInternal(params);
    }

    // 정시 모집 내부 수정
    public boolean update(RegularAdmissionInternalDto regularAdmissionInternal) {        
        return regularAdmissionInternalDao.updateRegularAdmissionInternal(regularAdmissionInternal) > 0;
    }
    
}
