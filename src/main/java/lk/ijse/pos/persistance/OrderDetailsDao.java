package lk.ijse.pos.persistance;

import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.OrderItemPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 8/3/2023
 * Time : 11:38 PM
 */

@Repository
public interface OrderDetailsDao extends CrudRepository<OrderDetails, OrderItemPK> {
    Optional<OrderDetails> findOrderDetailsByItem(Item item);
}
