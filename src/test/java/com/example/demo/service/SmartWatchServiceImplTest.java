package com.example.demo.service;

import com.example.demo.domain.SmartPhone;
import com.example.demo.domain.SmartWatch;
import com.example.demo.domain.pieces.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.management.monitor.Monitor;
import java.security.Provider;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartWatchServiceImplTest {

    @Test
    void count() {
        SmartWatchServiceImpl service= new SmartWatchServiceImpl();
        Integer num = service.count();
        assertNotNull(num);
        assertTrue(num>0);
        assertEquals(3,num);
    }

    @Test
    void findAllTest() {
        SmartWatchServiceImpl service= new SmartWatchServiceImpl();
        List<SmartWatch> smartwatches =service.findAll();
        assertNotNull(smartwatches);
        assertEquals(3,smartwatches.size());
    }

    @Test
    void findOneSmartWatchTest() {
        SmartWatchServiceImpl service= new SmartWatchServiceImpl();
        SmartWatch smart1 = service.findOne(1L);
        assertNotNull(smart1);
        assertEquals(1L,smart1.getId());
        assertNotNull(smart1.getName());
    }
    @Test
    void findOneSmartWatch99Test() {
        SmartWatchServiceImpl service= new SmartWatchServiceImpl();
        SmartWatch smart1 = service.findOne(99L);
        assertNull(smart1);
    }
    @Test
     void findOneExceptionSmWTest(){
        SmartWatchServiceImpl service= new SmartWatchServiceImpl();
        // verifica si se ha lanzado una excepción
        assertThrows(
                IllegalArgumentException.class,
                () -> service.findOne(null)
        );

    }

    @Test
    void saveNullTest(){
        SmartWatchServiceImpl service= new SmartWatchServiceImpl();

        assertThrows(
                IllegalArgumentException.class,
                () -> service.save(null)
        );

    }
    @Test
    void saveIdZeroTest() {
        SmartWatchServiceImpl service= new SmartWatchServiceImpl();
        Battery battery = new Battery(1L, 3500.0);
        RAM ram = new RAM(1L, "DDR4", 8);
        CPU cpu = new CPU(1L, 4);
        HealthMonitor monitor = new HealthMonitor(1L,12.6,7);
        SmartWatch smart = new SmartWatch(0L, "Ios smartwatch", ram, battery, cpu, true, monitor);
        smart.getCpu().start();
        assertEquals(3,service.count());
        SmartWatch result= service.save(smart);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());

//suma?
    }

    @Test
    void saveUpdateTest() {
        SmartWatchServiceImpl service= new SmartWatchServiceImpl();
        Battery battery = new Battery(1L, 3500.0);
        RAM ram = new RAM(1L, "DDR4", 8);
        CPU cpu = new CPU(1L, 4);
        HealthMonitor monitor = new HealthMonitor(1L,12.6,7);
        SmartWatch smart = new SmartWatch(1L, "update smartwatch", ram, battery, cpu, true, monitor);
        smart.getCpu().start();

        assertEquals(3,service.count());
// not solved sigue sumando

        SmartWatch result= service.save(smart);
        assertEquals(3,service.count());

        assertEquals(1L,result.getId());
        SmartWatch smart1= service.findOne(1L);
        assertEquals("update smartwatch", smart1.getName());


    }
    @Test
    void saveNegativeIdTest(){
        SmartWatchServiceImpl service= new SmartWatchServiceImpl();

        Battery battery = new Battery(1L, 3500.0);
        RAM ram = new RAM(1L, "DDR4", 8);
        CPU cpu = new CPU(1L, 4);

        // smartphone exclusive pieces
        HealthMonitor monitor = new HealthMonitor(1L, 0.0, 0);
        SmartWatch smart = new SmartWatch(-4L, "negative smartwatch", ram, battery, cpu, true, monitor);
        smart.getCpu().start();


        assertEquals(3,service.count());
        SmartWatch result= service.save(smart);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());
        assertThrows(
                IllegalArgumentException.class,
                () -> service.save(null)
       );
    }
        @Test
    void deleteNullTest() {
            SmartWatchServiceImpl service= new SmartWatchServiceImpl();
            boolean result = service.delete(null);
            assertFalse(result);
        }
    @Test
    void deleteNotContainsTest(){
        SmartWatchServiceImpl service= new SmartWatchServiceImpl();
        boolean result = service.delete(886L);
        assertFalse(result);
    }

    @Test
    void deleteOkTest(){
        SmartWatchServiceImpl service= new SmartWatchServiceImpl();
        boolean result = service.delete(1L);
        assertTrue(result);
    }
    @Test
    void deleteAll() {
        SmartWatchServiceImpl service= new SmartWatchServiceImpl();
        assertTrue(service.count() > 0);
        service.deleteAll();
        assertEquals(0, service.count());

    }
}