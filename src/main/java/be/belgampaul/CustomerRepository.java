package be.belgampaul;

/**
 * User: ikka
 * Date: 14.09.13
 * Time: 5:16
 */
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  List<Customer> findByLastName(String lastName);

}
