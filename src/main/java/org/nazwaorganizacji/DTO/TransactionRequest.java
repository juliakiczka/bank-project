package org.nazwaorganizacji.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransactionRequest {
    private double amount;
    private String currency;
    private long fromAccountId;
    private long toAccountId;
}
