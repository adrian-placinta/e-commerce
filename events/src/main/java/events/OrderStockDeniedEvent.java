package events;

import java.time.Instant;

public record OrderStockDeniedEvent(
        Long orderId,
        String reason,
        Instant createdAt
) {
}
