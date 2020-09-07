package restmicroservice.payment.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnPayload implements Serializable {

    private boolean approval;

    private String message;

    public ReturnPayload(boolean approval) {
        this.approval = approval;
    }
}
