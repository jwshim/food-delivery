package food.delivery.domain;

import food.delivery.RiderApplication;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;

    private Long orderId;

    private String address;

    @PostPersist
    public void onPostPersist() {}

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public void pick() {
        repository().findById(this.getId()).ifPresent(delivery->{
            if (delivery.getStatus().compareTo("cook finished") == 0) {
                delivery.setStatus("rider picked");
                Picked picked = new Picked(this);
                picked.publishAfterCommit();
            }
            repository().save(delivery);
         });

    }

    public void confirmDelivered() {
        repository().findById(this.getId()).ifPresent(delivery->{
            if (delivery.getStatus().compareTo("rider picked") == 0) {
                delivery.setStatus("food delivered");
                Delivered delivered = new Delivered(this);
                delivered.publishAfterCommit();
            }
            repository().save(delivery);
         });
    }

    public static void newOrder(OrderPlaced orderPlaced) {
        Delivery delivery = new Delivery();
        delivery.setAddress(orderPlaced.getAddress());
        delivery.setOrderId(orderPlaced.getId());
        delivery.setStatus("new order");
        repository().save(delivery);
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);
        */
        /** Example 2:  finding and process
        repository().findById(orderPlaced.get???()).ifPresent(delivery->{
            delivery // do something
            repository().save(delivery);
         });
        */
    }

    public static void updateStatus(CookFinished cookFinished) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);
        */
        repository().findByOrderId(cookFinished.getOrderId()).ifPresent(delivery->{
            delivery.setStatus("cook finished");
            repository().save(delivery);
        });
        /** Example 2:  finding and process
        
        repository().findById(cookFinished.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

    }

    public static void updateStatus(OrderCanceled orderCanceled) {
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);


         });
        */

    }
}
