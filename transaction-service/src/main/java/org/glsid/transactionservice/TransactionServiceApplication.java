package org.glsid.transactionservice;

import org.glsid.transactionservice.model.Transaction;
import org.glsid.transactionservice.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class TransactionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(TransactionRepository transactionRepository) {
        return args -> {
            // Référence aux UUIDs créés dans account-service
            UUID accountId1 = UUID.fromString("11111111-1111-1111-1111-111111111111");
            UUID accountId2 = UUID.fromString("22222222-2222-2222-2222-222222222222");

            transactionRepository.save(Transaction.builder()
                    .id(UUID.randomUUID())
                    .accountId(accountId1)
                    .amount(BigDecimal.valueOf(250.00))
                    .currency("MAD")
                    .merchantCategory("SUPERMARKET")
                    .country("MA")
                    .createdAt(Instant.now())
                    .build());

            transactionRepository.save(Transaction.builder()
                    .id(UUID.randomUUID())
                    .accountId(accountId1)
                    .amount(BigDecimal.valueOf(150.00))
                    .currency("MAD")
                    .merchantCategory("RESTAURANT")
                    .country("MA")
                    .createdAt(Instant.now())
                    .build());

            transactionRepository.save(Transaction.builder()
                    .id(UUID.randomUUID())
                    .accountId(accountId2)
                    .amount(BigDecimal.valueOf(400.00))
                    .currency("MAD")
                    .merchantCategory("TECH_STORE")
                    .country("MA")
                    .createdAt(Instant.now())
                    .build());
        };
    }
}
