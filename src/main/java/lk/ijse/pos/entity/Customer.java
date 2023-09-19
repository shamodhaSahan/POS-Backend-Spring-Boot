package lk.ijse.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By shamodha_s_rathnamalala
 * Date : 9/19/2023
 * Time : 12:55 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String nic;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal salary;
    @Column(nullable = false)
    private String address;

//    @OneToMany(
//            mappedBy = "customer",
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}
//    )
//    private List<Order> orders = new ArrayList<>();
}
