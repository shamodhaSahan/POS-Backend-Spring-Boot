package lk.ijse.pos.util;

import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.Order;
import lk.ijse.pos.entity.OrderDetails;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 1:53 PM
 */

@Component
public class DataTypeConvertor {
    private final ModelMapper modelMapper;

    public DataTypeConvertor(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public CustomerDTO getCustomerDTO(Customer customer){
        return modelMapper.map(customer, CustomerDTO.class);
    }
    public Customer getCustomerEntity(CustomerDTO customerDTO){
        return modelMapper.map(customerDTO, Customer.class);
    }
    public ItemDTO getItemDTO(Item item){
        return modelMapper.map(item, ItemDTO.class);
    }
    public Item getItemEntity(ItemDTO itemDTO){
        return modelMapper.map(itemDTO, Item.class);
    }
    public OrderDTO getOrderDTO(Order order){
        return modelMapper.map(order, OrderDTO.class);
    }
    public Order getOrderEntity(OrderDTO orderDTO){
        return modelMapper.map(orderDTO, Order.class);
    }
    public OrderDetailsDTO getOrderDetailsDTO(OrderDetails orderDetails){
        return modelMapper.map(orderDetails, OrderDetailsDTO.class);
    }
    public OrderDetails getOrderDetails(OrderDetailsDTO orderDetailsDTO){
        return modelMapper.map(orderDetailsDTO, OrderDetails.class);
    }
}
