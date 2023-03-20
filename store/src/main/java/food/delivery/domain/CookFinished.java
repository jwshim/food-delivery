package food.delivery.domain;

import food.delivery.domain.*;
import food.delivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CookFinished extends AbstractEvent {

    private Long id;
    private String status;
    private Long foodId;
    private Long orderId;
    private List<String> options;
    private Long storeId;

    public CookFinished(FoodCooking aggregate) {
        super(aggregate);
    }

    public CookFinished() {
        super();
    }
}
