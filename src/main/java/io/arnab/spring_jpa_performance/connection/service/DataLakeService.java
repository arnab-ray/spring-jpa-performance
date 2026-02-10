package io.arnab.spring_jpa_performance.connection.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DataLakeService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void publishAnonData(Integer age) {
        try {
            Thread.sleep(200);
            log.info("Age {}", age);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
