package lk.ijse.pos.api;

import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.service.OrderService;
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
@RequestMapping("/api/v1/order")
@CrossOrigin("*")
public class OrderController {
    private final OrderService orderService;
    private final RegexValidator regexValidator;

    public OrderController(OrderService orderService, RegexValidator regexValidator) {
        this.orderService = orderService;
        this.regexValidator = regexValidator;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> saveOrder(@RequestBody OrderDTO orderDTO) {
        regexValidator.orderValidation(orderDTO);
        return new ResponseEntity<>(orderService.saveOrder(orderDTO), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getAllOrder() {
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
    }
}
