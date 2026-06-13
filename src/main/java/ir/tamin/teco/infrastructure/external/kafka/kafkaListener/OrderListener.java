package ir.tamin.teco.infrastructure.external.kafka.kafkaListener;

import ir.tamin.teco.application.port.in.OrderEventHandler;

public class OrderListener implements OrderEventHandler {
    @Override
    //KAFKA CONSUMER SPRING ANNOTATIONS
    public void handle() {
        //RECEIVE JSON or a kafka.model.orderCreateDto
        //map to domain/event
        //call the appropriate handler/service in application layer
    }
}
