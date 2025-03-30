package kr.seoulfitness.admin.regularAdmissionEnglish;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegularAdmissionEnglishService {
    
    @Autowired
    private RegularAdmissionEnglishDao regularAdmissionEnglishDao;

    // 정시 모집 영어 등록
    public RegularAdmissionEnglishDto create(RegularAdmissionEnglishDto regularAdmissionEnglish) {        
        return regularAdmissionEnglishDao.insertRegularAdmissionEnglish(regularAdmissionEnglish);
    }

    // 정시 모집 영어 상세보기
    public RegularAdmissionEnglishDto read(Map<String, Object> params) {
        return regularAdmissionEnglishDao.getRegularAdmissionEnglish(params);
    }

    // 정시 모집 영어 수정
    public boolean update(RegularAdmissionEnglishDto regularAdmissionEnglish) {        
        return regularAdmissionEnglishDao.updateRegularAdmissionEnglish(regularAdmissionEnglish) > 0;
    }
    
}
