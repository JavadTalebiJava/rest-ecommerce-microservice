package restmicroservice.order.controller;

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
import restmicroservice.order.payload.ReturnPayload;
import restmicroservice.order.payload.StockPayload;
import restmicroservice.order.service.OrderService;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    private ResponseEntity<ReturnPayload> handleOrderRequest(@RequestBody OrderPayload orderPayload) {
        log.info("Order-Service is accepting request.");
        var  inStock=  orderService.isProductInStock(
                new StockPayload(orderPayload.getProductId(), orderPayload.getAmount()));

        if (!inStock.isApproval()){
            log.info("The product is out of stock");
            throw new ProductOutOfStockException("The product is out of stock");
        }

        var cardValidation = orderService.isCardNumberValid(orderPayload.getCardNumber());
        if (!cardValidation.isApproval()){
            log.info("Invalid credit card number");
            throw new BadFormatException("Invalid credit card number");
        }

        return ResponseEntity.status(HttpStatus.OK).body(orderService.finishPayment(orderPayload.getCardNumber()));
    }
}
