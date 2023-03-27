package food.delivery.domain;

import food.delivery.infra.AbstractEvent;
import java.util.*;
import lombok.Data;

@Data
public class OrderCanceled extends AbstractEvent {

    private Long id;
    private Long foodId;
    private List<String> options;
    private String address;
    private Long customerId;
    private String status;
    private Long storeId;
}