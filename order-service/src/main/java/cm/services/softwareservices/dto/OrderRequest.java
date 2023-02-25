package cm.services.softwareservices.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private List<OrderItemsRequest> orderItemsRequestList;
}
