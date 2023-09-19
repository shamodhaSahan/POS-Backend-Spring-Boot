package lk.ijse.pos.service.impl;

import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.exception.DuplicateException;
import lk.ijse.pos.exception.InUseException;
import lk.ijse.pos.exception.NotFoundException;
import lk.ijse.pos.persistance.CustomerDao;
import lk.ijse.pos.persistance.OrderDao;
import lk.ijse.pos.service.CustomerService;
import lk.ijse.pos.util.DataTypeConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 2:12 PM
 */

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    DataTypeConvertor convertor;
    @Autowired
    CustomerDao customerDao;
    @Autowired
    OrderDao orderDao;

    @Override
    public CustomerDTO getCustomerById(String id) {
        return convertor.getCustomerDTO(customerDao.findById(id).orElseThrow(() -> new NotFoundException("Customer not found..!")));
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        if (customerDao.findCustomerByNic(customerDTO.getNic()).isPresent())
            throw new DuplicateException("Customer nic is duplicated..!");
        return convertor.getCustomerDTO(customerDao.save(convertor.getCustomerEntity(customerDTO)));
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        customerDao.findCustomerByNic(customerDTO.getNic()).filter(customerForCheckNic -> !customerForCheckNic.getId().equals(customerDTO.getId())).ifPresentOrElse(__ -> { // If the Customer ID with Duplicate Nic and the Updated Customer ID do not match
            throw new DuplicateException("Customer nic is duplicated..!");
        }, () -> {
            customerDao.findById(customerDTO.getId()).ifPresentOrElse(customer -> {
                customer.setNic(customerDTO.getNic());
                customer.setName(customerDTO.getName());
                customer.setSalary(customerDTO.getSalary());
                customer.setAddress(customerDTO.getAddress());
            }, () -> {
                throw new NotFoundException("Customer not found..!");
            });
        });
    }

    @Override
    public void deleteCustomerById(String id) {
        customerDao.findById(id).ifPresentOrElse(customer -> orderDao.findOrderByCustomer(customer).ifPresentOrElse(__ -> {
            throw new InUseException("Customer have orders..!");
        }, () -> {
            customerDao.delete(customer);
        }), () -> {
            throw new NotFoundException("Customer not found..!");
        });
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return ((List<Customer>) customerDao.findAll()).stream().map(customer -> convertor.getCustomerDTO(customer)).collect(Collectors.toList());
    }
}
