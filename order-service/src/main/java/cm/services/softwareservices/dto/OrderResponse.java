package cm.services.softwareservices.dto;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private String buyerCode;
    private String reductionCode;
    private String dataTransaction;
}
