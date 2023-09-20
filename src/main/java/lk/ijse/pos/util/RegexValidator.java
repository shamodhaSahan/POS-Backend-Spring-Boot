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
    private final Pattern telephoneNumberPattern;
    private final Pattern emailPattern;
    private final Pattern pricePattern;
    private final Pattern orderIdPattern;

    public RegexValidator() {
        nicPattern = Pattern.compile("^[0-9]{9}[vVxX]||[0-9]{12}$");
        namePattern = Pattern.compile("^[a-zA-Z.+=@\\-_\\s]{3,50}$");
        telephoneNumberPattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");
        emailPattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        pricePattern = Pattern.compile("^(\\d+)||((\\d+\\.)(\\d){2})$");
        orderIdPattern = Pattern.compile("^[O][0-9]{3,}$");
    }

    public void customerValidation(CustomerDTO customerDTO) {
        String error = "";
        error += (customerDTO.getNic() == null || !nicPattern.matcher(customerDTO.getNic()).matches()) ? " nic," : "";
        error += (customerDTO.getName() == null || !namePattern.matcher(customerDTO.getName()).matches()) ? " name," : "";
        error += (customerDTO.getSalary() == null || !pricePattern.matcher(customerDTO.getSalary().toString()).matches()) ? " salary," : "";
        error += (customerDTO.getAddress() == null) ? " address," : "";
        if (!error.isEmpty()) {
            throw new InvalidException("Invalid " + error + "\b ");
        }
    }

    public void itemValidation(ItemDTO itemDTO) {
        String error = "";
        error += (itemDTO.getDescription() == null) ? " description," : "";
        error += (itemDTO.getQtyOnHand() <= 0) ? " quantity," : "";
        error += (itemDTO.getUnitPrice() == null || !pricePattern.matcher(itemDTO.getUnitPrice().toString()).matches()) ? " unit price," : "";
        if (!error.isEmpty()) {
            throw new InvalidException("Invalid " + error + "\b ");
        }
    }


    public void orderValidation(OrderDTO orderDTO) {
        String error = "";
        error += (orderDTO.getOrderId() == null || !orderIdPattern.matcher(orderDTO.getOrderId()).matches()) ? " order id," : "";
        error += (orderDTO.getDate() == null) ? " date," : "";
        error += (orderDTO.getCustomerId() == null) ? " customer id," : "";
        if (!error.isEmpty()) {
            error += "Invalid " + error + "\b ";
            error += (orderDTO.getOrderDetailsList() == null || orderDTO.getOrderDetailsList().size() == 0) ? " or order details empty" : "";
            throw new InvalidException(error);
        }
    }
}
