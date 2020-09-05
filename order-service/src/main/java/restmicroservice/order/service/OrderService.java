package restmicroservice.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import restmicroservice.order.payload.StockPayload;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final String STOCK_SERVICE_URL = "http://localhost:9082";
    private final String STOCK_SERVICE_PATH = "/api/products";
    private final String VALIDATION_SERVICE_URL = "http://localhost:9084";
    private final String VALIDATION_SERVICE_PATH = "/api/validation/{cardNumber}";
    private final String PAYMENT_SERVICE_URL = "http://localhost:9086";
    private final String PAYMENT_SERVICE_PATH = "api/payment";

    private final ObjectMapper objectMapper;

    public boolean isProductInStock(final StockPayload stockPayload) {
        //Send a request to stock-sevice to validate the product
       /*
       RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Foo> request = new HttpEntity<>(new Foo("bar"));
        ResponseEntity<Foo> response = restTemplate
                .exchange(fooResourceUrl, HttpMethod.POST, request, Foo.class);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

        Foo foo = response.getBody();

        assertThat(foo, notNullValue());
        assertThat(foo.getName(), is("bar"));*/

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<StockPayload> request = new HttpEntity<>(
                new StockPayload(stockPayload.getProductId(),stockPayload.getAmount()));
        ResponseEntity<Boolean> response = restTemplate
                .exchange(STOCK_SERVICE_URL+STOCK_SERVICE_PATH, HttpMethod.POST, request, Boolean.class);

        return response.getBody();
    }

    public boolean isCardNumberValid(final String cartNumber) {
       return isNumeric(cartNumber);
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
           Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
