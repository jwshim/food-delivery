package food.delivery.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
public class PayCommand {

    private Long orderId;
}
