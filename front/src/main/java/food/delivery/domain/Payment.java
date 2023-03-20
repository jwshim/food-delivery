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
        System.out.println("==================");
        System.out.println(paid.getId() + "/" + paid.getOrderId());
        System.out.println("==================");
        paid.setStatus("order paid");
        repository().findById(paid.getId()).ifPresent(payment->{
            payment.setStatus("order paid");
            repository().save(payment);
         });
        paid.publishAfterCommit();
    }

    public static void needPay(OrderPlaced orderPlaced) {
        /** Example 1:  new item 
        Payment payment = new Payment();
        repository().save(payment);
        */

        Payment payment = new Payment();
        payment.setOrderId(orderPlaced.getId());
        payment.setStatus("wait for pay");
        repository().save(payment);

        /** Example 2:  finding and process
        
        repository().findById(orderPlaced.get???()).ifPresent(payment->{
            
            payment // do something
            repository().save(payment);


         });
        */

    }
}
