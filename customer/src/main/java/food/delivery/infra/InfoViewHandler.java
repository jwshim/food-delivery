package food.delivery.infra;

import food.delivery.config.kafka.KafkaProcessor;
import food.delivery.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class InfoViewHandler {

    @Autowired
    private InfoRepository infoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1(
        @Payload OrderPlaced orderPlaced
    ) {
        try {
            if (!orderPlaced.validate()) return;

            // view 객체 생성
            Info info = new Info();
            // view 객체에 이벤트의 Value 를 set 함
            info.setOrderId(orderPlaced.getId());
            info.setStatus(orderPlaced.getStatus());
            info.setAddress(orderPlaced.getAddress());
            // view 레파지 토리에 save
            infoRepository.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaid_then_UPDATE_1(@Payload Paid paid) {
        try {
            if (!paid.validate()) return;
            // view 객체 조회

            List<Info> infoList = infoRepository.findByOrderId(
                Long.valueOf(paid.getOrderId())
            );
            for (Info info : infoList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                info.setStatus("Payed");
                // view 레파지 토리에 save
                infoRepository.save(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderAccepted_then_UPDATE_2(
        @Payload OrderAccepted orderAccepted
    ) {
        try {
            if (!orderAccepted.validate()) return;
            // view 객체 조회

            List<Info> infoList = infoRepository.findByOrderId(
                Long.valueOf(orderAccepted.getOrderId())
            );
            for (Info info : infoList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                info.setStatus("accepted");
                // view 레파지 토리에 save
                infoRepository.save(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderRejected_then_UPDATE_3(
        @Payload OrderRejected orderRejected
    ) {
        try {
            if (!orderRejected.validate()) return;
            // view 객체 조회

            List<Info> infoList = infoRepository.findByOrderId(
                Long.valueOf(orderRejected.getOrderId())
            );
            for (Info info : infoList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                info.setStatus("rejected");
                // view 레파지 토리에 save
                infoRepository.save(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookStarted_then_UPDATE_4(
        @Payload CookStarted cookStarted
    ) {
        try {
            if (!cookStarted.validate()) return;
            // view 객체 조회

            List<Info> infoList = infoRepository.findByOrderId(
                cookStarted.getOrderId()
            );
            for (Info info : infoList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                info.setStatus("cook started");
                // view 레파지 토리에 save
                infoRepository.save(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookFinished_then_UPDATE_5(
        @Payload CookFinished cookFinished
    ) {
        try {
            if (!cookFinished.validate()) return;
            // view 객체 조회

            List<Info> infoList = infoRepository.findByOrderId(
                cookFinished.getOrderId()
            );
            for (Info info : infoList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                info.setStatus("cook finished");
                // view 레파지 토리에 save
                infoRepository.save(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPicked_then_UPDATE_6(@Payload Picked picked) {
        try {
            if (!picked.validate()) return;
            // view 객체 조회

            List<Info> infoList = infoRepository.findByOrderId(
                picked.getOrderId()
            );
            for (Info info : infoList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                info.setStatus("food picked");
                // view 레파지 토리에 save
                infoRepository.save(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDelivered_then_UPDATE_7(@Payload Delivered delivered) {
        try {
            if (!delivered.validate()) return;
            // view 객체 조회

            List<Info> infoList = infoRepository.findByOrderId(
                delivered.getOrderId()
            );
            for (Info info : infoList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                info.setStatus("food delivered");
                // view 레파지 토리에 save
                infoRepository.save(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCanceled_then_UPDATE_8(
        @Payload OrderCanceled orderCanceled
    ) {
        try {
            if (!orderCanceled.validate()) return;
            // view 객체 조회

            List<Info> infoList = infoRepository.findByOrderId(
                orderCanceled.getId()
            );
            for (Info info : infoList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                info.setStatus("order canceled");
                // view 레파지 토리에 save
                infoRepository.save(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderRejected_then_UPDATE_9(
        @Payload OrderRejected orderRejected
    ) {
        try {
            if (!orderRejected.validate()) return;
            // view 객체 조회

            List<Info> infoList = infoRepository.findByOrderId(
                orderRejected.getOrderId()
            );
            for (Info info : infoList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                info.setStatus("order rejected");
                // view 레파지 토리에 save
                infoRepository.save(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
