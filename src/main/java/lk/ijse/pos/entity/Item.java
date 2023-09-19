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
 * Time : 12:55 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Item implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String itemCode;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private int qtyOnHand;
    @Column(nullable = false)
    private BigDecimal unitPrice;
}
