package org.glsid.transactionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "ACCOUNT-SERVICE", fallback = AccountRestClientFallback.class)
public interface AccountRestClient {

    @GetMapping("/accounts/{id}")
    AccountDto getAccountById(@PathVariable("id") UUID id);

    @PutMapping("/accounts/{id}/balance")
    AccountDto updateBalance(@PathVariable("id") UUID id, @RequestParam("newBalance") double newBalance);
}