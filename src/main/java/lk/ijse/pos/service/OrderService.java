package lk.ijse.pos.service;

import lk.ijse.pos.dto.OrderDTO;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 2:12 PM
 */
public interface OrderService {
    OrderDTO getOrderById(String id);
    OrderDTO saveOrder(OrderDTO orderDTO);
    void updateOrder(OrderDTO orderDTO);
    void deleteOrderById(String id);
    List<OrderDTO> getAllOrder();
}
