package events;

import java.time.Instant;
import java.util.List;

public record OrderCreatedEvent(
        Long orderId,
        List<OrderItem> orderItems,
        Instant createdAt
) {
    public record OrderItem(Long productId, Long quantity) {
    }
}
