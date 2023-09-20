package lk.ijse.pos.api;

import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.service.CustomerService;
import lk.ijse.pos.util.RegexValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final RegexValidator regexValidator;

    public CustomerController(CustomerService customerService, RegexValidator regexValidator) {
        this.customerService = customerService;
        this.regexValidator = regexValidator;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        regexValidator.customerValidation(customerDTO);
        return new ResponseEntity<>(customerService.saveCustomer(customerDTO), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getSelectedCustomer(@PathVariable String id) {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    ResponseEntity<?> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>("Customer " + id + " is deleted", HttpStatus.OK);
    }

    @PatchMapping(value = "{id}")
    ResponseEntity<?> updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {
        regexValidator.customerValidation(customerDTO);
        customerDTO.setId(id);
        customerService.updateCustomer(customerDTO);
        return new ResponseEntity<>("Customer " + id + " is updated", HttpStatus.OK);
    }
}
