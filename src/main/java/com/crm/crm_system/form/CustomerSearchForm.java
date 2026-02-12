package com.crm.crm_system.form;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

@Data
public class CustomerSearchForm {
    private String companyName;   // 会社名（部分一致）
    private String companyNameKana;   // 会社名(カナ)（部分一致）
    private String industryCode;   // 業種（完全一致）
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;   // 登録日（From）
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;   // 登録日（To）
    
    private Boolean isEmailAllowed;   // メール配信（nullなら全件）
}




