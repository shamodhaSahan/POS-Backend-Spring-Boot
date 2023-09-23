package lk.ijse.pos.api;

import jakarta.validation.Valid;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.exception.InvalidException;
import lk.ijse.pos.service.OrderService;
import lk.ijse.pos.util.RegexValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
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
    ResponseEntity<?> saveOrder(@Valid @RequestBody OrderDTO orderDTO, Errors errors) {
        System.out.println(orderDTO);
        System.out.println(errors);
        if (errors.hasFieldErrors())
            throw new InvalidException(errors.getFieldErrors().get(0).getDefaultMessage());
//        return new ResponseEntity<>(orderService.saveOrder(orderDTO), HttpStatus.CREATED);
        return null;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping(value = "{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> getSelectedOrder(@PathVariable String orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @DeleteMapping(value = "{orderId}")
    ResponseEntity<?> deleteCustomer(@PathVariable String orderId) {
        orderService.deleteOrderById(orderId);
        return new ResponseEntity<>("Order " + orderId + " is deleted", HttpStatus.OK);
    }

    @PatchMapping(value = "{orderId}")
    ResponseEntity<?> updateCustomer(@PathVariable String orderId, @Valid @RequestBody OrderDTO orderDTO, Errors errors) {
        if (errors.hasFieldErrors())
            throw new InvalidException(errors.getFieldErrors().get(0).getDefaultMessage());
        orderDTO.setOrderId(orderId);
        orderService.updateOrder(orderDTO);
        return new ResponseEntity<>("Order " + orderId + " is updated", HttpStatus.OK);
    }
}
