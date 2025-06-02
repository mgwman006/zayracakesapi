package zeyracakes.co.tz.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zeyracakes.co.tz.Models.Entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
