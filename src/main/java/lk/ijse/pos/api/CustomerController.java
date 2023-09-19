package lk.ijse.pos.api;

import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 12:55 PM
 */

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin("*")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @GetMapping
    List<CustomerDTO> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    CustomerDTO getSelectedCustomer(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping(value = "{id}")
    void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomerById(id);
    }

    @PatchMapping(value = "{id}")
    void updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customer) {
        //ToDo: Error handling
        customer.setId(id);
        customerService.updateCustomer(customer);
    }
}
