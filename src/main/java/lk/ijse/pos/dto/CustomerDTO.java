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
 * Date : 8/2/2023
 * Time : 11:54 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {
    @Null(message = "Customer id will generate by automatically")
    private String id;
    @NotNull(message = "Customer nic can't null")
    @Pattern(regexp = "^[0-9]{9}[vVxX]||[0-9]{12}$",message = "Invalid customer nic")
    private String nic;
    @NotNull(message = "Customer name can't null")
    @Pattern(regexp = "^[a-zA-Z.+=@\\-_\\s]{3,50}$",message = "Invalid customer name")
    private String name;
    @NotNull(message = "Customer salary can't null")
    @Pattern(regexp = "^(\\d+)||((\\d+\\.)(\\d){2})$",message = "Invalid customer salary")
    private BigDecimal salary;
    @NotNull(message = "Customer address can't null")
    private String address;
}
