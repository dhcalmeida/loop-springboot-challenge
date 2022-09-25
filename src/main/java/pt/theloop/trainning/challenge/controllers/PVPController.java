package pt.theloop.trainning.challenge.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pt.theloop.trainning.challenge.models.views.PVPView;
import pt.theloop.trainning.challenge.services.PVPViewService;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Prices")
@RestController
public class PVPController {

    private final PVPViewService pvpViewService;

    @Autowired
    public PVPController(PVPViewService pvpViewService) {
        this.pvpViewService = pvpViewService;
    }

    @Operation(summary = "Get all prices in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all prices available", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PVPView.class)))})}
    )
    @GetMapping(value = "/prices")
    public List<PVPView> getAll() {
        return pvpViewService.getAll();
    }

    @Operation(summary = "Find applicable price by brand, product and datetime")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of prices that match criteria", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PVPView.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request (invalid parameter)", content = @Content),
            @ApiResponse(responseCode = "404", description = "Price not found", content = @Content)}
    )
    @GetMapping(value = "/prices/brands/{brandId}/products/{productId}")
    public PVPView search(@PathVariable("brandId") @Parameter(description = "Brand identifier to search", example = "1") Long brandId,
                          @PathVariable("productId") @Parameter(description = "Product identifier to search", example = "35455") Long productId,
                          @RequestParam("dateTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm", fallbackPatterns = {"yyyy-MM-dd HH:mm", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd HH:mm:ss"})
                          @Parameter(description = "Date and time to search", example = "2020-06-14T14:59") LocalDateTime dateTime) {
        PVPView pvpView = pvpViewService.getByCriteria(productId, brandId, dateTime);
        if (null == pvpView) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return pvpView;
    }

}
