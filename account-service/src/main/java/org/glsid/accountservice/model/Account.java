package src.main.java.org.glsid.accountservice.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {
    @Id
    private UUID id;
    private String customerReference;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private String currency;
    private double balance;

    @PrePersist
    public void ensureId() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }
}