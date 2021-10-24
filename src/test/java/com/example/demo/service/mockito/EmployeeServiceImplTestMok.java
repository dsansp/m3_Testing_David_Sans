package com.example.demo.service.mockito;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceImplTestMok {
    EmployeeRepositoryImpl repositoryMock;
    EmployeeServiceImpl service;

    @BeforeEach
    void setUp() {
        repositoryMock = mock(EmployeeRepositoryImpl.class);
        service = new EmployeeServiceImpl(repositoryMock);

    }
    @Test
    void count() {
        when(repositoryMock.count()).thenReturn(5);
        Integer result = service.count();
        assertNotNull(result);
        assertEquals(5, result);
    }


    @Test
    void findAll() {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee(1L, "E1", 20));
        employees.add(new Employee(2L, "E2", 20));
        employees.add(new Employee(3L, "E3", 20));

        when(repositoryMock.findAll()).thenReturn(employees);
        List<Employee> result = service.findAll();
        assertNotNull(result);
        assertEquals(3L, result.size());
        verify(repositoryMock).findAll();
    }

    @Test
    void findOneOptional() {

        Employee employee1 = new Employee(1L, "E1", 40);
        when(repositoryMock.findOne(any())).thenReturn(employee1);
        Optional<Employee> employeeOpt = service.findOneOptional(900L);
        assertTrue(employeeOpt.isPresent());
    }

    @Test
    void findOneNullOptional() {
        when(repositoryMock.findOne(anyLong())).thenReturn(null);
        Optional<Employee> employeeOpt = service.findOneOptional(900L);
        assertTrue(employeeOpt.isEmpty());
        verify(repositoryMock).findOne(anyLong());
    }

    @Test
    void findOneOptionalException() {
        when(repositoryMock.findOne(anyLong())).thenThrow(new IllegalArgumentException());
        Optional<Employee> employeeOpt = service.findOneOptional(900L);
        assertTrue(employeeOpt.isEmpty());
        verify(repositoryMock).findOne(anyLong());
    }

    @Test
    void save() {

        Employee employee1 = new Employee(1L, "E1", 40);
        Employee employee2 = new Employee(1L, "E1", 40);
        when(repositoryMock.save(any())).thenReturn(employee1);
        Employee result = service.save(employee1);
        Employee result2 = service.save(employee2);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertNotNull(result2);
        assertEquals(1L, result2.getId());
    }

    @Test
    void delete() {
        when(repositoryMock.delete(any())).thenReturn(true);
        boolean result = service.delete(1L);
        assertTrue(result);
        verify(repositoryMock).delete(any());
    }
    @Test
    void deleteAll() {

        service.deleteAll();
        verify(repositoryMock).deleteAll();
    }

    }