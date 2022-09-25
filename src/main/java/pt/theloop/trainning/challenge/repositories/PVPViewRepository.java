package pt.theloop.trainning.challenge.repositories;

import pt.theloop.trainning.challenge.models.views.PVPView;
import pt.theloop.trainning.challenge.repositories.base.ReadOnlyRepository;

import java.time.LocalDateTime;

public interface PVPViewRepository extends ReadOnlyRepository<PVPView, Long> {

    PVPView findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Long productId, Long brandId, LocalDateTime dateTime1, LocalDateTime dateTime2);

}