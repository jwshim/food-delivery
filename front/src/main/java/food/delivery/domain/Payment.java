package food.delivery.domain;

import food.delivery.FrontApplication;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Payment_table")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String status;

    @PostPersist
    public void onPostPersist() {}

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = FrontApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    public void pay() {
        Paid paid = new Paid(this);
        paid.publishAfterCommit();
    }

    public static void needPay(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);
        */
        // hahahaha

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

    }
}
