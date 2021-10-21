package com.example.demo.service.employeeServiceUmplTest;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {


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
/*

    @Test
    void findOneOptional() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }

 */
}