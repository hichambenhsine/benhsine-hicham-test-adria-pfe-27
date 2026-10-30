package src.main.java.org.glsid.accountservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import src.main.java.org.glsid.accountservice.model.Account;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findByCustomerReference(String customerReference);
}
