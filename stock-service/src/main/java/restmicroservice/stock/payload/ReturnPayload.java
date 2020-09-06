package restmicroservice.stock.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ReturnPayload {

    private boolean approval;

    private String message;

    public ReturnPayload(boolean approval) {
        this.approval = approval;
    }
}
