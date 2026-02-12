package com.crm.crm_system.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Contact {
    private Integer contactId;
    private Integer customerId;
    @NotBlank(message = "自社担当を選択してください")
    private String internalRep;
    @NotBlank(message = "自社担当を選択してください")
    @Size(max = 60)
    private String contactName;
    @Size(max = 100)
    private String department;
    @Size(max = 30)
    @Pattern(regexp = "^[0-9]+$", message = "電話番号はハイフンなしの数字のみで入力してください")
    private String telNo;
    @Size(max = 255)
    @Email(message = "正しいメールアドレスの形式で入力してください")
    private String email;
}

