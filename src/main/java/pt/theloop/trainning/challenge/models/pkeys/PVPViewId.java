package pt.theloop.trainning.challenge.models.pkeys;

import lombok.Data;

import java.io.Serializable;

@Data
public class PVPViewId implements Serializable {

    private Long brandId;

    private Long priceListId;

}
