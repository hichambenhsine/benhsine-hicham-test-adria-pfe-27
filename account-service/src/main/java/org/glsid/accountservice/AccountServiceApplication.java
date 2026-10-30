package src.main.java.org.glsid.accountservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import src.main.java.org.glsid.accountservice.model.Account;
import src.main.java.org.glsid.accountservice.model.AccountStatus;
import src.main.java.org.glsid.accountservice.repository.AccountRepository;

import java.util.UUID;

@SpringBootApplication
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountRepository accountRepository) {
        return args -> {
            accountRepository.save(Account.builder()
                    .id(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                    .customerReference("CUST-001")
                    .status(AccountStatus.ACTIVE)
                    .currency("MAD")
                    .balance(50000.0)
                    .build());

            accountRepository.save(Account.builder()
                    .id(UUID.fromString("22222222-2222-2222-2222-222222222222"))
                    .customerReference("CUST-002")
                    .status(AccountStatus.ACTIVE)
                    .currency("MAD")
                    .balance(12000.0)
                    .build());
        };
    }
}
