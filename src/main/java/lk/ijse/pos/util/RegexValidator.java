package lk.ijse.pos.util;

import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDTO;
import lk.ijse.pos.exception.InvalidException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/20/2023
 * Time : 9:14 AM
 */

@Component
public class RegexValidator {
    private final Pattern nicPattern;
    private final Pattern namePattern;
    private final Pattern pricePattern;
    private final Pattern orderIdPattern;

    public RegexValidator() {
        nicPattern = Pattern.compile("^[0-9]{9}[vVxX]||[0-9]{12}$");
        namePattern = Pattern.compile("^[a-zA-Z.+=@\\-_\\s]{3,50}$");
        pricePattern = Pattern.compile("^(\\d+)||((\\d+\\.)(\\d){2})$");
        orderIdPattern = Pattern.compile("^[O][0-9]{3,}$");
    }

    public void customerValidation(CustomerDTO customerDTO) {
        if (customerDTO.getNic() == null || !nicPattern.matcher(customerDTO.getNic()).matches())
            throw new InvalidException("Invalid nic");
        if (customerDTO.getName() == null || !namePattern.matcher(customerDTO.getName()).matches())
            throw new InvalidException("Invalid name");
        if (customerDTO.getSalary() == null || !pricePattern.matcher(customerDTO.getSalary().toString()).matches())
            throw new InvalidException("Invalid salary");
        if (customerDTO.getAddress() == null) throw new InvalidException("Invalid address");
    }

    public void itemValidation(ItemDTO itemDTO) {
        if (itemDTO.getDescription() == null) throw new InvalidException("Invalid description");
        if (itemDTO.getQtyOnHand() <= 0) throw new InvalidException("Invalid quantity");
        if (itemDTO.getUnitPrice() == null || !pricePattern.matcher(itemDTO.getUnitPrice().toString()).matches())
            throw new InvalidException("Invalid  unit price");
    }


    public void orderValidation(OrderDTO orderDTO) {
        if (orderDTO.getOrderId() == null || !orderIdPattern.matcher(orderDTO.getOrderId()).matches())
            throw new InvalidException("Invalid order id");
        if (orderDTO.getDate() == null) throw new InvalidException("Invalid  date");
        if (orderDTO.getCustomerId() == null) throw new InvalidException("Invalid customer id");
        if (orderDTO.getOrderDetails().size() == 0) throw new InvalidException("Order details empty");
    }
}
