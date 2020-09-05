package restmicroservice.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restmicroservice.order.exception.BadFormatException;
import restmicroservice.order.exception.ProductOutOfStockException;
import restmicroservice.order.payload.OrderPayload;
import restmicroservice.order.payload.StockPayload;
import restmicroservice.order.service.OrderService;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    private ResponseEntity<OrderPayload> handleOrderRequest(@RequestBody OrderPayload orderPayload) throws JsonProcessingException {
        log.info("Order-Service is accepting request.");

        if (!orderService.isProductInStock(new StockPayload(orderPayload.getProductId(), orderPayload.getAmount()))){
            log.info("The product is out of stock");
            throw new ProductOutOfStockException("The product is out of stock");
        }
        if (!orderService.isCardNumberValid(orderPayload.getCartNumber())){
            log.info("Invalid credit card number");
            throw new BadFormatException("Invalid credit card number");
        }

        return ResponseEntity.status(HttpStatus.OK).body(orderPayload);
    }
}
