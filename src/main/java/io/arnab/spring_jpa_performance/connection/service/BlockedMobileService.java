package io.arnab.spring_jpa_performance.connection.service;

import io.arnab.spring_jpa_performance.shared_kernel.MobileNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BlockedMobileService {
    private static final Logger logger = LoggerFactory.getLogger(BlockedMobileService.class);
    public boolean verify(MobileNumber mobileNumber) {
        try {
            Thread.sleep(200);
            logger.info("validating if mobile number {} is black listed", mobileNumber);
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
