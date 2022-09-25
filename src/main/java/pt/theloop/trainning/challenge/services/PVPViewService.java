package pt.theloop.trainning.challenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.theloop.trainning.challenge.models.views.PVPView;
import pt.theloop.trainning.challenge.repositories.PVPViewRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PVPViewService {

    private final PVPViewRepository pvpViewRepository;

    @Autowired
    public PVPViewService(PVPViewRepository pvpViewRepository) {
        this.pvpViewRepository = pvpViewRepository;
    }

    public List<PVPView> getAll() {
        return pvpViewRepository.findAll();
    }

    public PVPView getByCriteria(Long productId, Long brandId, LocalDateTime dateTime) {
        return pvpViewRepository.
                findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        productId, brandId, dateTime, dateTime);
    }

}
