package kr.seoulfitness.branch;

import kr.seoulfitness.libs.ExtDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BranchDto extends ExtDto {
    private int branchId;
    private String branchName;
    private String branchAddress;
    private String branchPhone;
}

/*
CREATE TABLE BRANCHES (
    BRANCH_ID INT AUTO_INCREMENT PRIMARY KEY,
    BRANCH_NAME VARCHAR(50) NOT NULL,
    BRANCH_ADDRESS VARCHAR(255) NOT NULL,
    BRANCH_PHONE VARCHAR(20) NOT NULL,
    CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CREATED_BY INT,
    UPDATED_BY INT
);
*/