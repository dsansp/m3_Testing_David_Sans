package com.example.demo.service.Junit;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    EmployeeService service;
    EmployeeRepository repository;
    @BeforeEach
    void setUp(){
        EmployeeRepository repository = new EmployeeRepositoryImpl();
        service = new EmployeeServiceImpl(repository);
    }
    @DisplayName("Funciones Buscar")
    @Nested
    class RetrieveTest {
        @DisplayName("Contar número de empleados")
        @Test
        void count() {
            Integer count = service.count();
            assertNotNull(count);
            assertEquals(3, count);
        }
        @DisplayName("Buscar todos los empleados")
        @Test
        void findAll() {
            List<Employee> employees = service.findAll();
            assertNotNull(employees);
            assertEquals(3, employees.size());
        }
        @DisplayName("Buscar un empleado por id ")
        @Test
        void findOneOKTest() {
            Employee employee = service.findOne(1L);
            assertNotNull(employee);
            assertEquals(1l, employee.getId());
        }
        @DisplayName("Buscar un empleado con id que no existe ")
        @Test
        void findOneNotExistTest() {
            Employee employee = service.findOne(999L);
            assertNull(employee);
        }
        @DisplayName("Buscar un empleado con id null")
        @Test
        void findOneExceptionTest(){
            assertThrows(
                    IllegalArgumentException.class,
                    ()->service.findOne(null)
            );
        }
        @DisplayName("Buscar un empleado - con Optional")
        @Test
        void findOneOKOptional() {
            Optional<Employee> employeeOpt = service.findOneOptional(1L);
            assertTrue(employeeOpt.isPresent());
            Long id = employeeOpt.get().getId();
            assertEquals(1, id);
        }
        @DisplayName("Buscar un empleado con id que no existe en la base de datos ")

        @Test
        void findOneNotExistOptional() {
            Optional<Employee> employeeOpt = service.findOneOptional(999L);
            assertTrue(employeeOpt.isEmpty());
        }
        @DisplayName("Buscar un empleado con id nulo con Optional")
        @Test
        void findOneNullOptional() {
            Optional<Employee> employeeOpt = service.findOneOptional(null);
            assertTrue(employeeOpt.isEmpty());
        }
    }

    @DisplayName("Funcionalidad CREATE y UPDATE")
    @Nested
    class SaveTest {
        @DisplayName("Comprobar save con id = null")
        @Test
        void saveIdNullTest(){
            Employee employee = new Employee(null,"Nombre1",45);
            Employee result = service.save(employee);
            assertNull(result);

        }
        @DisplayName("Comprobar save con id = 0")
        @Test
        void saveIdZeroTest(){
            Employee employee = new Employee(0L,"Nombre1",42);
            Employee result = service.save(employee);
            assertNotNull(result);
            System.out.println(result.getId());
            assertNotNull(result.getId());
           // assertEquals(4, result.getId());
            assertEquals(3, service.count());
        }
        @DisplayName("Comprobar save con un id < 0")
        @Test
        void saveNegativeTest(){
            Employee employee = new Employee(-15L,"Nombre",35);
            assertEquals(3, service.count());
            Employee result = service.save(employee);
            assertNotNull(result);
            assertNotNull(result.getId());
            assertEquals(-15, result.getId());
            assertEquals(3, service.count());
        }
        @DisplayName("Comprobar que se actualizan los empleados")
        @Test
        void saveUpdateTest(){
            Employee employee = new Employee(1L, "Emp 1 updated", 30);
            assertEquals(3,service.count());
            Employee result = service.save(employee);
            assertEquals(3, service.count());
            assertEquals(1L,result.getId());

        }
    }

    @DisplayName("Funcionalidad DELETE")
    @Nested
    class DeleteTest {
        @DisplayName("Borrar con id nulo")
        @Test
        void deleteNullTest(){
            boolean result = service.delete(null);
            assertFalse(result);
        }
        @DisplayName("Borrar un empleado OK")
        @Test
        void deleteOKTest(){
            boolean result = service.delete(1L);
            assertTrue(result);
            assertTrue(service.count() > 0);
            service.delete(1L);
            assertEquals(2,service.count());
        }
        @DisplayName("Borrar un empleado con id que no existe ")
        @Test
        void deleteNotExistsTest(){
            boolean result = service.delete(999L);
            assertFalse(result);
        }
        @DisplayName("Borrar todos los empleados")
        @Test
        void deleteAllTest(){
            assertTrue(service.count() > 0);
            service.deleteAll();
            assertEquals(0, service.count());
        }
    }
   }