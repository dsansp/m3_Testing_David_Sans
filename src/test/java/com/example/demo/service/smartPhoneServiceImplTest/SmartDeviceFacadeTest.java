package com.example.demo.service.smartPhoneServiceImplTest;

import com.example.demo.domain.SmartDevice;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.SmartWatch;
import com.example.demo.service.SmartDeviceFacade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartDeviceFacadeTest {

    @Test
    void createSmartPhone() {
        SmartDevice result = SmartDeviceFacade.createSmartPhone();
        assertNotNull(result);
        assertNotNull(result.getCpu());
        assertTrue(result.getCpu().getOn());
        assertNotNull(result.getRam());
        assertNotNull(result.getBattery());
        assertTrue(result instanceof SmartPhone);
        SmartPhone smartphone=(SmartPhone) result;
        assertNotNull(smartphone.getCamera());

    }

    @Test
    void createSmartWatch() {
        SmartDevice result =SmartDeviceFacade.createSmartWatch();
        assertNotNull(result);
        assertNotNull(result.getCpu());
        assertTrue(result.getCpu().getOn());
        assertNotNull(result.getRam());
        assertNotNull(result.getBattery());
        assertTrue(result instanceof SmartWatch);
        SmartWatch smartwatch =(SmartWatch) result;
        assertNotNull(smartwatch.getMonitor());
    }
}