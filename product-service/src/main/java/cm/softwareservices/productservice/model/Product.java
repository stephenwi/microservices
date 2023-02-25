package cm.softwareservices.productservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name ="product")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
