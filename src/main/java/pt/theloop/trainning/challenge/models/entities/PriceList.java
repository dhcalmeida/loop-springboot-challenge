package pt.theloop.trainning.challenge.models.entities;

import lombok.Data;
import pt.theloop.trainning.challenge.models.enums.CurrencyEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class PriceList {

    @Id
    private long id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private Integer priority;

    @Column(nullable = false)
    private CurrencyEnum currency;

}
