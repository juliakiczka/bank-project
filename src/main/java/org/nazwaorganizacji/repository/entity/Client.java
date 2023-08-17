package org.nazwaorganizacji.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity//encja jest czymś co zapisujemy do bazy danych
@Table(name = "users")
/*
@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
 =
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id //klucz główny
    @GeneratedValue //id sam się będzie inkrementował
    @Column(name = "user_id")
    private Long id;
    @Column(name = "first_name")
    private String name;
    @Column(name = "mail")

    private String email;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//tryb EAGER oznacza, że hibernate będzie nam wszystko zaciągał


    @JoinColumn(name = "user_id")
    //relacja jednego klienta do wielu kont
    private List<Account> accounts;
//    @Transient //pole pomijane przez hibernate
//    private double balance;

    //hibernate potrzebuje konstruktora bezargumentowego i gettery i settery!


    public Client(String name, String email, List<Account> accounts) {
        this.name = name;
        this.email = email;
        this.accounts = accounts;
    }

    public double getBalance() {
        if (!accounts.isEmpty()) {
            return accounts.get(0).getBalance();
        }
        return 0;
    }

    public void setBalance(double newBalance) {
        if (!accounts.isEmpty()) {
            accounts.get(0).setBalance(newBalance);
        }
    }
}
