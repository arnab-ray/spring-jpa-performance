package io.arnab.spring_jpa_performance.service;

import io.arnab.spring_jpa_performance.domain.MobileNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void notifyOnboarded(MobileNumber mobileNumber) {
        try {
            Thread.sleep(200);
            logger.info("notification sent to mobile number: {}", mobileNumber);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
