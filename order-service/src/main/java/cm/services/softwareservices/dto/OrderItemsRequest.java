package cm.services.softwareservices.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemsRequest {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
