package ir.tamin.teco.infrastructure.schedule;

import org.springframework.scheduling.annotation.Scheduled;

public class ExpireOrderJob {

    @Scheduled(fixedDelay = 100_000)
    void expireOrders() {
    }
}
