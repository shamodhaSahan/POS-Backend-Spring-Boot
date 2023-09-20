package lk.ijse.pos.service;

import lk.ijse.pos.dto.CustomerDTO;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 2:12 PM
 */
public interface CustomerService {
    CustomerDTO getCustomerById(String id);
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    void updateCustomer(CustomerDTO customerDTO);
    void deleteCustomerById(String id);
    List<CustomerDTO> getAllCustomers();
}
