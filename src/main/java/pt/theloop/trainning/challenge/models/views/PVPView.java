package pt.theloop.trainning.challenge.models.views;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import pt.theloop.trainning.challenge.models.enums.CurrencyEnum;
import pt.theloop.trainning.challenge.models.pkeys.PVPViewId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Immutable
@Subselect("SELECT brand.id AS brand_id, price_list.start_time AS start_date, price_list.end_time AS end_date, price_list.id AS price_list_id, product.id AS product_id, price_list.priority, price.val AS price, price_list.currency AS curr " +
        "FROM brand INNER JOIN product ON brand.id = product.brand_id INNER JOIN price ON price.product_id = product.id INNER JOIN price_list ON price.price_list_id = price_list.id")
@Data
@Setter(AccessLevel.NONE)
@IdClass(PVPViewId.class)
public class PVPView {

    @Id
    private Long brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Id
    private Long priceListId;

    private Long productId;

    private Integer priority;

    private BigDecimal price;

    private CurrencyEnum curr;

}
