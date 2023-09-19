package lk.ijse.pos.service.impl;

import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.service.OrderService;
import lk.ijse.pos.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 2:12 PM
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    DataTypeConvertor conversion;

    @Override
    public OrderDTO getOrderById(String id) {
        return null;
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public void updateOrder(OrderDTO orderDTO) {

    }

    @Override
    public void deleteOrderById(String id) {

    }

    @Override
    public List<OrderDTO> getAllOrder() {
        return null;
    }
}
