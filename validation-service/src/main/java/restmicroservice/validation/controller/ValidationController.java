package restmicroservice.validation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restmicroservice.validation.payload.ReturnPayload;
import restmicroservice.validation.payload.ValidationPayload;

@Slf4j
@RestController
@RequestMapping("/api/validation")
@RequiredArgsConstructor
public class ValidationController {


    @PostMapping
    private ResponseEntity<ReturnPayload> handleRequest(@RequestBody ValidationPayload validationPayload)  {
        log.info("Validation-Service is accepting request.");

        final boolean isValid = validateCardNumber(validationPayload.getCardNumber());

        if(!isValid){
            log.info("Invalid Credit Card Number {}", validationPayload.getCardNumber());

            return ResponseEntity.accepted().body(
                    new ReturnPayload(false,"Invalid Credit Card Number"));
        }

        return ResponseEntity.accepted().body(new ReturnPayload(true));
    }

    private boolean validateCardNumber(final String cardNumber){
        try {
            Long.parseLong(cardNumber);
            return true;
        } catch (NumberFormatException ex){ }
        return false;
    }
}
