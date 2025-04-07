package kr.seoulfitness.admin.branch;

import java.util.Date;
import java.util.List;

import kr.seoulfitness.admin.branchManager.BranchManagerDto;
import lombok.Data;

@Data
public class BranchDto {
    // 지점 정보
    private int branchId;           // 지점 아이디
    private String branchName;      // 지점 이름
    private String branchAddress;   // 지점 주소
    private String branchPhone;     // 지점 전화번호
    private Date createdAt;       // 생성일시
    private Date updatedAt;       // 수정일시
    private String createdBy;       // 생성자
    private String updatedBy;       // 수정한 사람

    // 등록한 사람, 수정한 사람 정보
    private String createdUserName; // 등록한 사람 이름
    private String updatedUserName; // 수정한 사람 이름
    private String createdUserPhone; // 등록한 사람 전화번호
    private String updatedUserPhone; // 수정한 사람 전화번호

    // 지점 관리자 정보
    private List<BranchManagerDto> branchManagers;
}
