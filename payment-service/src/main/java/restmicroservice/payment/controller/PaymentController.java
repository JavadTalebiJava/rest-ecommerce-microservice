package restmicroservice.payment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restmicroservice.payment.payload.ReturnPayload;
import restmicroservice.payment.payload.PaymentPayload;

@Slf4j
@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    @PostMapping
    private ResponseEntity<ReturnPayload> handleChargeRequest(@RequestBody PaymentPayload validationPayload)  {
        log.info("Payment-Service is accepting request.");

        log.info("The amount was charged from credit card {}", validationPayload.getCardNumber());

        return ResponseEntity.accepted().body(
                new ReturnPayload(true,"Payment is done for CardNumber " + validationPayload.getCardNumber()));
    }

}
