package food.delivery.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;


@Entity
@Table(name="Info_table")
@Data
public class Info {

        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private String status;


}
