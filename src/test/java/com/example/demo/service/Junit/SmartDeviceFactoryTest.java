package com.example.demo.service.Junit;

import com.example.demo.domain.SmartDevice;
import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.SmartWatch;

import com.example.demo.service.SmartDeviceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class SmartDeviceFactoryTest {
    @DisplayName("verificamos crear un SmartPhone")

    @Test
    void createByTypeSmartphone() {

        SmartDevice result = SmartDeviceFactory.createByType("phone");

        assertNotNull(result);
        assertNotNull(result.getCpu());
        assertTrue(result.getCpu().getOn());
        assertNotNull(result.getRam());
        assertNotNull(result.getBattery());
       assertTrue(result instanceof SmartPhone);
        SmartPhone smartphone = (SmartPhone) result;
        assertNotNull(smartphone.getCamera());
        assertNotNull(smartphone.toString());

    }


    @DisplayName("verificamos crear un  Smartwatch")
    @Test
    void createByTypeSmartWatch() {

        SmartDevice result = SmartDeviceFactory.createByType("watch");

        assertNotNull(result);
        assertNotNull(result.getCpu());
        assertFalse(result.getCpu().getOn());
        assertNotNull(result.getRam());
        assertNotNull(result.getBattery());

        SmartWatch watch = (SmartWatch) result;
        assertNotNull(watch.getMonitor());

    }

    @DisplayName("Comprobando si podría saltar una exception")
    @Test
    void creatByTypeException(){

        assertThrows(IllegalArgumentException.class,()->SmartDeviceFactory.createByType("patata"));



    }
}