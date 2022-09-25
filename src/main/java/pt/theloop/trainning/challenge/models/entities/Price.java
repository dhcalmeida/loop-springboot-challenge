package pt.theloop.trainning.challenge.models.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"price_list_id", "product_id"})})
public class Price {

    @Id
    private long id;

    @JoinColumn(nullable = false)
    @ManyToOne
    private PriceList priceList;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private BigDecimal val;

}
