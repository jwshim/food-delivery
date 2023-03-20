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
            Optional<Info> infoOptional = infoRepository.findById(
                Long.valueOf(paid.getOrderId())
            );

            if (infoOptional.isPresent()) {
                Info info = infoOptional.get();
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
            Optional<Info> infoOptional = infoRepository.findById(
                Long.valueOf(orderAccepted.getOrderId())
            );

            if (infoOptional.isPresent()) {
                Info info = infoOptional.get();
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
            Optional<Info> infoOptional = infoRepository.findById(
                Long.valueOf(orderRejected.getOrderId())
            );

            if (infoOptional.isPresent()) {
                Info info = infoOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                info.setStatus("rejected");
                // view 레파지 토리에 save
                infoRepository.save(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
