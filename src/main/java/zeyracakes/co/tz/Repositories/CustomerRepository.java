package zeyracakes.co.tz.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zeyracakes.co.tz.Models.Entities.Users.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    public Optional<Customer> findByEmail(String email);
    public Optional<Customer> findByPhoneNumber(String phoneNumber);

}
