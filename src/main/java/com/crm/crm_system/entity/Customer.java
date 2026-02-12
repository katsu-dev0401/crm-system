package com.crm.crm_system.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Customer {
    private Integer customerId;   // 顧客コード
    @NotBlank(message = "会社名を入力してください")
    @Size(max = 100)
    private String companyName;   // 会社名
    @Size(max = 100)
    @Pattern(regexp = "^[ァ-ンヴー]*$", message = "全角カタカナで入力してください")
    private String companyNameKana;   // 会社名(カナ)
    @NotBlank
    private String industryCode;   // 業種
    private String internalRep;   // 自社担当
    private String contactPerson;   // ご担当者
    private String telNo;   // 電話番号
    private String email;   // メールアドレス
    // @Size(max = 8)
    // @Pattern(regexp = "^[0-9-]*$")
    @Pattern(regexp = "^[0-9]{7}$", message = "郵便番号はハイフンなしの7桁で入力してください")
    private String postalCode;   // 郵便番号
    @Size(max = 10)
    private String prefecture;   // 都道府県
    @Size(max = 100)
    private String city;   // 市区町村
    @Size(max = 100)
    private String address1;   // 番地
    @Size(max = 100)
    private String address2;   // 建物名
    private String department;   // 部署
    private String positionName;   // 役職
    @Size(max = 1000)
    private String remarks;   // 備考
    private Boolean isEmailAllowed;   // メール配信 (true=可, false=不可)
    private Boolean isDeleted;   // 削除フラグ (true=削除済み, false=有効)
    private LocalDateTime createdAt;   // 登録日
    private LocalDateTime updatedAt;   // 更新日
    
    // 担当者リスト（1対多の関係）
    @Valid
    private List<Contact> contacts = new ArrayList<>();
}