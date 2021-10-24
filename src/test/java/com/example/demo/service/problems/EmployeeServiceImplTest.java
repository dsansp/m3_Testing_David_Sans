package com.example.demo.service.problems;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    EmployeeRepositoryImpl repository;
    EmployeeServiceImpl service;


    @Test
    void count() {
        EmployeeRepositoryImpl repository = new EmployeeRepositoryImpl();
        int num = repository.count();
        assertNotNull(num);
        assertTrue(num > 0);
        assertEquals(3, num);
    }

       @Test
      void findAllTest() {
           EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
         assertNotNull(service);
         assertEquals(3, service.count());
     }
    @Test
    void findEmployee1Test() {
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        Employee employee1 = service.findOne(1L);
        assertNotNull(employee1);
        assertEquals(1L, employee1.getId());
        assertNotNull(employee1.getName());


    }
    @Test
    void findEmployee781Test() {
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        Employee employee1 = service.findOne(781L);
        assertNull(employee1);



    }
    @Test
    void findEmployeeNullTest() {
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        assertThrows(
                IllegalArgumentException.class,
                () -> service.findOne(null));



    }


    @Test
    void findOneOptional() {

        EmployeeRepositoryImpl service=new EmployeeRepositoryImpl();

        Optional<Employee> employeeOpt = Optional.ofNullable(service.findOne(900L));

        assertTrue(employeeOpt.isEmpty());
    }

    @Test
    void saveNullTest() {
        EmployeeRepositoryImpl service=new EmployeeRepositoryImpl();
        assertThrows(
                NullPointerException.class,
                () -> service.save(null)
        );
    }
    @Test
    void saveIdZeroTest() {
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        Employee empleado = new Employee(0L, "nombre", 30);
        assertEquals(3, service.count());
        Employee result = service.save(empleado);
        assertEquals(4, service.count());
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(4, result.getId());
    }
    @Test
    void saveUpdateTest() {
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        Employee empleado = new Employee(1L, "nuevo nombre", 30);
        assertEquals(3, service.count());
        Employee result = service.save(empleado);
        assertEquals(3, service.count());
        assertEquals(1L, result.getId());
        Employee employee1 = service.findOne((1L));
        assertEquals("nuevo nombre", employee1.getName());

    }


    @Test
        void saveNegativeIdTest(){
        EmployeeRepositoryImpl service=new EmployeeRepositoryImpl();

      Employee empleado = new Employee(-8L, "nombre XXX", 30);
      assertEquals(3, service.count());
      assertThrows(
              IllegalArgumentException.class,
              () -> service.save(empleado)
      );
      assertEquals(3, service.count());

  }
  @Test
   void deleteNullTest() {
      EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
boolean result = service.delete(null);
assertFalse(result);

  }
    @Test
    void deleteNotContainsTest() {
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        boolean result = service.delete(888L);
        assertFalse(result);

    }
    @Test
    void deleteOkTest() {
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
        boolean result = service.delete(1L);
        assertTrue(result);
    }

    @Test
    void deleteAll() {
        EmployeeRepositoryImpl service = new EmployeeRepositoryImpl();
    assertTrue(service.count()>0);
    service.deleteAll();
    assertEquals(0,service.count());
    }

    }


