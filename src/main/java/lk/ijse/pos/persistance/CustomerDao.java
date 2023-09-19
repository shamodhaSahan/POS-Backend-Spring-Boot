package lk.ijse.pos.persistance;

import lk.ijse.pos.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 8/3/2023
 * Time : 11:38 PM
 */

public interface CustomerDao extends CrudRepository<Customer, String> {
    Optional<Customer> findCustomerByNic(String nic);
}
