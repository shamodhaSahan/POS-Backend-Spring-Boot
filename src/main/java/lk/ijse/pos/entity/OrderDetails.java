package lk.ijse.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 12:56 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@IdClass(OrderItemPK.class)
public class OrderDetails implements SuperEntity {
    @Id
    private String orderId;

    @Id
    private String itemCode;

    private int qty;
    private BigDecimal unitPrice;


    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orderId", insertable = false, updatable = false)
    private Order order;


    @ManyToOne
    @JoinColumn(name = "itemCode", referencedColumnName = "itemCode", insertable = false, updatable = false)
    private Item item;
}
