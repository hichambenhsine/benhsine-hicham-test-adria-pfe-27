package org.glsid.transactionservice.feign;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountRestClientFallback implements AccountRestClient {
    @Override
    public AccountDto getAccountById(UUID id) {
        AccountDto fallback = new AccountDto();
        fallback.setId(id);
        fallback.setStatus("BLOCKED");
        fallback.setBalance(0.0);
        return fallback;
    }

    @Override
    public AccountDto updateBalance(UUID id, double newBalance) {
        throw new RuntimeException("account-service indisponible. Opération annulée par le circuit breaker.");
    }
}
