package restmicroservice.stock.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restmicroservice.stock.payload.StockPayload;

@Slf4j
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class StockController {

    @PostMapping
    private ResponseEntity<Boolean> handleRequest(@RequestBody StockPayload stock) throws JsonProcessingException {
        log.info("Stock-Service is accepting request.");

        boolean inStock = false;

        if (stock.getProductId() > 0
                && stock.getAmount() > 0) {
            inStock = true;
        } else {
            log.info("The product {} is out of stock", stock.getProductId());
        }

        return ResponseEntity.accepted().body(inStock);
    }
}
