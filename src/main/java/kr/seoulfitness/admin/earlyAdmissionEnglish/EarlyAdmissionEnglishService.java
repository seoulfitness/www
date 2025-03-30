package kr.seoulfitness.admin.earlyAdmissionEnglish;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarlyAdmissionEnglishService {
    
    @Autowired
    private EarlyAdmissionEnglishDao earlyAdmissionEnglishDao;

    // 수시 모집 영어 등록
    public EarlyAdmissionEnglishDto create(EarlyAdmissionEnglishDto earlyAdmissionEnglish) {        
        return earlyAdmissionEnglishDao.insertEarlyAdmissionEnglish(earlyAdmissionEnglish);
    }

    // 수시 모집 영어 상세보기
    public EarlyAdmissionEnglishDto read(Map<String, Object> params) {
        return earlyAdmissionEnglishDao.getEarlyAdmissionEnglish(params);
    }

    // 수시 모집 영어 수정
    public boolean update(EarlyAdmissionEnglishDto earlyAdmissionEnglish) {        
        return earlyAdmissionEnglishDao.updateEarlyAdmissionEnglish(earlyAdmissionEnglish) > 0;
    }
    
}
