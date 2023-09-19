package lk.ijse.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 12:55 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "orders")
public class Order implements SuperEntity {
    @Id
    private String orderId;
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne//(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    @OneToMany(
            mappedBy = "order",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.EAGER
    )
    private List<OrderDetails> orderDetails;
}
