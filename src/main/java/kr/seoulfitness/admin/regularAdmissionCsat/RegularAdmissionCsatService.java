package kr.seoulfitness.admin.regularAdmissionCsat;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularAdmissionCsatService {
    
    @Autowired
    private RegularAdmissionCsatDao regularAdmissionCsatDao;

    // 정시 모집 등록
    public RegularAdmissionCsatDto create(RegularAdmissionCsatDto regularAdmissionCsat) {        
        return regularAdmissionCsatDao.insertRegularAdmissionCsat(regularAdmissionCsat);
    }

    // 정시 모집 상세보기
    public RegularAdmissionCsatDto read(Map<String, Object> params) {
        return regularAdmissionCsatDao.getRegularAdmissionCsat(params);
    }

    // 정시 모집 수정
    public boolean update(RegularAdmissionCsatDto regularAdmissionCsat) {        
        return regularAdmissionCsatDao.updateRegularAdmissionCsat(regularAdmissionCsat) > 0;
    }
    
}
