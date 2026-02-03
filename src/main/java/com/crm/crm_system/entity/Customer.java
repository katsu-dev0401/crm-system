package com.crm.crm_system.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class Customer {
    private Integer customerId;   // PostgreSQLのSERIAL型に対応
    private String customerName;
    private String telNo;
    private String rankCode;
    private Integer isDeleted;    // PostgreSQLのSMALLINTはIntegerで受ける
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}