package app.store.microserviceclient.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "clients")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity extends PersonEntity {

    @Column(unique = true, nullable = false, name = "client_id")
    private Long clientId;
    private String password;
    private Boolean status;


}
