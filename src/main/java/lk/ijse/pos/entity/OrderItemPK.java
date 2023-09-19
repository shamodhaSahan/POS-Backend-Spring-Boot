package lk.ijse.pos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 8/3/2023
 * Time : 2:26 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemPK implements Serializable {
    private String orderId;
    private String itemCode;
}
