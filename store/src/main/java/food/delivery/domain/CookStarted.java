package food.delivery.domain;

import food.delivery.domain.*;
import food.delivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CookStarted extends AbstractEvent {

    private Long id;
    private String status;
    private Long foodId;
    private Long orderId;
    private List<String> options;
    private Long storeId;

    public CookStarted(FoodCooking aggregate) {
        super(aggregate);
    }

    public CookStarted() {
        super();
    }
}
