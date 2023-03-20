package food.delivery.domain;

import food.delivery.StoreApplication;
import food.delivery.domain.OrderRejected;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "FoodCooking_table")
@Data
public class FoodCooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;

    private Long foodId;

    private Long orderId;

    @ElementCollection
    private List<String> options;

    private Long storeId;

    /*
    @PostPersist
    public void onPostPersist() {
        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();
    }
    */

    public static FoodCookingRepository repository() {
        FoodCookingRepository foodCookingRepository = StoreApplication.applicationContext.getBean(
            FoodCookingRepository.class
        );
        return foodCookingRepository;
    }

    public void accept(AcceptCommand acceptCommand) {
        repository().findById(this.getId()).ifPresent(foodCooking-> {
            if (acceptCommand.getAccept()) {
                foodCooking.setStatus("order accepted");
                OrderAccepted orderAccepted = new OrderAccepted(this);
                orderAccepted.setStatus("order accepted");
                orderAccepted.publishAfterCommit();
            }
            else {
                foodCooking.setStatus("order rejected");
                OrderRejected orderRejected = new OrderRejected(this);
                orderRejected.setStatus("order rejected");
                orderRejected.publishAfterCommit();
            }
            repository().save(foodCooking);
        });
    }

    public void start() {
        CookStarted cookStarted = new CookStarted(this);
        repository().findById(cookStarted.getId()).ifPresent(foodCooking-> {
            foodCooking.setStatus("order cook started");
            repository().save(foodCooking);
        });
        cookStarted.publishAfterCommit();
    }

    public void finish() {
        CookFinished cookFinished = new CookFinished(this);
        repository().findById(cookFinished.getId()).ifPresent(foodCooking-> {
            foodCooking.setStatus("order cook finished");
            repository().save(foodCooking);
        });
        cookFinished.publishAfterCommit();
    }

    public static void newOrder(OrderPlaced orderPlaced) {
        FoodCooking foodCooking = new FoodCooking();

        foodCooking.setFoodId(orderPlaced.getFoodId());
        foodCooking.setOptions((List<String>)orderPlaced.getOptions());
        foodCooking.setOrderId(orderPlaced.getId());
        foodCooking.setStoreId(orderPlaced.getStoreId());
        foodCooking.setStatus("new order");

        repository().save(foodCooking);
        /** Example 1:  new item 
        FoodCooking foodCooking = new FoodCooking();
        repository().save(foodCooking);
        */
        /** Example 2:  finding and process
        repository().findById(orderPlaced.get???()).ifPresent(foodCooking->{
            foodCooking // do something
            repository().save(foodCooking);
         });
        */
    }

    public static void updateStatus(Paid paid) {
        /** Example 1:  new item 
        FoodCooking foodCooking = new FoodCooking();
        repository().save(foodCooking);
        */
        /** Example 2:  finding and process
        repository().findById(paid.get???()).ifPresent(foodCooking->{
            foodCooking // do something
            repository().save(foodCooking);
         });
        */
        repository().findByOrderId(paid.getOrderId()).ifPresent(foodCooking-> {
            foodCooking.setStatus("order paid");
        });
    }

    public static void updateStatus(OrderCanceled orderCanceled) {
        /** Example 1:  new item 
        FoodCooking foodCooking = new FoodCooking();
        repository().save(foodCooking);

        */

        /** Example 2:  finding and process
        
        repository().findById(orderCanceled.get???()).ifPresent(foodCooking->{
            
            foodCooking // do something
            repository().save(foodCooking);


         });
        */

    }
}
