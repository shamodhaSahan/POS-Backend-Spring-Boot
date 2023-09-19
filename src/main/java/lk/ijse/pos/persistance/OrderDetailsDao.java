package lk.ijse.pos.persistance;

import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.OrderItemPK;
import org.springframework.data.repository.CrudRepository;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 8/3/2023
 * Time : 11:38 PM
 */

public interface OrderDetailsDao extends CrudRepository<OrderDetails, OrderItemPK> {
}
