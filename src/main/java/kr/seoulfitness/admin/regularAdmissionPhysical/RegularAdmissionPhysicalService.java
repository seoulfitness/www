package kr.seoulfitness.admin.regularAdmissionPhysical;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularAdmissionPhysicalService {
    
    @Autowired
    private RegularAdmissionPhysicalDao regularAdmissionPhysicalDao;

    // 정시 모집 실기 등록
    public RegularAdmissionPhysicalDto create(RegularAdmissionPhysicalDto regularAdmissionPhysical) {        
        return regularAdmissionPhysicalDao.insertRegularAdmissionPhysical(regularAdmissionPhysical);
    }

    // 정시 모집 실기 상세보기
    public RegularAdmissionPhysicalDto read(Map<String, Object> params) {
        return regularAdmissionPhysicalDao.getRegularAdmissionPhysical(params);
    }

    // 정시 모집 실기 수정
    public boolean update(RegularAdmissionPhysicalDto regularAdmissionPhysical) {        
        return regularAdmissionPhysicalDao.updateRegularAdmissionPhysical(regularAdmissionPhysical) > 0;
    }
    
}
