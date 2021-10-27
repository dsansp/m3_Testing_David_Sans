package com.example.demo.service.Junit;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.IRPFCalculator;
import com.example.demo.service.IVACalculator;
import com.example.demo.service.SalaryCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryCalculatorServiceTest<empleado> {
    @DisplayName("verificamos crear con Id null")
    @Test
    void calculateSalaryTestNull() {
Employee empleado = new Employee(null,"pepe",28);

   IRPFCalculator irpfCalculator = new IRPFCalculator();
   IVACalculator ivaCalculator= new IVACalculator();
        SalaryCalculatorService service=new SalaryCalculatorService( irpfCalculator, ivaCalculator);

        double calculate;
        calculate = service.calculateSalary(empleado);

        assertNotNull(calculate);

    }
    @DisplayName("verificamos crear salario Ok")
    @Test
    void calculateSalaryTestOk() {
        Employee empleado = new Employee(null,"pepe",28);

        IRPFCalculator irpfCalculator = new IRPFCalculator();
        IVACalculator ivaCalculator= new IVACalculator();
        SalaryCalculatorService service=new SalaryCalculatorService( irpfCalculator, ivaCalculator);

        double calculate;
        calculate = service.calculateSalary(empleado);

        assertEquals(45641.2, calculate);

    }
}




