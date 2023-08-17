package org.nazwaorganizacji.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // wzorzec projektowy, który pozwala tworzyć obiekt klasy jak rekordy (client.builder().name("Alek").build())
public class ClientResponse {
    private Long id;
    private String name;
    private String email;
    //  zamieniamy Account na Long, bo  polegamy na separacji warstw
    private List<Long> accounts;

}

