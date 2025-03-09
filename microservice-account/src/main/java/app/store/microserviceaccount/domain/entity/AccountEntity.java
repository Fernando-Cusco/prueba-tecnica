package app.store.microserviceaccount.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "client_id")
    private Long clientId;
    @Column(unique = true, nullable = false, name = "account_number")
    private String accountNumber;
    @Column(nullable = false, name = "account_type")
    private String accountType;
    @Column(nullable = false)
    private BigDecimal amount;
    private Boolean status;

    @Column(name = "client_name")
    private String clientName;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementEntity> movements;

    public void addMovement(MovementEntity movement) {
        if (this.movements == null) {
            this.movements = new ArrayList<>();
        }
        this.movements.add(movement);
        movement.setAccount(this);
    }

}
