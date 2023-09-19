package lk.ijse.pos.service.impl;

import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import lk.ijse.pos.exception.DuplicateException;
import lk.ijse.pos.exception.NotFoundException;
import lk.ijse.pos.persistance.CustomerDao;
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
        customerDao.findCustomerByNic(customerDTO.getNic()).ifPresentOrElse(
                customerForCheckNic -> { // customer with a duplicate nic
                    if (!customerForCheckNic.getId().equals(customerDTO.getId())) // If the Customer ID with Duplicate Niche and the Updated Customer ID do not match
                        throw new DuplicateException("Customer nic is duplicated..!"); //
                },
                () -> customerDao.findById(customerDTO.getId()).ifPresentOrElse(
                        customer -> {
                            customer.setNic(customerDTO.getNic());
                            customer.setName(customerDTO.getName());
                            customer.setSalary(customerDTO.getSalary());
                            customer.setAddress(customerDTO.getAddress());
                        },
                        () -> {
                            throw new NotFoundException("Customer not found..!");
                        }
                )
        );

//        if (customerDao.findCustomerByNic(customerDTO.getNic()).isPresent() &&
//                            !customerDao.findCustomerByNic(customerDTO.getNic()).get().getId().equals(customerDTO.getId())) {
//                        throw new DuplicateException("Customer nic is duplicated..!");
//        customerDao.findById(customerDTO.getId()).ifPresentOrElse(
//                customer -> {
//                        customer.setNic(customerDTO.getNic());
//                        customer.setName(customerDTO.getName());
//                        customer.setSalary(customerDTO.getSalary());
//                        customer.setAddress(customerDTO.getAddress());
//
//                },
//                () -> {
//                    throw new NotFoundException("Customer not found..!");
//                }
//        );
    }

    @Override
    public void deleteCustomerById(String id) {
        customerDao.findById(id).ifPresentOrElse(
                customer -> customerDao.delete(customer),
                () -> {
                    throw new NotFoundException("Customer not found..!");
                }
        );
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return ((List<Customer>) customerDao.findAll()).stream().map(
                customer -> convertor.getCustomerDTO(customer)
        ).collect(Collectors.toList());
    }
}
