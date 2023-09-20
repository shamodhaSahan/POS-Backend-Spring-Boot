package lk.ijse.pos.service.impl;

import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderItemPK;
import lk.ijse.pos.exception.InvalidException;
import lk.ijse.pos.exception.NotFoundException;
import lk.ijse.pos.persistance.CustomerDao;
import lk.ijse.pos.persistance.ItemDao;
import lk.ijse.pos.persistance.OrderDao;
import lk.ijse.pos.persistance.OrderDetailsDao;
import lk.ijse.pos.service.OrderService;
import lk.ijse.pos.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 2:12 PM
 */

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final DataTypeConvertor convertor;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDetailsDao orderDetailsDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ItemDao itemDao;

    public OrderServiceImpl(DataTypeConvertor convertor) {
        this.convertor = convertor;
    }

    @Override
    public OrderDTO getOrderById(String id) {
        return convertor.getOrderDTO(orderDao.findById(id).orElseThrow(() -> new NotFoundException("Order not found..!")));
    }

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {
        customerDao.findById(orderDTO.getCustomerId()).ifPresentOrElse(customer -> {
            orderDTO.getOrderDetails().forEach(orderDetailsDTO -> {
                itemDao.findById(orderDetailsDTO.getItemCode()).ifPresentOrElse(item -> {
                    int qty = item.getQtyOnHand() - orderDetailsDTO.getQty();
                    if (qty < 0) throw new InvalidException("Not enough quantity");
                    item.setQtyOnHand(qty);
                }, () -> {
                    throw new NotFoundException("Item not found..!");
                });
            });
        }, () -> {
            throw new NotFoundException("Customer not found..!");
        });
        return convertor.getOrderDTO(orderDao.save(convertor.getOrderEntity(orderDTO)));
    }

    @Override
    public void updateOrder(OrderDTO orderDTO) {
        orderDao.findById(orderDTO.getOrderId()).ifPresentOrElse(order -> {
            customerDao.findById(orderDTO.getCustomerId()).ifPresentOrElse(customer -> {
                order.setDate(orderDTO.getDate());
                order.setCustomer(customer);
                orderDTO.getOrderDetails().forEach(orderDetailsDTO -> {
                    orderDetailsDao.findById(new OrderItemPK(orderDetailsDTO.getOrderId(), orderDetailsDTO.getItemCode())).ifPresentOrElse(orderDetails -> {
                        orderDetails.setQty(orderDetailsDTO.getQty());
                        orderDetails.setUnitPrice(orderDetailsDTO.getUnitPrice());
                    }, () -> {
                        throw new NotFoundException("Order Details not found..!");
                    });
                });
            }, () -> {
                throw new NotFoundException("Customer not found..!");
            });
        }, () -> {
            throw new NotFoundException("Order not found..!");
        });
    }

    @Override
    public void deleteOrderById(String id) {
        orderDao.findById(id).ifPresentOrElse(order -> {
            orderDao.delete(order);
            order.getOrderDetails().forEach(orderDetails -> {
                itemDao.findById(orderDetails.getItemCode()).ifPresentOrElse(item -> {
                    item.setQtyOnHand(item.getQtyOnHand() + orderDetails.getQty());
                }, () -> {
                    throw new NotFoundException("Item not found..!");
                });
            });
        }, () -> {
            throw new NotFoundException("Order not found..!");
        });
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return ((List<Order>) orderDao.findAll()).stream().map(order -> convertor.getOrderDTO(order)).collect(Collectors.toList());
    }
}
