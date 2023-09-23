package lk.ijse.pos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 8/3/2023
 * Time : 12:06 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsDTO {
    @NotNull(message = "Order id can't null")
    @Pattern(regexp = "^[O][0-9]{3,}$",message = "Invalid order id")
    private String orderId;
    @NotNull(message = "Item code can't null")
    private String itemCode;
    @NotNull(message = "Item quantity on hand can't null")
    @Pattern(regexp = "^\\d+$",message = "Invalid item quantity")
    private int qty;
    @NotNull(message = "Item price can't null")
    @Pattern(regexp = "^(\\d+)||((\\d+\\.)(\\d){2})$",message = "Invalid item price")
    private BigDecimal unitPrice;
}
