package events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderInventoryFailedEvent {
    private String orderId;
    private String productId;
    private int quantity;
    private String reason;
}