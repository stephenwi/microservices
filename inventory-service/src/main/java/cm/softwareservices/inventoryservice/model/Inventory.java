package cm.softwareservices.inventoryservice.model;

import javax.persistence.*;
import org.springframework.data.annotation.Id;
import lombok.*;

@Table(name = "inventory")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String skuCode;
    private Integer quantity;
}
