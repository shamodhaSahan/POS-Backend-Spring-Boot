package lk.ijse.pos.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 8/3/2023
 * Time : 12:06 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    @NotNull(message = "Order id can't null")
    @Pattern(regexp = "^[O][0-9]{3,}$",message = "Invalid order id")
    private String orderId;
    @NotNull(message = "Order date can't null")
    private LocalDate date;
    @NotNull(message = "Customer id can't null")
    private String customerId;
    @NotEmpty(message = "Order details can't empty")
    private List<OrderDetailsDTO> orderDetails = new ArrayList<>();
}
