package cm.softwareservices.productservice.repository;

import cm.softwareservices.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
