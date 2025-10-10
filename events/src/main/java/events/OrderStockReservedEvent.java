package events;

import java.time.Instant;

public record OrderStockReservedEvent(
        Long orderId,
        Instant createdAt
) {
}
