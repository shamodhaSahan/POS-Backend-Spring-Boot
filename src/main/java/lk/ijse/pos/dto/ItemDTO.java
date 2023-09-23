package lk.ijse.pos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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
public class ItemDTO {
    @Null(message = "Item code will generate by automatically")
    private String code;
    @NotNull(message = "Description nic can't null")
    private String description;
    @NotNull(message = "Item quantity on hand can't null")
    @Pattern(regexp = "^\\d+$",message = "Invalid item quantity")
    private int qtyOnHand;
    @NotNull(message = "Item price can't null")
    @Pattern(regexp = "^(\\d+)||((\\d+\\.)(\\d){2})$",message = "Invalid item price")
    private BigDecimal unitPrice;
}
