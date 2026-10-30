package org.glsid.transactionservice.feign;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountDto {
    private UUID id;
    private String customerReference;
    private String status;
    private String currency;
    private double balance;
}