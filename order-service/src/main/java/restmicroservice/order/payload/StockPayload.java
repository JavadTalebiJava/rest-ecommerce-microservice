package restmicroservice.order.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class StockPayload {

    private Long productId;
    private float amount;


}
