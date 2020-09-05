package restmicroservice.order.payload;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderPayload {

    private Long customerId;

    private Long productId;
    private float amount;
    private String cartNumber;


}
