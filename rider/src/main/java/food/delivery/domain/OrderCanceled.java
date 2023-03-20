package food.delivery.domain;

import food.delivery.domain.*;
import food.delivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderCanceled extends AbstractEvent {

    private Long id;
    private Long foodId;
    private Object options;
    private String address;
    private Long customerId;
    private String status;
    private Long storeId;
}
