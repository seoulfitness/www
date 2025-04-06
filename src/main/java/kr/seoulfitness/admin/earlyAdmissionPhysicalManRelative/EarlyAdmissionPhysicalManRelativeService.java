package kr.seoulfitness.admin.earlyAdmissionPhysicalManRelative;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EarlyAdmissionPhysicalManRelativeService {
    
    @Autowired
    private EarlyAdmissionPhysicalManRelativeDao earlyAdmissionPhysicalManRelativeDao;

    // 수시 모집 실기 남자 상대평가 점수 등록
    public EarlyAdmissionPhysicalManRelativeDto create(EarlyAdmissionPhysicalManRelativeDto earlyAdmissionPhysicalManRelative) {        
        return earlyAdmissionPhysicalManRelativeDao.insertEarlyAdmissionPhysicalManRelative(earlyAdmissionPhysicalManRelative);
    }

    // 수시 모집 실기 남자 상대평가 점수 목록
    public List<EarlyAdmissionPhysicalManRelativeDto> getEarlyAdmissionPhysicalManRelativeList(Map<String, Object> params) {
        return earlyAdmissionPhysicalManRelativeDao.getEarlyAdmissionPhysicalManRelativeList(params);
    }

    // 수시 모집 실기 남자 상대평가 점수 정보 상세
    public EarlyAdmissionPhysicalManRelativeDto read(Map<String, Object> params) {
        return earlyAdmissionPhysicalManRelativeDao.getEarlyAdmissionPhysicalManRelative(params);
    }

    // 수시 모집 실기 남자 상대평가 점수 수정
    public boolean update(EarlyAdmissionPhysicalManRelativeDto earlyAdmissionPhysicalManRelative) {        
        return earlyAdmissionPhysicalManRelativeDao.updateEarlyAdmissionPhysicalManRelative(earlyAdmissionPhysicalManRelative) > 0;
    }

    // 수시 모집 실기 남자 상대평가 점수 삭제
    public boolean delete(EarlyAdmissionPhysicalManRelativeDto earlyAdmissionPhysicalManRelative) {
        return earlyAdmissionPhysicalManRelativeDao.deleteEarlyAdmissionPhysicalManRelative(earlyAdmissionPhysicalManRelative) > 0;
    }
    
}
