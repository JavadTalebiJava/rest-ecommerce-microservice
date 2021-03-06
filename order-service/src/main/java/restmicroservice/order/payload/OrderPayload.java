package restmicroservice.order.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderPayload implements Serializable {

    private Long customerId;

    private Long productId;
    private float amount;
    private String cardNumber;


}
