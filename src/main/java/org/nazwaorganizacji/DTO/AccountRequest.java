package org.nazwaorganizacji.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountRequest {
    private double balance;
    private String currency;
    private Long userId;
}
