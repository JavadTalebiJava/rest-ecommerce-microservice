package restmicroservice.order.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import restmicroservice.order.payload.ReturnPayload;
import restmicroservice.order.payload.StockPayload;
import restmicroservice.order.payload.ValidationPayload;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final String STOCK_SERVICE_URL = "http://localhost:9082";
    private final String STOCK_SERVICE_PATH = "/api/products";
    private final String VALIDATION_SERVICE_URL = "http://localhost:9084";
    private final String VALIDATION_SERVICE_PATH = "/api/validation";
    private final String PAYMENT_SERVICE_URL = "http://localhost:9086";
    private final String PAYMENT_SERVICE_PATH = "/api/payment";
    private RestTemplate restTemplate = new RestTemplate();
    public ReturnPayload isProductInStock(final StockPayload stockPayload) {
        //Send a request to stock-sevice to validate the product

        HttpEntity<StockPayload> request = new HttpEntity<>(
                new StockPayload(stockPayload.getProductId(),stockPayload.getAmount()));
        ResponseEntity<ReturnPayload> response = restTemplate
                .exchange(STOCK_SERVICE_URL+STOCK_SERVICE_PATH,
                        HttpMethod.POST, request, ReturnPayload.class);

        return response.getBody();
    }

    public ReturnPayload isCardNumberValid(final String cardNumber) {
         HttpEntity<ValidationPayload> request = new HttpEntity<>(
                new ValidationPayload(cardNumber));
        ResponseEntity<ReturnPayload> response = restTemplate
                .exchange(VALIDATION_SERVICE_URL+VALIDATION_SERVICE_PATH,
                        HttpMethod.POST, request, ReturnPayload.class);

        return response.getBody();
    }


    public ReturnPayload finishPayment(final String cardNumber) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ValidationPayload> request = new HttpEntity<>(
                new ValidationPayload(cardNumber));
        ResponseEntity<ReturnPayload> response = restTemplate
                .exchange(PAYMENT_SERVICE_URL+PAYMENT_SERVICE_PATH,
                        HttpMethod.POST, request, ReturnPayload.class);

        return response.getBody();
    }
}
