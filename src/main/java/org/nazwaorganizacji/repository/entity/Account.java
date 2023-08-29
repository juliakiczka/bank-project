package org.nazwaorganizacji.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;
    @Column(name = "balance")
    private double balance;
    @Column(name = "currency")
    private String currency;
    @Column(name = "user_id")
    private Long userId;

    public Account(double balance, String currency) {
        this.balance = balance;
        this.currency = currency;
    }
}
